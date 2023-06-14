import React, { useEffect, useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { Card, Button, ListGroup, Modal, Form } from 'react-bootstrap';

const Parametro = () => {
    const [parametro, setParametro] = useState([]);
    const [parametroActual, setParametroActual] = useState('');

    const [showModal, setShowModal] = useState(false);
    const [nuevoValor, setNuevoValor] = useState('');

    const getParametro = async () => {
        try {
            let url = 'http://' + urlweb + '/Parametros';
            const response = await axios.get(url);
            if (response.status === 200) {
                setParametro(response.data);
                setParametroActual(response.data[0].valor);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleSubmit = async () => {
        try {
            let url = 'http://' + urlweb + '/Parametros/'+nuevoValor;
            const response = await axios.post(url);
            if (response.status === 200) {
                setParametroActual(nuevoValor);
                setShowModal(false); 
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleChange = (event) => {
        setNuevoValor(event.target.value);
    };

    useEffect(() => {
        getParametro();
    }, []);

    console.log(parametro);

    return (
        <div>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Días previos para notificación de factura por vencer</Card.Title>
                    <br></br>
                    <ListGroup>
                        <ListGroup.Item style={{ fontWeight: "bold" }}>Valor del parámetro actual: {parametroActual} días</ListGroup.Item>
                    </ListGroup>
                    <br></br>
                    <Button onClick={() => setShowModal(true)}>Cambiar parámetro</Button>
                </Card.Body>
            </Card>

            <Modal show={showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar parámetro</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group controlId="nuevoValor">
                            <Form.Label>Nuevo valor</Form.Label>
                            <Form.Control type="text" value={nuevoValor} onChange={handleChange} />
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

export default Parametro;
