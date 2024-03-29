import React, { useEffect, useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { Card, Button, ListGroup, Modal, Form } from 'react-bootstrap';
import Cookies from 'js-cookie';
import Alerta from '../../components/Alerta';

const Parametro = () => {
    const [parametro, setParametro] = useState([]);
    const [parametroActual, setParametroActual] = useState('');

    const [showModal, setShowModal] = useState(false);
    const [nuevoValor, setNuevoValor] = useState('');

    const getParametro = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/Parametros';
            const response = await axios.get(url,config);
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
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/Parametros/'+nuevoValor;
            const response = await axios.post(url,null,config);
            if (response.status === 200) {
                setParametroActual(nuevoValor);
                setShowModal(false); 
                Alerta.fire({
                    icon: 'success',
                    title: 'Parámetro actualizado exitosamente'
                });
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

    return (
        <div style={{width: "100%"}}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Días previos para notificación de factura por vencer</Card.Title>
                    <br></br>
                    <ListGroup>
                        <ListGroup.Item style={{ fontWeight: "bold" }}>Valor del parámetro actual: {parametroActual} días</ListGroup.Item>
                    </ListGroup>
                    <br></br>
                    <div>
                        <Button variant='outline-primary' onClick={() => setShowModal(true)}>Cambiar parámetro</Button>
                    </div>
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
