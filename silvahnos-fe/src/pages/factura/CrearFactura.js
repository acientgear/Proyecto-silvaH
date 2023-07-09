import axios from 'axios';
import { useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import FormFactura from '../../components/FormFactura';
import urlweb from '../../config/config';

const CrearFactura = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 
    const [validated, setValidated] = useState(false);

    const [factura, setFactura] = useState({
        id: null,
        numero_factura: 0,
        fecha_emision: '',
        fecha_vencimiento: '',
        fecha_pago: '',
        monto: 0,
        observaciones: '',
        borrado: false,
        estado: {
            id: 1
        },
        empresa: {
            id: 0
        },
        fecha_creacion: '',
        fecha_modificacion: '',
        fecha_borrado: ''
    });

    const handleChange = (e) => {
        setFactura({
            ...factura,
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
            console.log(factura)
            createFactura();
            setValidated(true);
        }
    };

    const createFactura = async () => {
        try {
            let url = "http://"+urlweb+"/facturas";
            let response = await axios.post(url, factura,config);
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
                    <FormFactura
                        factura={factura}
                        setFactura={setFactura}
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

export default CrearFactura;