import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Modal, Row, Table } from 'react-bootstrap';

const Facturas = () => {
    const [facturas, setFacturas] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

    const handleCloseDelete = () =>{
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (factura) =>{
        setEditedItem(factura);
        setShowDelete(true);
    } 

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false)
    };

    const handleShowEdit = (factura) => {
        setEditedItem(factura);
        setShowEdit(true);
    };

    const handleChange = (e) => {
        setEditedItem({
            ...editedItem,
            [e.target.name]: e.target.value,
        });
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteFactura();
    };

    const getFacturas = async () => {
        try {
            let url = 'http://localhost:8090/facturas';
            const response = await axios.get(url);
            if (response.status === 200) {
                setFacturas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const updateFactura = async () => {
        try {
            let url = 'http://localhost:8090/facturas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseEdit();
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const deleteFactura = async () => {
        try {
            let url = 'http://localhost:8090/facturas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseDelete();
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);  
        }
    };

    const formatearFecha = (fecha) => {
        if(fecha===null){
            return "---"
        }
        let fechaC = fecha.split('T')[0];
        fechaC = fechaC.split('-');
        return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
    };


    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        observaciones: '',
        fecha_emision: null,
        fecha_vencimiento: null,
        fecha_pago: null,
        numero_factura: null,
    });

    const defaultItem = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        observaciones: '',
        fecha_emision: null,
        fecha_vencimiento: null,
        fecha_pago: null,
        numero_factura: null,
    }

    useEffect(() => {
        getFacturas();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Facturas</h1></Col>
                    <Col><Button variant='primary' href='/crearFactura' style={{ marginRight: 3 }}>Registrar Factura</Button></Col>    
                </Row>
                <Row>
                    <Col>
                        <Table striped hover>
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Numero factura</th>
                                    <th>Fecha emision</th>
                                    <th>Fecha vencimiento</th>
                                    <th>Fecha pago</th>
                                    <th>Observacion</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {facturas.map((factura) => (
                                    <tr key={factura.id}>
                                        <td>{factura.id}</td>
                                        <td>{factura.numero_factura}</td>
                                        <td>{formatearFecha(factura.fecha_emision)}</td>
                                        <td>{formatearFecha(factura.fecha_vencimiento)}</td>
                                        <td>{formatearFecha(factura.fecha_pago)}</td>
                                        <td>{factura.observaciones}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(factura)} style={{ marginRight: 2 }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(factura)}>Eliminar</Button>
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
                <Modal.Title>Editar </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className='mb-3' controlId='formNumeroFactura'>
                        <Form.Label>Numero factura</Form.Label>
                        <Form.Control name="numero_factura" type='number' value={editedItem.numero_factura} onChange={handleChange}/>
                    </Form.Group>
                    <Form.Group className='mb-3' controlId='formFechaEmision'>
                        <Form.Label>Fecha emision</Form.Label>
                        <Form.Control name="fecha_emision" type='date' value={editedItem.fecha_emision} onChange={handleChange}/>
                    </Form.Group>
                    <Form.Group className='mb-3' controlId='formFechaVencimiento'>
                        <Form.Label>fecha vencimiento</Form.Label>
                        <Form.Control name="fecha_vencimiento" type='date' value={editedItem.fecha_vencimiento} onChange={handleChange}/>
                    </Form.Group>
                    <Form.Group className='mb-3' controlId='formFechaPago'>
                        <Form.Label>Fecha pago</Form.Label>
                        <Form.Control name="fecha_pago" type='date' value={editedItem.fecha_pago} onChange={handleChange}/>
                    </Form.Group>
                    <Form.Group className='mb-3' controlId='formobservaciones'>
                        <Form.Label>Observaciones</Form.Label>
                        <Form.Control name="observaciones" as="textarea" row={3} value={editedItem.observaciones} onChange={handleChange}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant='secondary' onClick={handleCloseEdit}>Cerrar</Button>
                <Button variant='primary' onClick={updateFactura}>Guardar</Button>
            </Modal.Footer>
        </Modal>

        {/*Modal para eliminar*/}
        <Modal show={showDelete} onHide={handleCloseDelete}>
            <Modal.Header closeButton>
                <Modal.Title>Eliminar Factura</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>¿Está seguro que desea eliminar La factura?</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
            </Modal.Footer>
        </Modal>
    </>
    );
};

export default Facturas;