import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Row, Table } from 'react-bootstrap';

const Egresos = () => {
    const [egresos, setEgresos] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

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

    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
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
        monto: '',
        descripcion: '',
    }

    let fechaAcual = new Date();
    let anio = fechaAcual.getFullYear();
    let mes = fechaAcual.getMonth() + 1;

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

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    let total = 0;
    egresos.forEach((egreso) => {
        total += egreso.monto;
        console.log(total);
    });

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

    const [validated, setValidated] = useState(false);
    console.log(validated);

    useEffect(() => {
        getEgresos();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Egresos</h1></Col>
                </Row>
                <Row>
                    <Col>
                        <Table responsive striped >
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Descripción</th>
                                    <th>Patente</th>
                                    <th>Monto</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {egresos.map((egreso) => (
                                    <tr key={egreso.id}>
                                        <td>{formatearFecha(egreso.fecha_creacion)}</td>
                                        <td>{egreso.descripcion}</td>
                                        <td>{egreso.patente}</td>
                                        <td>{formatoMonto(egreso.monto)}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(egreso)} style={{ marginRight: 2 }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(egreso)}>Eliminar</Button>
                                        </td>
                                    </tr>

                                ))}
                            </tbody>
                        </Table>
                        <h1 style={{ textAlign: "center" }}>Total: {formatoMonto(total)}</h1>
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
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente"
                                required
                                isValid={255 > editedItem.patente.length && editedItem.patente.length > 0}
                                isInvalid={editedItem.patente.length > 255 || editedItem.patente.length === 0}
                                type="text" placeholder="Ingrese patente" onChange={handleChange}
                            />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una patente valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" required
                                isValid={1000000000 > editedItem.monto && editedItem.monto > 0}
                                isInvalid={editedItem.monto <= 0 || editedItem.monto > 1000000000}
                                min={1}
                                max={1000000000}
                                type="number" placeholder="Ingrese monto" onChange={handleChange} />
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
                                as="textarea" row={3} placeholder="Ingrese descripción" onChange={handleChange} />
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