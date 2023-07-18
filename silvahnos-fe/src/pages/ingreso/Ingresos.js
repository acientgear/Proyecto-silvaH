import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';
import { Button, Col, Container, Modal, Pagination, Row, Table } from 'react-bootstrap';
import InputMonth from '../../components/InputMonth';
import FormIngreso from '../../components/FormIngreso';
import urlweb from '../../config/config';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";

const Ingresos = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };

    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [ingresos, setIngresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

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
        setValidated(false);
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
            updateIngreso();
            setValidated(true);
        }
    };

    const updateIngreso = async () => {
        try {
            let url = 'http://' + urlweb + '/ingresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                setShowAlertEdit(true);
                getIngresos();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteIngreso();
    };

    const deleteIngreso = async () => {
        try {
            let url = 'http://' + urlweb + '/ingresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                setShowAlertDelete(true);
                getIngresos();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleChangeMes = (e) => {
        setMes(e.target.value);
    };

    const handleChangeAnio = (e) => {
        setAnio(e.target.value);
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        motivo: {},
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

    const getIngresos = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/ingresos/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    const formatearFecha = (fecha) => {
        const fechaActual = new Date(fecha);
        return fechaActual.getDate() + '/' + (fechaActual.getMonth() + 1) + '/' + fechaActual.getFullYear();
    };

    let total = 0;
    ingresos.forEach(ingreso => {
        total += ingreso.monto;
    });

    useEffect(() => {
        getIngresos();
        handleAlertCreate();
    }, [getIngresos, handleAlertCreate]);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Ingresos</h1></Col>
                    <Row className="justify-content-center align-items-center">
                        <Col className='d-flex align-items-center'>
                            <InputMonth
                                mes={mes}
                                anio={anio}
                                onChangeAnio={handleChangeAnio}
                                onChangeMes={handleChangeMes}
                                get={getIngresos}
                            />
                        </Col>
                    </Row>
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
                                        <td>{ingreso.descripcion}</td>
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
                        setIngreso={setEditedItem}
                        validated={validated}
                        modal={true}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleCloseEdit={handleCloseEdit}
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

            {showAlertDelete && (<Alerta mensaje="Ingreso eliminado correctamente" tipo="danger" show={showAlertDelete} setShow={setShowAlertDelete} />)}
            {showAlertEdit && (<Alerta mensaje="Ingreso editado correctamente" tipo="primary" show={showAlertEdit} setShow={setShowAlertEdit} />)}
            {showAlertCreate && (<Alerta mensaje="Ingreso creado correctamente" tipo="success" show={showAlertCreate} setShow={setShowAlertCreate} />)}
        </>
    );
};

export default Ingresos;

