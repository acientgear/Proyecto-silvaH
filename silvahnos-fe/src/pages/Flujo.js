import { Col, Row, Container, Table } from "react-bootstrap";
import LineChart from './GraficoFlujo';
import { useState } from 'react';

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

const Flujo = () => {

    const [flujos, setFlujos] = useState(data);

    const ingresosTotales = flujos.reduce((total, mes) => total + mes.ingresos, 0);
    const egresosTotales = flujos.reduce((total, mes) => total + mes.egresos, 0);
    const saldoCuenta = ingresosTotales - egresosTotales;

    return (
        <Container>
            <Row>
                <Col><h1>Flujo de caja</h1></Col>
            </Row>
            <Row className="justify-content-md-center">
                <Col md="auto">
                    <Table striped hover>
                        <thead>
                            <tr style={{ background: "#ACB1D6" }}>
                                <th></th>
                                <th style={{ width: '120px' }}>Enero</th>
                                <th style={{ width: '120px' }}>Febrero</th>
                                <th style={{ width: '120px' }}>Marzo</th>
                                <th style={{ width: '120px' }}>Abril</th>
                                <th style={{ width: '120px' }}>Mayo</th>
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
                </Col>
            </Row>
            <Row>
                <div style={{ width: "500px", height: "250px", margin: "auto", justifyContent: "center" }}>
                    <LineChart />
                </div>
            </Row>
        </Container>
    );
};

export default Flujo;
