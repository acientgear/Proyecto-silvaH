import LineChart from './GraficoFlujo';
import { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import { ListGroup, Table, Col, Row, Container, Badge, Tab, Tabs, Card } from 'react-bootstrap';
import PieChartEgreso from './egreso/PieChart';
import PieChartIngreso from './ingreso/PieChart';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';
import GraficoBarras from './GraficoBarras';
import TablaMensual from './TablaMensual';

const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
};

const formatearFecha = (fecha) => {
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
};

const Flujo = () => {
    const [registros, setRegistros] = useState([]);
    const mes = new Date().getMonth() + 1;
    const anio = new Date().getFullYear();

    const getTotalPorMes = useCallback(async (tipo, anio, mes) => {
        try {
            let url = 'http://localhost:8090/' + tipo + '/total/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                return response.data;
            }
        } catch (err) {
            console.log(err.message);
        }
    }, []);

    const getTotalMontosPorMes = useCallback(async (tipo) => {
        const montos = [];
        const fecha = new Date();
        const anio = fecha.getFullYear();

        for (let i = 1; i <= 12; i++) {
            const monto = await getTotalPorMes(tipo, anio, i);
            montos.push(monto);
        }

        const datos = await Promise.all(montos);

        return datos;
    }, [getTotalPorMes]);

    const getRegistros = useCallback(async () => {
        try {
            let url = 'http://localhost:8090/registros/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setRegistros(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    const [montosIngresos, setMontosIngresos] = useState([]);
    const [montosEgresos, setMontosEgresos] = useState([]);
    let saldoCue = 0;

    const fetchMontos = useCallback(async () => {
        const tipo1 = 'ingresos';
        const tipo2 = 'egresos';

        const montosIngresos = await getTotalMontosPorMes(tipo1);
        const montosEgresos = await getTotalMontosPorMes(tipo2);

        setMontosIngresos(montosIngresos);
        setMontosEgresos(montosEgresos);
    }, [getTotalMontosPorMes]);

    useEffect(() => {
        getRegistros();
        fetchMontos();
    }, [fetchMontos, getRegistros]);

    console.log("flujo.js");

    return (
        <Container fluid >
            <h1>Balance</h1>
            <Tab.Container id="list-group-tabs-example" defaultActiveKey="#resumen_anual">
                <Row>
                    <Col sm={3} style={{ marginTop: "10px" }}>
                        <ListGroup>
                            <ListGroup.Item action variant="info" href="#resumen_anual">
                                Resumen anual
                            </ListGroup.Item>
                            <ListGroup.Item action variant="info" href="#resumen_mensual">
                                Resumen mensual
                            </ListGroup.Item>
                            <ListGroup.Item action variant="info" href="#graficos">
                                Gráficos
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                    <Col sm={9}>
                        <Tab.Content>
                            <Tab.Pane eventKey="#resumen_anual">
                                <h3>Resumen anual</h3>
                                <hr></hr>
                                <Row>
                                    <Table responsive hover>
                                        <thead>
                                            <tr style={{ background: "#ACB1D6" }}>
                                                <th width={125}></th>
                                                {Sem1.map((mes) => (
                                                    <th key={mes} width={100}>{mes}</th>
                                                ))}

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr style={{ background: "#E6F4DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                                {montosIngresos.slice(0, 6).map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {montosEgresos.slice(0, 6).map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {montosIngresos.slice(0, 6).map((monto, index) => {
                                                    saldoCue = saldoCue + monto - montosEgresos[index];
                                                    return <td key={index}>{formatoMonto(saldoCue)}</td>;
                                                })}
                                            </tr>
                                        </tbody>
                                    </Table>
                                    <Table responsive hover>
                                        <thead>
                                            <tr style={{ background: "#ACB1D6" }}>
                                                <th width={125}></th>
                                                {Sem2.map((mes) => (
                                                    <th key={mes} width={100}>{mes}</th>
                                                ))}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr style={{ background: "#E6F4DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                                {montosIngresos.slice(6, 12).map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {montosEgresos.slice(6, 12).map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {montosIngresos.slice(6, 12).map((monto, index) => {
                                                    saldoCue = saldoCue + monto - montosEgresos[index];
                                                    return <td key={index}>{formatoMonto(saldoCue)}</td>;
                                                })}
                                            </tr>
                                        </tbody>
                                    </Table>
                                    <hr />
                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="#resumen_mensual">
                                <h3>Resumen mensual</h3>
                                <hr></hr>
                                <Row>
                                    <Col sm={6}>
                                        <Card style={{ maxHeight: '500px', overflowY: 'scroll', scrollbarWidth: 'thin', scrollbarColor: 'gray lightgray' }}>
                                            <Card.Body>
                                                {registros.map((registro, index) => (
                                                    <div key={index}>
                                                        <ListGroup.Item key={`registro-${index}`} as="li" className="d-flex justify-content-between align-items-start">
                                                            <div className="ms-2 me-auto">
                                                                <div className="fw-bold">{formatearFecha(registro.fecha)}</div>
                                                                {registro.descripcion}
                                                            </div>
                                                            <Badge bg={registro.tipo === 'Egreso' ? '#FBE6DD' : '#E6F4DD'} pill style={{ color: "black", backgroundColor: registro.tipo === 'Egreso' ? '#FBE6DD' : '#E6F4DD' }}>
                                                                {formatoMonto(registro.monto)}
                                                            </Badge>
                                                        </ListGroup.Item>
                                                        <br></br>
                                                    </div>
                                                ))}
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                    <Col sm={6}>
                                        <TablaMensual />
                                    </Col>
                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="#graficos">
                                <h3>Gráficos</h3>
                                <hr></hr>
                                <Row xs={1} sm={2}>
                                    <Col>
                                        <Card>
                                            <Card.Body>
                                                <Card.Title>Ingresos y egresos</Card.Title>
                                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                                <div style={{ width: "100%", height: "250px", margin: "auto", justifyContent: "center" }}>
                                                    <LineChart />
                                                </div>
                                            </Card.Body>
                                        </Card>
                                        <br></br>
                                        <Card>
                                            <Card.Body>
                                                <Card.Title>Ingresos y egresos</Card.Title>
                                                <Card.Subtitle className="mb-2 text-muted">Anual</Card.Subtitle>
                                                <div style={{ width: "100%", height: "250px", margin: "auto", justifyContent: "center" }}>
                                                    <GraficoBarras />
                                                </div>
                                            </Card.Body>
                                        </Card>

                                    </Col>
                                    <Col>
                                        <Card>
                                            <Card.Body>
                                                <Card.Title>Porcentaje ingresos y egresos</Card.Title>
                                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                                <Tabs justify
                                                    defaultActiveKey="Ingresos"
                                                    id="uncontrolled-tab-example"
                                                    style={{ justifyContent: "center" }}
                                                >
                                                    <Tab eventKey="Ingresos" title="Ingresos" style={{ color: "black" }}>
                                                        <PieChartIngreso anio={anio} mes={mes} />
                                                    </Tab>
                                                    <Tab eventKey="Egresos" title="Egresos" style={{ color: "black" }}>
                                                        <PieChartEgreso anio={anio} mes={mes} />
                                                    </Tab>
                                                </Tabs>
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                </Row>
                            </Tab.Pane>
                        </Tab.Content>
                    </Col>
                </Row>
            </Tab.Container>
        </Container>
    );
};

export default Flujo;
