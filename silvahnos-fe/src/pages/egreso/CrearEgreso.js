import axios from 'axios';
import { Col, Container, Row } from 'react-bootstrap';
import { useState } from 'react';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';

function CrearEgreso() {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 
    const [validated, setValidated] = useState(false);

    const [egreso, setEgreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        monto: '',
        motivo: {
            id: 0
        },
        descripcion: ''
    });

    const handleChange = (e) => {
        setEgreso({
            ...egreso,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
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
            let url = "http://"+urlweb+"/egresos";
            let response = await axios.post(url, egreso,config);
            if (response.status === 200) {                
                window.location.href = "/egresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
            <Row>
                <Col>
                    <h1>Crear Egreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormEgreso 
                        egreso={egreso}
                        setEgreso={setEgreso}
                        validated={validated}
                        modal={false}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                    />
                </Col>
            </Row>
        </Container >
    );
}

export default CrearEgreso;