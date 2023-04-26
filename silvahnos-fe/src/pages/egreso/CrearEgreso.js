import axios from 'axios';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { useState } from 'react';

function CrearEgreso() {
    const [validated, setValidated] = useState(false);

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

    const handleSumbit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            createEgreso();
            setValidated(true);
        }
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

    let montoValido = false;

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Crear Egreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form noValidate validated={validated} onSubmit={handleSumbit}>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente"
                                required
                                isValid={255 > egreso.patente.length && egreso.patente.length > 0}
                                isInvalid={egreso.patente.length > 255 || egreso.patente.length === 0}
                                type="text" placeholder="Ingrese patente" onChange={handleChange}
                            />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una patente valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEgreso">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" required
                                isValid={1000000000 > egreso.monto && egreso.monto > 0}
                                isInvalid={egreso.monto <= 0 || egreso.monto > 1000000000}
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
                                isValid={255 > egreso.descripcion.length && egreso.descripcion.length > 0} 
                                isInvalid={egreso.descripcion.length > 255 || egreso.descripcion.length === 0}
                                as="textarea" row={3} placeholder="Ingrese descripción" onChange={handleChange} />
                                <Form.Control.Feedback type="invalid">
                                    Ingrese una descripción valida
                                </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                        <Button type="submit">Guardar</Button>
                    </Form>
                </Col>
            </Row>
        </Container >
    );
}

export default CrearEgreso;