import axios from 'axios';
import { useEffect, useState } from 'react';
import { Col, Container, Row, Table } from 'react-bootstrap';

const Facturas = () => {
    const [facturas, setFacturas] = useState([]);

    const getFacturas = async () => {
        try {
            let url = 'http://localhost:8090/facturas';
            const response = await axios.get(url);
            if (response.status === 200) {
                setFacturas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getFacturas();
    }, []);

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Facturas</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Table striped hover>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha emision</th>
                                <th>Fecha vencimiento</th>
                                <th>Fecha pago</th>
                                <th>Observacion</th>
                                <th>Numero factura</th>
                            </tr>
                        </thead>
                        <tbody>
                            {facturas.map((factura) => (
                                <tr key={factura.id}>
                                    <td>{factura.id}</td>
                                    <td>{factura.fecha_emision}</td>
                                    <td>{factura.fecha_vencimiento}</td>
                                    <td>{factura.fecha_pago}</td>
                                    <td>{factura.observaciones}</td>
                                    <td>{factura.numero_factura}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
};

export default Facturas;