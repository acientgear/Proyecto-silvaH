import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';
import { Button, Col, Container, Modal, Row, Table, Pagination, Toast } from 'react-bootstrap';
import FormMonth from '../../components/FormMonth';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';
import Alerta from '../../components/Alerta';
import { AiFillEdit, AiFillDelete, AiOutlineDown } from "react-icons/ai";
import Cookies from 'js-cookie';
import { Tooltip } from 'react-tooltip';
import Sem1 from '../../components/data/Sem1'
import Sem2 from '../../components/data/Sem2'


const Egresos = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };

    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 10;

    const [egresos, setEgresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    const meses = Sem1.concat(Sem2);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [showToast, setShowToast] = useState(false);

    const toogleToast = () => setShowToast(!showToast);

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
        setEditedItem(egreso);
        setShowEdit(true);
    };

    const updateEgreso = async (editedItem) => {
        try {
            let url = 'http://' + urlweb + '/egresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseEdit();
                getEgresos(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    title: 'Egreso editado correctamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        editedItem.fecha_borrado = new Date();
        deleteEgreso();
    };

    const handleGetToast = (anio, mes) => {
        setAnio(anio);
        setMes(mes);
        toogleToast();
        getEgresos(anio, mes);
    };

    const deleteEgreso = async () => {
        try {
            let url = 'http://' + urlweb + '/egresos';
            const response = await axios.post(url, editedItem, config);
            if (response.status === 200) {
                handleCloseDelete();
                getEgresos(anio, mes);
                Alerta.fire({
                    icon: 'success',
                    width: '400px',
                    title: 'Egreso eliminado correctamente',
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
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
        motivo: {
            id: 0
        },
        monto: '',
        descripcion: '',
    }

    const getEgresos = useCallback(async (anio, mes) => {
        try {
            let url = 'http://' + urlweb + '/egresos/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setEgresos(response.data);
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
    egresos.forEach((egreso) => {
        total += egreso.monto;
    });

    useEffect(() => {
        getEgresos(anio, mes);
        const alert = localStorage.getItem("alert");
        if (alert === "true") {
            localStorage.setItem("alert", false);
            Alerta.fire({
                icon: 'success',
                title: 'Egreso creado correctamente',
            });
        }
    }, [getEgresos, anio, mes]);

    return (
        <>
            <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                <Row>
                    <Col><h1>Egresos</h1></Col>
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
                                top: 164,
                                zIndex: 999
                            }}
                        >
                            <Toast.Header>
                                <strong className="me-auto">Filtrar fecha: </strong>
                            </Toast.Header>
                            <Toast.Body style={{ backgroundColor: "white" }}>
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
                                    <th>Monto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((egreso) => (
                                    <tr key={egreso.id}>
                                        <td>{formatearFecha(egreso.fecha_creacion)}</td>
                                        <td style={{ width: "20%" }}>
                                            <span
                                                data-tooltip-id="tooltip-descrip"
                                                data-tooltip-content={egreso.descripcion}
                                                data-tooltip-variant='info'
                                            >
                                                {egreso.descripcion.length < 15 ?
                                                    egreso.descripcion :
                                                    egreso.descripcion.slice(0, 15) + "..."}
                                            </span>
                                        </td>
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
                        postEgreso={updateEgreso}
                        modal={true}
                        handleClose={handleCloseEdit}
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

            {/*Tooltip para descripción*/}
            <Tooltip id="tooltip-descrip" opacity={1} />
        </>
    );
}

export default Egresos;