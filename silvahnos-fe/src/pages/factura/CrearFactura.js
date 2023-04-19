import axios from 'axios';
import { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

const CrearFactura = () => {
    const [factura, setFactura] = useState({
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

    const handleChange = (e) => {
        setFactura({
            ...factura,
            [e.target.name]: e.target.value,
        });
    };

    const createFactura = async () => {
        try {
            let url = "http://localhost:8090/facturas";
            let response = await axios.post(url, factura);
            if (response.status === 200) {
                window.location.href = "/facturas";
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    return(
        <Container>
            <Row>
                <Col>
                    <h1>Crear Factura</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Numero factura</Form.Label>
                            <Form.Control name="numero_factura" type="number" placeholder="Ingrese numero factura" onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Fecha emision</Form.Label>
                            <Form.Control name="fecha_emision" type="date" placeholder="Ingrese fecha emision" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Fecha vencimiento</Form.Label>
                            <Form.Control name="fecha_vencimiento" type="date" placeholder="Ingrese fecha vencimiento" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Fecha pago</Form.Label>
                            <Form.Control name="fecha_pago" type="date" placeholder="Ingrese fecha pago" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>observaciones</Form.Label>
                            <Form.Control name="observaciones" as="textarea" row={3} placeholder="Ingrese observaciones/comentarios" onChange={handleChange}/>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Button variant='primary' href='/' style={{marginRight: 2}}>Atras</Button>
                    <Button variant='success' onClick={createFactura}>Guardar</Button>
                </Col>
            </Row>
        </Container>
    );
};

export default CrearFactura;