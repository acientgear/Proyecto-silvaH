import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Row, Table, Pagination } from 'react-bootstrap';
import InputMonth from '../../components/InputMonth';
import CategoriasEgreso from '../../components/data/CategoriasEgreso';

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

    const handleSumbit = (e) => {
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
            let url = 'http://localhost:8090/egresos';
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
            let url = 'http://localhost:8090/egresos';
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

    const getEgresos = async () => {
        try {
            let url = 'http://localhost:8090/egresos/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setEgresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

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
    }, []);

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
                    <Col className='d-flex align-items-center justify-content-end'><Button href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar un egreso</Button></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped responsive="sm" hover>
                            <thead>
                                <tr>
                                    <th style={{ width: '150px' }}>Fecha</th>
                                    <th style={{ width: '150px' }}>Descripción</th>
                                    <th style={{ width: '150px' }}>Origen</th>
                                    <th style={{ width: '150px' }}>Monto</th>
                                    <th style={{ width: '150px' }}>Acciones</th>
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
                                            <Button variant='primary' onClick={() => handleShowEdit(egreso)} style={{ marginRight: 2, width: "88px" }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(egreso)} style={{ width: "88px" }}>Eliminar</Button>
                                        </td>
                                    </tr>

                                ))}
                            </tbody>
                            <tfoot>
                                <tr style={{background:"#FBE6DD"}}>
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
                    <Form noValidate validated={validated} onSubmit={handleSumbit}>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Origen</Form.Label>
                            <Form.Select
                                aria-label="select"
                                name="origen"
                                required
                                placeholder="Ingrese de donde viene el egreso"
                                onChange={handleChange}
                                value={editedItem.origen}
                            >
                                {CategoriasEgreso.map((categoria) => (
                                    <option key={categoria.id} value={categoria.nombre}>
                                        {categoria.nombre}
                                    </option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" required
                                isValid={1000000000 > editedItem.monto && editedItem.monto > 0}
                                isInvalid={editedItem.monto <= 0 || editedItem.monto > 1000000000}
                                min={1}
                                max={1000000000}
                                type="number" placeholder="Ingrese monto" 
                                value={editedItem.monto}
                                onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                El monto debe ser mayor a $ 0 y menor a $ 1.000.000.000
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control name="descripcion"
                                required
                                isValid={255 > editedItem.descripcion.length && editedItem.descripcion.length > 0}
                                isInvalid={editedItem.descripcion.length > 255 || editedItem.descripcion.length === 0}
                                as="textarea" row={3} placeholder="Ingrese descripción" value={editedItem.descripcion} onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una descripción valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant='secondary' onClick={handleCloseEdit}>Cerrar</Button>
                        <Button type="submit">Guardar</Button>
                    </Form>
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