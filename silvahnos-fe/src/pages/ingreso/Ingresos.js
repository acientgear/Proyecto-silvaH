import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';
import { Button, Col, Container, Modal, Pagination, Row, Table, Toast } from 'react-bootstrap';
import FormMonth from '../../components/FormMonth';
import FormIngreso from '../../components/FormIngreso';
import urlweb from '../../config/config';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete, AiOutlineDown } from "react-icons/ai";
import Cookies from 'js-cookie';
import { Tooltip } from 'react-tooltip';
import Sem2 from '../../components/data/Sem2';
import Sem1 from '../../components/data/Sem1';

const Ingresos = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };

    const [currentPage, setCurrentPage] = useState(1);
    const meses = Sem1.concat(Sem2);
    const pageSize = 10;

    const [ingresos, setIngresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [showToast, setShowToast] = useState(false);

    const toogleToast = () => setShowToast(!showToast);

    const handlePageChange = (page) => {
        if (page < 1 || page > Math.ceil(ingresos.length / pageSize))
            return;
        setCurrentPage(page);
    }

    const paginatedData = ingresos.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const handleCloseDelete = () => {
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (ingreso) => {
        setEditedItem(ingreso);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false);
    };

    const handleShowEdit = (ingreso) => {
        setEditedItem(ingreso);
        setShowEdit(true);
    };

    const updateIngreso = async (editedItem) => {
        try {
            let url = 'http://' + urlweb + '/ingresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                getIngresos(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    title: 'Ingreso editado exitosamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        editedItem.fecha_borrado = new Date();
        deleteIngreso();
    };

    const deleteIngreso = async () => {
        try {
            let url = 'http://' + urlweb + '/ingresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                getIngresos(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    width: '400px',
                    title: 'Ingreso eliminado exitosamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleGetToast = (anio, mes) => {
        toogleToast();
        setAnio(anio);
        setMes(mes);
        getIngresos(anio, mes);
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        motivo: {
            id: 0
        },
        monto: '',
        descripcion: '',
    });

    const defaultItem = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        motivo: {},
        monto: '',
        descripcion: '',
    }

    const getIngresos = useCallback(async (anio, mes) => {
        try {
            let url = 'http://' + urlweb + '/ingresos/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, []);

    const formatearFecha = (fecha) => {
        const fechaActual = new Date(fecha);
        return fechaActual.getDate() + '/' + (fechaActual.getMonth() + 1) + '/' + fechaActual.getFullYear();
    };

    let total = 0;
    ingresos.forEach(ingreso => {
        total += ingreso.monto;
    });

    useEffect(() => {
        getIngresos(anio, mes);
        const alert = localStorage.getItem("alert");
        if (alert === "true") {
            localStorage.setItem("alert", false);
            Alerta.fire({
                icon: 'success',
                title: 'Ingreso creado exitosamente',
            });
        }
    }, [anio, mes, getIngresos]);

    return (
        <>
            <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                <Row>
                    <Col><h1>Ingresos</h1></Col>
                </Row>
                <Row className="justify-content-center align-items-center">
                    <Col className='d-flex align-items-center gap-2'>
                        {`De ${meses[mes - 1]} de ${anio}`}
                        <AiOutlineDown
                            style={{ cursor: 'pointer' }}
                            onClick={toogleToast} />
                        <Toast 
                            show={showToast} 
                            onClose={toogleToast} 
                            style={{
                                    padding: 0, 
                                    position: "absolute",
                                    top: 162,
                                    zIndex: 999,
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
                </Row>
                <p></p>
                <Row>
                    <Col>
                        <Table striped responsive="sm" hover>
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Descripción</th>
                                    <th>Motivo</th>
                                    <th>Patente</th>
                                    <th>Monto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((ingreso) => (
                                    <tr key={ingreso.id}>
                                        <td>{formatearFecha(ingreso.fecha_creacion)}</td>
                                        <td style={{ maxWidth: 200 }}>
                                            <span
                                                data-tooltip-id="tooltip-descrip"
                                                data-tooltip-content={ingreso.descripcion}
                                                data-tooltip-variant='info'
                                            >
                                                {ingreso.descripcion.length < 15 ?
                                                    ingreso.descripcion :
                                                    ingreso.descripcion.slice(0, 15) + "..."}
                                            </span>
                                        </td>
                                        <td>{ingreso.motivo.nombre}</td>
                                        <td>{ingreso.patente}</td>
                                        <td>{formatoMonto(ingreso.monto)}</td>
                                        <td>
                                            <a href="#se" style={{ cursor: "pointer", marginRight: 2, color: "#0d6efd" }} onClick={() => handleShowEdit(ingreso)}><AiFillEdit /></a>
                                            <a href="#sd" style={{ cursor: "pointer", marginRight: 2, color: "#dc3545" }} onClick={() => handleShowDelete(ingreso)}><AiFillDelete /></a>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                            <tfoot>
                                <tr style={{ background: "#E6F4DD" }}>
                                    <td>Total</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>{formatoMonto(total)}</td>
                                    <td></td>
                                </tr>
                            </tfoot>
                        </Table>
                        <Row>
                            <Col>
                                <Pagination>
                                    <Pagination.First onClick={() => handlePageChange(1)} />
                                    <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)} />
                                    {[...Array(Math.ceil(ingresos.length / pageSize)).keys()].map((page) => {
                                        if (page === 0 || page === Math.ceil(ingresos.length / pageSize) - 1 || (currentPage - 2 <= page && page <= currentPage + 2)) {
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
                                    <Pagination.Last onClick={() => handlePageChange(Math.ceil(ingresos.length / pageSize))} />
                                </Pagination>
                            </Col>
                            <Col className='d-flex align-items-center justify-content-end'>
                                <Button href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold" }}>Registrar ingreso</Button></Col>
                        </Row>
                    </Col>

                </Row>
            </Container>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormIngreso
                        ingreso={editedItem}
                        postIngreso={updateIngreso}
                        modal={true}
                        handleClose={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar Ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar el ingreso?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>

            {/*Tooltip para descripción*/}
            <Tooltip id="tooltip-descrip" opacity={1} />
        </>
    );
};

export default Ingresos;

