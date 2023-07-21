import React, { useEffect, useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { Card, Button, ListGroup, Modal, Form } from 'react-bootstrap';
import Cookies from 'js-cookie';

const Correo = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    }; 
    const [correo, setCorreo] = useState([]);
    const [correoActual, setCorreoActual] = useState('');

    const [showModal, setShowModal] = useState(false);
    const [nuevoCorreo, setNuevoCorreo] = useState('');

    const getCorreo = async () => {
        try {
            let url = 'http://' + urlweb + '/Correos';
            const response = await axios.get(url,config);
            if (response.status === 200) {
                setCorreo(response.data);
                setCorreoActual(response.data[0].direccion);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleSubmit = async () => {
        try {
            let url = 'http://' + urlweb + '/Correos/'+nuevoCorreo;
            const response = await axios.post(url,null,config);
            if (response.status === 200) {
                setCorreoActual(nuevoCorreo);
                setShowModal(false); 
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleChange = (event) => {
        setNuevoCorreo(event.target.value);
    };

    useEffect(() => {
        getCorreo();
    }, []);

    return (
        <div style={{width: "100%"}}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Correo destinatario de notificación</Card.Title>
                    <br></br>
                    <ListGroup>
                        <ListGroup.Item style={{ fontWeight: "bold" }}>Correo actual: {correoActual}</ListGroup.Item>
                    </ListGroup>
                    <br></br>
                    <div>
                        <Button variant='outline-primary' onClick={() => setShowModal(true)}>Actualizar correo</Button>
                    </div>
                </Card.Body>
            </Card>

            <Modal show={showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Actualizar correo</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="nuevoValor">
                            <Form.Label>Nuevo correo</Form.Label>
                            <Form.Control type="text" value={nuevoCorreo} onChange={handleChange} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowModal(false)}>Cancelar</Button>
                    <Button variant="primary" onClick={handleSubmit}>Guardar</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default Correo;
