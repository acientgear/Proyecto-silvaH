import axios from 'axios';
import { useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import FormFactura from '../../components/FormFactura';
import urlweb from '../../config/config';

const CrearFactura = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 

    const facturaDefault = {
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
    }

    const createFactura = async (factura) => {
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
        <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
            <Row>
                <Col>
                    <h1>Crear Factura</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormFactura
                        factura={facturaDefault}
                        postFactura={createFactura}
                        modal={false}
                    />
                </Col>
            </Row>
        </Container>
    );
};

export default CrearFactura;