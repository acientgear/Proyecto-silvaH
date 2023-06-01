import axios from 'axios';
import { useEffect, useState, useCallback } from 'react';
import { Button, Col, Container, Modal, Row, Table, Pagination } from 'react-bootstrap';
import InputMonth from '../../components/InputMonth';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";

const Egresos = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [egresos, setEgresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handlePageChange = (page) => {
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
            let url = 'http://'+urlweb+'/egresos';
            const response = await axios.post(url, editedItem);
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
            let url = 'http://'+urlweb+'/egresos';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseDelete();
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
        origen: '',
        monto: '',
        descripcion: '',
    });

    const defaultItem = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        origen: '',
        monto: '',
        descripcion: '',
    }

    const getEgresos = useCallback(async () => {
        try {
          let url = 'http://'+urlweb+'/egresos/' + anio + '/' + mes;
          const response = await axios.get(url);
          if (response.status === 200) {
            setEgresos(response.data);
          }
        } catch (err) {
          console.log(err.message);
        }
      }, [anio, mes]);
      

    const formatearFecha = (fecha) => {
        let fechaC = fecha.split('T')[0];
        fechaC = fechaC.split('-');
        return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
    };

    let total = 0;
    egresos.forEach((egreso) => {
        total += egreso.monto;
    });

    useEffect(() => {
        getEgresos();
    },[getEgresos]);

    console.log("fecha creación: ",egresos.map((egreso) => egreso.fecha_creacion));
    console.log(new Date());

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Egresos</h1></Col>
                    <Col className='d-flex align-items-center'>
                        <InputMonth
                            mes={mes}
                            anio={anio}
                            onChangeAnio={handleChangeAnio}
                            onChangeMes={handleChangeMes}
                            get={getEgresos}
                        />
                    </Col>
                    <Col className='d-flex align-items-center justify-content-end'><Button href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar egreso</Button></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped responsive="sm" hover>
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Descripción</th>
                                    <th>Origen</th>
                                    <th>Monto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((egreso) => (
                                    <tr key={egreso.id}>
                                        <td>{formatearFecha(egreso.fecha_creacion)}</td>
                                        <td>{egreso.descripcion}</td>
                                        <td>{egreso.origen}</td>
                                        <td>{formatoMonto(egreso.monto)}</td>
                                        <td>
                                            <a style={{cursor: "pointer", marginRight: 2, color: "#0d6efd"}} onClick={() => handleShowEdit(egreso)}><AiFillEdit/></a>
                                            <a style={{cursor: "pointer", marginRight: 2, color: "#dc3545"}} onClick={() => handleShowDelete(egreso)}><AiFillDelete/></a>
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
                        <Pagination>
                            {[...Array(Math.ceil(egresos.length / pageSize)).keys()].map((page) => (
                                <Pagination.Item
                                    key={page + 1}
                                    active={page + 1 === currentPage}
                                    onClick={() => handlePageChange(page + 1)}
                                >
                                    {page + 1}
                                </Pagination.Item>
                            ))}
                        </Pagination>
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
        </>
    );
}

export default Egresos;