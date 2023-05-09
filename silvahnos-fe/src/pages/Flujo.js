import LineChart from './GraficoFlujo';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { ListGroup, ListGroupItem, Table, Col, Row, Container, Accordion, Badge, Tab, Tabs, Card } from 'react-bootstrap';
import PieChart from './PieChart';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';


const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
};

const formatearFecha = (fecha) => {
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
};

const data = [
    { month: 'Enero', ingresos: 2, egresos: 1, saldoCuenta: -8168454 },
    { month: 'Febrero', ingresos: 4, egresos: 3, saldoCuenta: 0 },
    { month: 'Marzo', ingresos: 6, egresos: 5, saldoCuenta: 0 },
    { month: 'Abril', ingresos: 8, egresos: 7, saldoCuenta: 0 },
    { month: 'Mayo', ingresos: 10, egresos: 9, saldoCuenta: 0 },
    { month: 'Junio', ingresos: 12, egresos: 11, saldoCuenta: 0 },
    { month: 'Julio', ingresos: 14, egresos: 13, saldoCuenta: 0 },
    { month: 'Agosto', ingresos: 16, egresos: 15, saldoCuenta: 0 },
    { month: 'Septiembre', ingresos: 18, egresos: 17, saldoCuenta: 0 },
    { month: 'Octubre', ingresos: 20, egresos: 19, saldoCuenta: 0 },
    { month: 'Noviembre', ingresos: 22, egresos: 21, saldoCuenta: 0 },
    { month: 'Diciembre', ingresos: 24, egresos: 23, saldoCuenta: 0 },
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

    const [saldos, setSaldos] = useState([]);

    const getSaldos = async () => {
        try {
            let url = 'http://localhost:8090/saldos/';
            const response = await axios.get(url);
            if (response.status === 200) {
                setSaldos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

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
        getSaldos();
    }, []);

    return (

        <Container fluid >
            <h1>Flujo de caja</h1>
            <Tab.Container id="list-group-tabs-example" defaultActiveKey="#link1">
                <Row>
                    <Col sm={3} style={{ marginTop: "10px" }}>
                        <ListGroup>
                            <ListGroup.Item action href="#link1">
                                Resumen anual
                            </ListGroup.Item>
                            <ListGroup.Item action href="#link2">
                                Resumen mensual
                            </ListGroup.Item>
                            <ListGroup.Item action href="#link3">
                                Gráficos
                            </ListGroup.Item>

                        </ListGroup>
                    </Col>
                    <Col sm={9}>
                        <Tab.Content>
                            <Tab.Pane eventKey="#link1">
                                <Row>
                                    <h2>Resumen anual</h2>
                                    <Table responsive hover>
                                        <thead>
                                            <tr style={{ background: "#ACB1D6" }}>
                                                <th width={125}></th>
                                                {Sem1.map((mes, index) => (
                                                    <th key={index} width={100}>{mes}</th>
                                                ))}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr style={{ background: "#E6F4DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                                {flujos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(mes.ingresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {flujos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(mes.egresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {flujos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        const saldoMesAnterior = index > 0 ? flujos[index - 1].saldoCuenta : 0;
                                                        flujos[index].saldoCuenta = saldoMesAnterior + mes.ingresos - mes.egresos;
                                                        return <td key={index}>{formatoMonto(saldoMesAnterior + mes.ingresos - mes.egresos)}</td>
                                                    }
                                                })}
                                            </tr>
                                        </tbody>
                                    </Table>
                                    <Table responsive hover>
                                        <thead>
                                            <tr style={{ background: "#ACB1D6" }}>
                                                <th width={125}></th>
                                                {Sem2.map((mes, index) => (
                                                    <th key={index} width={100}>{mes}</th>
                                                ))}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr style={{ background: "#E6F4DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                                {flujos.map((mes, index) => {
                                                    if (index > 5) {
                                                        return (<td key={index}>{formatoMonto(mes.ingresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {flujos.map((mes, index) => {
                                                    if (index > 5) {
                                                        return (
                                                            <td key={index}>{formatoMonto(mes.egresos)}</td>
                                                        )
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {flujos.map((mes, index) => {
                                                    if (index > 5) {
                                                        const saldoMesAnterior = index > 0 ? flujos[index - 1].saldoCuenta : 0;
                                                        flujos[index].saldoCuenta = saldoMesAnterior + mes.ingresos - mes.egresos;
                                                        return <td key={index}>{formatoMonto(saldoMesAnterior + mes.ingresos - mes.egresos)}</td>
                                                    }
                                                })}
                                            </tr>
                                        </tbody>
                                    </Table>
                                    <hr />
                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="#link2">
                                <Row>
                                    <h4 style={{ textAlign: "center" }}>Ingresos y egresos de este mes</h4>
                                    <Col sm={6}>
                                        <Card style={{ maxHeight: '500px', overflowY: 'scroll', scrollbarWidth: 'thin', scrollbarColor: 'gray lightgray' }}>
                                            <Card.Body>
                                                {registros.map((registro) => (
                                                    <div key={registro.id}>
                                                        <ListGroup.Item as="li" className="d-flex justify-content-between align-items-start">
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
                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="#link3">
                                <Row sm={2}>
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


                                    </Col>
                                    <Col>
                                        <Card>
                                            <Card.Body>
                                                <Card.Title>Porcentaje ingresos y egresos</Card.Title>
                                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                                <Tabs
                                                    defaultActiveKey="Ingresos"
                                                    id="uncontrolled-tab-example"
                                                    style={{ justifyContent: "center"}}
                                                >
                                                    <Tab  eventKey="Ingresos" title="Ingresos">
                                                        <PieChart />
                                                    </Tab>
                                                    <Tab eventKey="Egresos" title="Egresos">
                                                        <PieChart />
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
