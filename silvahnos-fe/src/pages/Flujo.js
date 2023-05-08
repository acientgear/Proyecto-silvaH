import LineChart from './GraficoFlujo';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { ListGroup, ListGroupItem, Table, Col, Row, Container, Accordion,Badge } from 'react-bootstrap';

const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
};

const data = [
    { month: 'Enero', ingresos: 18000000, egresos: 26168454, saldoCuenta: -8168454 },
    { month: 'Febrero', ingresos: 20000000, egresos: 18000000, saldoCuenta: 0 },
    { month: 'Marzo', ingresos: 22268362, egresos: 20690022, saldoCuenta: 0 },
    { month: 'Abril', ingresos: 0, egresos: 0, saldoCuenta: 0 },
    { month: 'Mayo', ingresos: 0, egresos: 0, saldoCuenta: 0 }
];

const ingresos = [
    { tipo: 'Astara', monto: 100000 },
    { tipo: 'Taller', monto: 200000 }
];

const egresos = [
    { tipo: 'Arriendo', monto: 100000 },
    { tipo: 'Astara', monto: 200000 },
    { tipo: 'Repuestos', monto: 100000 },
    { tipo: 'Sueldos', monto: 100000 },
    { tipo: 'Taller', monto: 200000 },
    { tipo: 'Otros', monto: 100000 }
];



const Flujo = () => {
    const [registros, setRegistros] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

    const getRegistros = async () => {
        try {
            let url = 'http://localhost:8090/egresos/ingresos/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setRegistros(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    console.log("registros: ", registros);

    const [flujos, setFlujos] = useState(data);

    const ingresosTotales = flujos.reduce((total, mes) => total + mes.ingresos, 0);
    const egresosTotales = flujos.reduce((total, mes) => total + mes.egresos, 0);
    const saldoCuenta = ingresosTotales - egresosTotales;

    useEffect(() => {
        getRegistros();
    }, []);

    return (
        <Container>
            <Row>
                <Col><h1>Flujo de caja</h1></Col>
            </Row>
            <Row >
                <Col md="auto">
                    <Table striped hover>
                        <thead>
                            <tr>
                                <th></th>
                                <th>Mes actual</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style={{ fontWeight: "bold" }}>Ingresos</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Astara</td>
                                <td>$ 100.000</td>
                            </tr>
                            <tr>
                                <td>Taller</td>
                                <td>$ 200.000</td>
                            </tr>
                            <tr>
                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                <td>$ 300.000</td>
                            </tr>
                            <tr>
                                <td style={{ fontWeight: "bold" }}>Egresos</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Arriendo</td>
                                <td>$ 100.000</td>
                            </tr>
                            <tr>
                                <td>Astara</td>
                                <td>$ 200.000</td>
                            </tr>
                            <tr>
                                <td>Repuestos</td>
                                <td>$ 100.000</td>
                            </tr>
                            <tr>
                                <td>Sueldos</td>
                                <td>$ 100.000</td>
                            </tr>
                            <tr>
                                <td>Taller</td>
                                <td>$ 200.000</td>
                            </tr>
                            <tr>
                                <td>Otros</td>
                                <td>$ 100.000</td>
                            </tr>
                            <tr>
                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                <td>$ 800.000</td>
                            </tr>
                            <tr>
                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                <td>$ 500.000</td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
                <Col md="auto">
                    <Row>
                        <Table striped hover>
                            <thead>
                                <tr style={{ background: "#ACB1D6" }}>
                                    <th></th>
                                    <th style={{ width: '100px' }}>Enero</th>
                                    <th style={{ width: '100px' }}>Febrero</th>
                                    <th style={{ width: '100px' }}>Marzo</th>
                                    <th style={{ width: '100px' }}>Abril</th>
                                    <th style={{ width: '100px' }}>Mayo</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr style={{ background: "#E6F4DD" }}>
                                    <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                    {flujos.map((mes, index) => (
                                        <td key={index}>{formatoMonto(mes.ingresos)}</td>
                                    ))}
                                </tr>
                                <tr style={{ background: "#FBE6DD" }}>
                                    <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                    {flujos.map((mes, index) => (
                                        <td key={index}>{formatoMonto(mes.egresos)}</td>
                                    ))}
                                </tr>
                                <tr style={{ background: "#B9F3E4" }}>
                                    <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                    {flujos.map((mes, index) => {
                                        const saldoMesAnterior = index > 0 ? flujos[index - 1].saldoCuenta : 0;
                                        flujos[index].saldoCuenta = saldoMesAnterior + mes.ingresos - mes.egresos;
                                        return <td key={index}>{formatoMonto(saldoMesAnterior + mes.ingresos - mes.egresos)}</td>
                                    })}
                                </tr>
                            </tbody>
                        </Table>
                    </Row>
                    <Row>
                        <div style={{ width: "500px", height: "250px", margin: "auto", justifyContent: "center" }}>
                            <LineChart />
                        </div>
                    </Row>
                </Col>
                <Col>
                    <h4 style={{ textAlign: "center" }}>Detalle flujo de este mes</h4>
                    <Accordion alwaysOpen>
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>Ingresos: $ 300.000</Accordion.Header>
                            <Accordion.Body>
                                <ListGroup>
                                    {ingresos.map((ingreso) => (
                                        <div key={ingreso.tipo}>
                                            <ListGroupItem >{ingreso.tipo}: {ingreso.monto}</ListGroupItem>
                                        </div>
                                    ))}
                                </ListGroup>
                            </Accordion.Body>
                        </Accordion.Item>
                        <Accordion.Item eventKey="1">
                            <Accordion.Header>Egresos: $ 800.000</Accordion.Header>
                            <Accordion.Body>
                                <ListGroup>
                                    {egresos.map((egreso) => (
                                        <div key={egreso.tipo}>
                                            <ListGroupItem>{egreso.tipo}: {egreso.monto}</ListGroupItem>
                                        </div>
                                    ))}
                                </ListGroup>
                            </Accordion.Body>
                        </Accordion.Item>
                        <ListGroup>
                            <ListGroupItem>Saldo cuenta: $ -500.000</ListGroupItem>
                        </ListGroup>
                    </Accordion>
                </Col>
            </Row>
            <Row>
                <Col sm={4} style={{maxHeight: '500px', overflowY: 'scroll', scrollbarWidth: 'thin', scrollbarColor: 'gray lightgray' }}>
                    <h4 style={{ textAlign:"center"}}>Ingresos y egresos de este mes</h4>
                    {registros.map((registro) => (
                        <div key={registro.id}>
                            <ListGroup.Item as="li" className="d-flex justify-content-between align-items-start">
                                <div className="ms-2 me-auto">
                                    <div className="fw-bold">{registro.fecha}</div>
                                    {registro.descripcion}
                                </div>
                                <Badge bg="warning" pill style={{ color: "black" }}>
                                    {registro.monto}
                                </Badge>
                            </ListGroup.Item>
                            <br></br>
                        </div>
                    ))}
                </Col>
            </Row>
        </Container>
    );
};

export default Flujo;
