import axios from 'axios';
import { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

const CrearIngreso = () => {
    const [ingreso, setIngreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        monto: '',
        descripcion: '',
    });

    const handleChange = (e) => {
        setIngreso({
            ...ingreso,
            [e.target.name]: e.target.value,
        });
    };

    const createIngreso = async () => {
        try {
            let url = "http://localhost:8090/ingresos";
            let response = await axios.post(url, ingreso);
            if (response.status === 200) {
                window.location.href = "/";
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    return(
        <Container>
            <Row>
                <Col>
                    <h1>Crear Ingreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente" type="text" placeholder="Ingrese patente" onChange={handleChange} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" type="number" placeholder="Ingrese monto" onChange={handleChange}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control name="descripcion" as="textarea" row={3} placeholder="Ingrese descripción" onChange={handleChange}/>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Button variant='primary' href='/' style={{marginRight: 2}}>Atras</Button>
                    <Button variant='success' onClick={createIngreso}>Guardar</Button>
                </Col>
            </Row>
        </Container>
    );
};

export default CrearIngreso;