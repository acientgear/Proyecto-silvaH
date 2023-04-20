import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Row, Table } from 'react-bootstrap';

const Egresos = () => {
    const [egresos, setEgresos] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

    const handleCloseDelete = () =>{
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (egreso) =>{
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

    const getEgresos = async () => {
        try {
            let url = 'http://localhost:8090/egresos';
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

    useEffect(() => {
        getEgresos();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Egresos</h1></Col>
                    <Col><Button variant='primary' href='/crearEgreso' style={{ marginRight: 3 }}>Registrar egreso</Button></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped hover>
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Monto</th>
                                    <th>Motivo</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {egresos.map((egreso) => (
                                    <tr key={egreso.id}>
                                        <td>{formatearFecha(egreso.fecha_creacion)}</td>
                                        <td>{egreso.monto}</td>
                                        <td>{egreso.patente}</td>
                                        <td>{egreso.descripcion}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(egreso)} style={{ marginRight: 2 }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(egreso)}>Eliminar</Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className='mb-3' controlId='formPatente'>
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente" type='text' value={editedItem.patente} onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className='mb-3' controlId='formMonto'>
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" type='number' value={editedItem.monto} onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className='mb-3' controlId='formDescripcion'>
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control name="descripcion" as='textarea' row={3} value={editedItem.descripcion} onChange={handleChange}/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseEdit}>Cerrar</Button>
                    <Button variant='primary' onClick={updateEgreso}>Guardar</Button>
                </Modal.Footer>
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