import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Row, Table } from 'react-bootstrap';

const Ingresos = () => {
    const [ingresos, setIngresos] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

    const handleCloseDelete = () =>{
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (ingreso) =>{
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
    };

    const handleChange = (e) => {
        setEditedItem({
            ...editedItem,
            [e.target.name]: e.target.value,
        });
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



    const getIngresos = async () => {
        try {
            let url = 'http://localhost:8090/ingresos';
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

    useEffect(() => {
        getIngresos();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col>
                        <h1>Ingresos</h1>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped hover>
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Monto</th>
                                    <th>Origen</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {ingresos.map((ingreso) => (
                                    <tr key={ingreso.id}>
                                        <td>{formatearFecha(ingreso.fecha_creacion)}</td>
                                        <td>{ingreso.monto}</td>
                                        <td>{ingreso.patente}</td>
                                        <td>{ingreso.descripcion}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(ingreso)} style={{ marginRight: 2 }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(ingreso)}>Eliminar</Button>
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
                    <Modal.Title>Editar Ingreso</Modal.Title>
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
                    <Button variant='primary' onClick={updateIngreso}>Guardar</Button>
                </Modal.Footer>
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