import axios from 'axios';
import { useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import FormFactura from '../../components/FormFactura';
import urlweb from '../../config/config';

const CrearFactura = () => {
    const [validated, setValidated] = useState(false);
    const [facturaId, setFacturaId] = useState(0);

    const [factura, setFactura] = useState({
        id: null,
        borrado: false,
        fecha_creacion: '',
        fecha_modificacion: '',
        fecha_borrado: '',
        observaciones: '',
        monto: 0,
        estado: {
            id: 1
        },
        empresa: {
            id: 0
        },
        fecha_emision: '',
        fecha_vencimiento: '',
        fecha_pago: '',
        numero_factura: 0,
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