import axios from 'axios';
import { useCallback, useEffect, useState } from 'react';
import { Button, Col, Container, Modal, Pagination, Row, Table, ListGroup } from 'react-bootstrap';
import urlweb from '../../config/config';
import InputMonth from '../../components/InputMonth';
import FormFactura from '../../components/FormFactura';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete, AiFillCheckCircle } from "react-icons/ai";

const Facturas = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };
    const [currentPage, setCurrentPage] = useState(1);
    const [iva, setIva] = useState(0);
    const pageSize = 6;

    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    const [facturas, setFacturas] = useState([]);

    const [showCheck, setShowCheck] = useState(false);
    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);
    const [showAlertDelete, setShowAlertDelete] = useState(false);
    const [showAlertEdit, setShowAlertEdit] = useState(false);
    const [showAlertCreate, setShowAlertCreate] = useState(false);


    const handleAlertCreate = useCallback(() => {
        let showCrear = localStorage.getItem("showCrear");
        if (showCrear === 'true') {
            setShowAlertCreate(true);
        }
        localStorage.setItem("showCrear", false);
    }, []);

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

    const handleChange = (e) => {
        setEditedItem({
            ...editedItem,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            updateFactura();
            setValidated(true);
        }
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
        deleteFactura();
    };

    const handleChangeMes = (e) => {
        setMes(e.target.value);
    };

    const handleChangeAnio = (e) => {
        setAnio(e.target.value);
    };

    const getIva = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/facturas/iva/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setIva(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    const getFacturas = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/facturas/' + anio + '/' + mes;
            console.log(url);
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setFacturas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    const updateFactura = async () => {
        try {
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                handleCloseCheck();
                setShowAlertEdit(true);
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const pagarFactura = async () => {
        try {
            let url = 'http://' + urlweb + '/facturas/pagar';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                handleCloseCheck();
                setShowAlertEdit(true);
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const deleteFactura = async () => {
        try {
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                setShowAlertDelete(true);
                getFacturas();
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
        getFacturas();
        handleAlertCreate();
        getIva();
    }, [getFacturas, handleAlertCreate, getIva]);

    return (
        <>
            <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                <Row>
                    <Col><h1>Facturas</h1></Col>
                    <p></p>
                    <Row className="justify-content-center align-items-center">
                        <Col className='d-flex align-items-center'>
                            <InputMonth
                                mes={mes}
                                anio={anio}
                                onChangeAnio={handleChangeAnio}
                                onChangeMes={handleChangeMes}
                                get={getFacturas}
                            />
                        </Col>
                    </Row>
                    <p></p>
                    <Row className="justify-content-center align-items-center">
                        <Col className='d-flex align-items-center'>
                            <ListGroup>
                                <ListGroup.Item style={{ fontWeight: "bold" }}>IVA a pagar: {formatoMonto(iva)}</ListGroup.Item>
                            </ListGroup>
                        </Col>
                    </Row>
                </Row>
                <p></p>
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
                                        <td>{factura.observaciones}</td>
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
                            <Col className='d-flex align-items-center justify-content-md-end'><Button href="/crearFactura" style={{ backgroundColor: "rgb(165, 192, 221)", color: "black", border: "none", fontWeight: "bold" }}>Registrar una factura</Button></Col>
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
                        setFactura={setEditedItem}
                        validated={validated}
                        modal={true}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleCloseEdit={handleCloseEdit}
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

            {showAlertDelete && (<Alerta mensaje="Factura eliminada correctamente" tipo="danger" show={showAlertDelete} setShow={setShowAlertDelete} />)}
            {showAlertEdit && (<Alerta mensaje="Factura editada correctamente" tipo="primary" show={showAlertEdit} setShow={setShowAlertEdit} />)}
            {showAlertCreate && (<Alerta mensaje="Factura creada correctamente" tipo="success" show={showAlertCreate} setShow={setShowAlertCreate} />)}
        </>
    );
};

export default Facturas;