import axios from 'axios';
import { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import CategoriasIngreso from '../../components/data/CategoriasIngreso';

const CrearIngreso = () => {
    const [validated, setValidated] = useState(false);

    const [ingreso, setIngreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        monto: 0,
        descripcion: '',
        origen: '',
    });

    const handleChange = (e) => {
        setIngreso({
            ...ingreso,
            [e.target.name]: e.target.value,
        });
        setValidated(false);
    };

    const handleSumbit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            createIngreso();
            setValidated(true);
        }
    };


    const createIngreso = async () => {
        try {
            let url = "http://localhost:8090/ingresos";
            let response = await axios.post(url, ingreso);
            if (response.status === 200) {
                window.location.href = "/ingresos";
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Crear Ingreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form noValidate validated={validated} onSubmit={handleSumbit}>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control name="patente"
                                required
                                isValid={6 > ingreso.patente.length && ingreso.patente.length > 0}
                                isInvalid={ingreso.patente.length > 6 || ingreso.patente.length === 0}
                                type="text" placeholder="Ingrese la patente" onChange={handleChange}
                            />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una patente valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Origen</Form.Label>
                            <Form.Select name="origen"
                                required
                                aria-label="select"
                                placeholder="Ingrese de donde viene" 
                                onChange={handleChange}
                            >
                                {CategoriasIngreso.map((categoria) => (
                                    <option key={categoria.id} value={categoria.nombre}>{categoria.nombre}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Monto</Form.Label>
                            <Form.Control name="monto" required
                                isValid={1000000000 > ingreso.monto && ingreso.monto > 0}
                                isInvalid={ingreso.monto <= 0 || ingreso.monto > 1000000000}
                                min={1}
                                max={1000000000}
                                type="number" placeholder="Ingrese monto" onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                Ingrese un monto entre $1 y $1.000.000.000
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control name="descripcion"
                                required
                                isValid={255 > ingreso.descripcion.length && ingreso.descripcion.length > 0}
                                isInvalid={ingreso.descripcion.length > 255 || ingreso.descripcion.length === 0}
                                as="textarea" row={3} placeholder="Ingrese descripción" onChange={handleChange} />
                            <Form.Control.Feedback type="invalid">
                                Ingrese una descripción valida
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                        <Button variant='success' type='sumbit'>Guardar</Button>
                    </Form>

                </Col>
            </Row>
        </Container>
    );
};

export default CrearIngreso;