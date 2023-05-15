import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Pagination, Row, Table } from 'react-bootstrap';
import InputMonth from '../../components/InputMonth';
import CategoriasIngreso from '../../components/data/CategoriasIngreso';

const Ingresos = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [ingresos, setIngresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handlePageChange = (page) => {
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
        setShowEdit(false)
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

    const handleSumbit = (e) => {
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
            let url = 'http://localhost:8090/ingresos';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseEdit();
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

    const handleChangeMes = (e) => {
        setMes(e.target.value);
    };

    const handleChangeAnio = (e) => {
        setAnio(e.target.value);
    };

    const deleteIngreso = async () => {
        try {
            let url = 'http://localhost:8090/ingresos';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseDelete();
                getIngresos();
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
        patente: '',
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
        patente: '',
        origen: '',
        monto: '',
        descripcion: '',
    }

    const getIngresos = async () => {
        try {
            let url = 'http://localhost:8090/ingresos/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setIngresos(response.data);
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
    ingresos.forEach(ingreso => {
        total += ingreso.monto;
    });

    useEffect(() => {
        getIngresos();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Ingresos</h1></Col>
                    <Col className='d-flex align-items-center'>
                        <InputMonth 
                            mes={mes} 
                            anio={anio}
                            onChangeAnio={handleChangeAnio}
                            onChangeMes={handleChangeMes}
                            get={getIngresos}
                        />
                    </Col>
                    <Col className='d-flex align-items-center justify-content-end'><Button href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold" }}>Registrar un ingreso</Button></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped responsive="sm" hover>
                            <thead>
                                <tr>
                                    <th style={{ width: '150px' }}>Fecha</th>
                                    <th style={{ width: '150px' }}>Descripción</th>
                                    <th style={{ width: '150px' }}>Origen</th>
                                    <th style={{ width: '150px' }}>Patente</th>
                                    <th style={{ width: '150px' }}>Monto</th>
                                    <th style={{ width: '150px' }}>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((ingreso) => (
                                    <tr key={ingreso.id}>
                                        <td>{formatearFecha(ingreso.fecha_creacion)}</td>
                                        <td>{ingreso.descripcion}</td>
                                        <td>{ingreso.origen}</td>
                                        <td>{ingreso.patente}</td>
                                        <td>{formatoMonto(ingreso.monto)}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(ingreso)} style={{ marginRight: 2, width: "88px" }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(ingreso)} style={{ width: "88px" }}>Eliminar</Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody> 
                            <tfoot>
                                <tr style={{background:"#E6F4DD"}}>
                                    <td>Total</td>
                                    <td></td>
                                    <td></td>
                                    <td>{formatoMonto(total)}</td>
                                    <td></td>
                                </tr>
                            </tfoot>
                        </Table>
                        <Pagination>
                            {[...Array(Math.ceil(ingresos.length / pageSize)).keys()].map((page) => (
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
                    <Modal.Title>Editar Ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={validated} onSubmit={handleSumbit}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente"
                                required
                                isValid={6 > editedItem.patente.length && editedItem.patente.length > 0}
                                isInvalid={editedItem.patente.length > 6 || editedItem.patente.length === 0}
                                type="text" placeholder="Ingrese la patente" value={editedItem.patente} onChange={handleChange}
                            />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una patente valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Origen</Form.Label>
                            <Form.Select name="origen"
                                required
                                aria-label="select"
                                placeholder="Ingrese de donde viene" 
                                onChange={handleChange}
                                value={editedItem.origen}
                            >
                                {CategoriasIngreso.map((categoria) => (
                                    <option key={categoria.id} value={categoria.nombre}>{categoria.nombre}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className='mb-3' controlId='formMonto'>
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto"
                                required
                                isValid={1000000000 > editedItem.monto && editedItem.monto > 0}
                                isInvalid={editedItem.monto <= 0 || editedItem.monto > 1000000000}
                                min={1}
                                max={1000000000}
                                type='number' value={editedItem.monto} onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                Ingrese un monto entre $1 y $1.000.000.000
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className='mb-3' controlId='formDescripcion'>
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control name="descripcion"
                                required
                                isValid={255 > editedItem.descripcion.length && editedItem.descripcion.length > 0}
                                isInvalid={editedItem.descripcion.length > 255 || editedItem.descripcion.length === 0}
                                as='textarea' row={3} value={editedItem.descripcion} onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una descripción valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <hr></hr>
                        <div style={{ display: "flex", justifyContent: "end" }}>
                            <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleCloseEdit}>Cerrar</Button>
                            <Button variant='primary' type='sumbit'>Guardar</Button>
                        </div>
                    </Form>
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
        </>
    );
};

export default Ingresos;