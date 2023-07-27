import axios from 'axios';
import { useCallback, useEffect, useState } from 'react';
import { Button, Col, Container, Modal, Pagination, Row, Table, ListGroup, Toast } from 'react-bootstrap';
import urlweb from '../../config/config';
import FormMonth from '../../components/FormMonth';
import FormFactura from '../../components/FormFactura';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete, AiFillCheckCircle, AiOutlineDown } from "react-icons/ai";
import Cookies from 'js-cookie';
import { Tooltip } from 'react-tooltip';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';

const Facturas = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const [iva, setIva] = useState(0);
    const pageSize = 10;

    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    const [facturas, setFacturas] = useState([]);
    const meses = Sem1.concat(Sem2);

    const [showCheck, setShowCheck] = useState(false);
    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [showToast, setShowToast] = useState(false);

    const toogleToast = () => setShowToast(!showToast);

    const handlePageChange = (page) => {
        if (page < 1 || page > Math.ceil(facturas.length / pageSize))
            return;
        setCurrentPage(page);
    }

    const paginatedData = facturas.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

    const handleCloseCheck = () => {
        setEditedItem(defaultItem);
        setShowCheck(false);
    }

    const handleShowCheck = (factura) => {
        setEditedItem(factura);
        setShowCheck(true);
    }

    const handleCloseDelete = () => {
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (factura) => {
        setEditedItem(factura);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false)
    };

    const handleShowEdit = (factura) => {
        setEditedItem(factura);
        setShowEdit(true);
    };

    const handleCheck = () => {
        editedItem.estado.id = 3;
        editedItem.fecha_pago = (() => {
            const fechaActual = new Date();
            const dia = String(fechaActual.getDate() + 1).padStart(2, '0');
            const mes = String(fechaActual.getMonth() + 1).padStart(2, '0');
            const anio = fechaActual.getFullYear();
            return anio + '-' + mes + '-' + dia;
        })();
        pagarFactura();
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        editedItem.fecha_borrado = new Date();
        deleteFactura();
    };

    const handleGetToast = (anio, mes) => {
        setAnio(anio);
        setMes(mes);
        getFacturas(anio, mes);
        getIva(anio, mes);
        toogleToast();
    };

    const getIva = useCallback(async (anio, mes) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/facturas/iva/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setIva(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, []);

    const getFacturas = useCallback(async (anio, mes) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/facturas/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setFacturas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, []);

    const updateFactura = async (editedItem) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                getFacturas(anio, mes);
                getIva(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    title: 'Factura editada correctamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const pagarFactura = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/facturas/pagar';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseCheck();
                getFacturas(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    title: 'Factura pagada correctamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const deleteFactura = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                getFacturas(anio, mes);
                getIva(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    width: '400px',
                    title: 'Factura eliminada correctamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const formatearFecha = (fecha) => {
        if (fecha === null) {
            return "---"
        }
        let fechaC = fecha.split('T')[0];
        fechaC = fechaC.split('-');
        return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
    };

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        numero_factura: 0,
        fecha_emision: '',
        fecha_vencimiento: '',
        fecha_pago: '',
        monto: 0,
        observaciones: '',
        borrado: false,
        estado: {},
        empresa: {},
        fecha_creacion: '',
        fecha_modificacion: '',
        fecha_borrado: ''
    });

    const defaultItem = {
        id: null,
        numero_factura: 0,
        fecha_emision: '',
        fecha_vencimiento: '',
        fecha_pago: '',
        monto: 0,
        observaciones: '',
        borrado: false,
        estado: {},
        empresa: {},
        fecha_creacion: '',
        fecha_modificacion: '',
        fecha_borrado: ''
    }

    useEffect(() => {
        getFacturas(anio, mes);
        getIva(anio, mes);
        const alert = localStorage.getItem("alert");
        if (alert === "true") {
            localStorage.setItem("alert", false);
            Alerta.fire({
                icon: 'success',
                title: 'Factura creada correctamente',
            });
        }
    }, [getFacturas, getIva, anio, mes]);

    return (
        <>
            <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                <Row>
                    <Col><h1>Facturas</h1></Col>
                </Row>
                <Row className="justify-content-center align-items-center mb-2">
                    <Col className='d-flex align-items-center gap-2 '>
                        {`De ${meses[mes - 1]} del año ${anio}`}
                        <AiOutlineDown
                            style={{ cursor: 'pointer' }}
                            onClick={toogleToast} />
                        <Toast 
                            show={showToast} 
                            onClose={toogleToast} 
                            style={{
                                    padding: 0, 
                                    position: "absolute",
                                    top: 164,
                                    zIndex: 999
                                }}
                            >
                            <Toast.Header>
                                <strong className="me-auto">Filtrar fecha: </strong>
                            </Toast.Header>
                            <Toast.Body style={{backgroundColor: "white"}}>
                                <FormMonth
                                    mes={mes}
                                    anio={anio}
                                    get={handleGetToast}
                                />
                            </Toast.Body>
                        </Toast>
                    </Col>
                    <Col className='d-flex justify-content-end'>
                        <ListGroup>
                            <ListGroup.Item style={{ fontWeight: "bold", padding: "2px 16px" }}>IVA a pagar: {formatoMonto(iva)}</ListGroup.Item>
                        </ListGroup>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table responsive="sm" striped hover>
                            <thead>
                                <tr>
                                    <th>N° factura</th>
                                    <th>Empresa</th>
                                    <th>Fecha emision</th>
                                    <th>Monto</th>
                                    <th>Fecha vencimiento</th>
                                    <th>Fecha pago</th>
                                    <th>Estado</th>
                                    <th>Observaciones</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((factura) => (
                                    <tr key={factura.id}>
                                        <td>{factura.numero_factura}</td>
                                        <td>{factura.empresa.nombre}</td>
                                        <td>{formatearFecha(factura.fecha_emision)}</td>
                                        <td>{formatoMonto(factura.monto)}</td>
                                        <td>{formatearFecha(factura.fecha_vencimiento)}</td>
                                        <td>{factura.fecha_pago ? formatearFecha(factura.fecha_pago) : '---'}</td>
                                        <td>{factura.estado.nombre}</td>
                                        <td style={{ maxWidth: 200 }}>
                                            <span
                                                data-tooltip-id="tooltip-descrip"
                                                data-tooltip-content={factura.observaciones}
                                                data-tooltip-variant='info'
                                            >
                                                {factura.observaciones.length < 15 ?
                                                    factura.observaciones :
                                                    factura.observaciones.slice(0, 15) + "..."}
                                            </span>
                                        </td>
                                        <td>
                                            <a href="#sc" style={{ cursor: "pointer", marginRight: 2, color: "#198754" }} onClick={() => handleShowCheck(factura)}><AiFillCheckCircle /></a>
                                            <a href="#se" style={{ cursor: "pointer", marginRight: 2, color: "#0d6efd" }} onClick={() => handleShowEdit(factura)}><AiFillEdit /></a>
                                            <a href="#sd" style={{ cursor: "pointer", marginRight: 2, color: "#dc3545" }} onClick={() => handleShowDelete(factura)}><AiFillDelete /></a>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        <Row>
                            <Col>
                                <Pagination>
                                    <Pagination.First onClick={() => handlePageChange(1)} />
                                    <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)} />
                                    {[...Array(Math.ceil(facturas.length / pageSize)).keys()].map((page) => {
                                        if (page === 0 || page === Math.ceil(facturas.length / pageSize) - 1 || (currentPage - 2 <= page && page <= currentPage + 2)) {
                                            return (
                                                <Pagination.Item
                                                    key={page + 1}
                                                    active={page + 1 === currentPage}
                                                    onClick={() => handlePageChange(page + 1)}
                                                >
                                                    {page + 1}
                                                </Pagination.Item>
                                            );
                                        } else if (page === currentPage - 3 || page === currentPage + 3) {
                                            return (
                                                <Pagination.Ellipsis />
                                            );
                                        }
                                        return null;
                                    })}
                                    <Pagination.Next onClick={() => handlePageChange(currentPage + 1)} />
                                    <Pagination.Last onClick={() => handlePageChange(Math.ceil(facturas.length / pageSize))} />
                                </Pagination>
                            </Col>
                            <Col className='d-flex align-items-center justify-content-md-end'><Button href="/crearFactura" style={{ backgroundColor: "rgb(165, 192, 221)", color: "black", border: "none", fontWeight: "bold" }}>Registrar factura</Button></Col>
                        </Row>

                    </Col>
                </Row>
            </Container >

            {/*Modal para marcar como pagada*/}
            < Modal show={showCheck} onHide={handleCloseCheck} >
                <Modal.Header closeButton>
                    <Modal.Title>Marcar como pagada</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea marcar como pagada la factura?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseCheck}>Cerrar</Button>
                    <Button variant='success' onClick={handleCheck}>Marcar como pagada</Button>
                </Modal.Footer>
            </Modal >

            {/*Modal para editar*/}
            < Modal show={showEdit} onHide={handleCloseEdit} >
                <Modal.Header closeButton>
                    <Modal.Title>Editar Factura</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormFactura
                        factura={editedItem}
                        postFactura={updateFactura}
                        modal={true}
                        handleClose={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal >

            {/*Modal para eliminar*/}
            < Modal show={showDelete} onHide={handleCloseDelete} >
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar Factura</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar La factura?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal >

            {/*Tooltip para descripción*/}
            <Tooltip id="tooltip-descrip" opacity={1} />
        </>
    );
};

export default Facturas;