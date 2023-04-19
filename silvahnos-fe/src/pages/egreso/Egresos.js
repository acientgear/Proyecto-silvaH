import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Row, Table } from 'react-bootstrap';

const Egresos = () => {
    const [egresos, setEgresos] = useState([]);

    const getEgresos = async () => {
        try {
            let url = "http://localhost:8090/egresos";
            let response = await axios.get(url);
            if (response.status === 200) {
                setEgresos(response.data);
            }
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getEgresos();
    }, []);

    return (
        <Container>
            <Row>
                <Col><h1>Egresos</h1></Col>
                <Col><Button variant='primary' href='/crearEgreso' style={{ marginRight: 3 }}>Registrar egreso</Button></Col>
            </Row>
            <Row>
                <Col>
                    <Table striped hover>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha</th>
                                <th>Monto</th>
                                <th>Motivo</th>
                                <th>Descripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            {egresos.map((egreso) => (
                                <tr key={egreso.id}>
                                    <td>{egreso.id}</td>
                                    <td>{egreso.fechaCreacion}</td>
                                    <td>{egreso.monto}</td>
                                    <td>{egreso.patente}</td>
                                    <td>{egreso.descripcion}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default Egresos;