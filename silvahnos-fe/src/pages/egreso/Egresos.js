import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';
import { Button, Col, Container, Modal, Row, Table, Pagination } from 'react-bootstrap';
import InputMonth from '../../components/InputMonth';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";

const Egresos = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };

    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [egresos, setEgresos] = useState([]);
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
        if (page < 1 || page > Math.ceil(egresos.length / pageSize))
            return;
        setCurrentPage(page);
    }

    const paginatedData = egresos.slice(
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

    const handleShowDelete = (egreso) => {
        setEditedItem(egreso);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false)
    };

    const handleShowEdit = (egreso) => {
        setValidated(false);
        setEditedItem(egreso);
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
            updateEgreso();
            setValidated(true);
        }
    };

    const updateEgreso = async () => {
        try {
            let url = 'http://' + urlweb + '/egresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                getEgresos();

            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteEgreso();
    };

    const deleteEgreso = async () => {
        try {
            let url = 'http://' + urlweb + '/egresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                setShowAlertDelete(true);
                getEgresos();
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
        motivo: {},
        monto: '',
        descripcion: '',
    }

    const getEgresos = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/egresos/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setEgresos(response.data);
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
    egresos.forEach((egreso) => {
        total += egreso.monto;
    });

    useEffect(() => {
        getEgresos();
        handleAlertCreate();
    }, [getEgresos, handleAlertCreate]);

    console.log(egresos);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Egresos</h1></Col>
                    <Row className="justify-content-center align-items-center">
                        <Col className='d-flex align-items-center'>
                            <InputMonth
                                mes={mes}
                                anio={anio}
                                onChangeAnio={handleChangeAnio}
                                onChangeMes={handleChangeMes}
                                get={getEgresos}
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
                                    <th>Monto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((egreso) => (
                                    <tr key={egreso.id}>
                                        <td>{formatearFecha(egreso.fecha_creacion)}</td>
                                        <td>{egreso.descripcion}</td>
                                        <td>{egreso.motivo.nombre}</td>
                                        <td>{formatoMonto(egreso.monto)}</td>
                                        <td>
                                            <button
                                                type="button"
                                                style={{ cursor: "pointer", marginRight: 2, color: "#0d6efd", background: "none", border: "none", padding: 0 }}
                                                onClick={() => handleShowEdit(egreso)}
                                            >
                                                <AiFillEdit />
                                            </button>
                                            <button
                                                type="button"
                                                style={{ cursor: "pointer", marginRight: 2, color: "#dc3545", background: "none", border: "none", padding: 0 }}
                                                onClick={() => handleShowDelete(egreso)}
                                            >
                                                <AiFillDelete />
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>

                            <tfoot>
                                <tr style={{ background: "#FBE6DD" }}>
                                    <td>Total</td>
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
                                    {[...Array(Math.ceil(egresos.length / pageSize)).keys()].map((page) => {
                                        if (page === 0 || page === Math.ceil(egresos.length / pageSize) - 1 || (currentPage - 2 <= page && page <= currentPage + 2)) {
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
                                    <Pagination.Last onClick={() => handlePageChange(Math.ceil(egresos.length / pageSize))} />
                                </Pagination>
                            </Col>
                            <Col className='d-flex align-items-center justify-content-end'><Button href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar egreso</Button></Col>                        </Row>
                    </Col>
                </Row>
            </Container>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormEgreso
                        egreso={editedItem}
                        setEgreso={setEditedItem}
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
                    <Modal.Title>Eliminar Egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar el egreso?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>
            {showAlertDelete && (<Alerta mensaje="Egreso eliminado correctamente" tipo="danger" show={showAlertDelete} setShow={setShowAlertDelete} />)}
            {showAlertEdit && (<Alerta mensaje="Egreso editado correctamente" tipo="primary" show={showAlertEdit} setShow={setShowAlertEdit} />)}
            {showAlertCreate && (<Alerta mensaje="Egreso creado correctamente" tipo="success" show={showAlertCreate} setShow={setShowAlertCreate} />)}
        </>
    );
}

export default Egresos;