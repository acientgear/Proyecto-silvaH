import axios from 'axios';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { useState } from 'react';

function CrearEgreso() {
    const [egreso, setEgreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        monto: '',
        patente: '',
        descripcion: '',
    });

    const handleChange = (e) => {
        setEgreso({
            ...egreso,
            [e.target.name]: e.target.value
        });
    };

    const createEgreso = async () => {
        try {
            let url = "http://localhost:8090/egresos";
            let response = await axios.post(url, egreso);
            if (response.status === 200) {
                window.location.href = "/egresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Crear Egreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente" type="text" placeholder="Ingrese patente" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" type="text" placeholder="Ingrese monto" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Descripcion</Form.Label>
                            <Form.Control as="textarea" row={3} name="descripcion" type="textarea" placeholder="Ingrese descripción" onChange={handleChange} />
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                    <Button variant='success' onClick={createEgreso}>Guardar</Button>
                </Col>
            </Row>
        </Container>
    );
}

export default CrearEgreso;