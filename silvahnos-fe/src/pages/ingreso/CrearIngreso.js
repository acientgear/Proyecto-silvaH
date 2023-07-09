import axios from 'axios';
import { useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import FormIngreso from '../../components/FormIngreso';
import urlweb from '../../config/config';

const CrearIngreso = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };  
    const [validated, setValidated] = useState(false);

    const [ingreso, setIngreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        monto: 0,
        motivo: {
            id: 0
        },
        descripcion: '',
    });

    const handleChange = (e) => {
        setIngreso({
            ...ingreso,
            [e.target.name]: e.target.value,
        });
        setValidated(false);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            console.log(ingreso)
            createIngreso();
            setValidated(true);
        }
    };

    const createIngreso = async () => {
        try {
            let url = "http://"+urlweb+"/ingresos";
            let response = await axios.post(url, ingreso,config);
            if (response.status === 200) {
                window.location.href = "/ingresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Crear Ingreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormIngreso
                        ingreso={ingreso}
                        setIngreso={setIngreso}
                        validated={validated}
                        modal={false}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                    />
                </Col>
            </Row>
        </Container>
    );
};

export default CrearIngreso;