import LineChart from './GraficoFlujo';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { ListGroup, Table, Col, Row, Container, Badge, Tab, Tabs, Card } from 'react-bootstrap';
import PieChartEgreso from './egreso/PieChart';
import PieChartIngreso from './ingreso/PieChart';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';
import GraficoBarras from './GraficoBarras';

const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
};

const formatearFecha = (fecha) => {
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
};





// otra forma de mostrar el saldo
const getTotalPorMes = async (tipo, anio, mes) => {
    try {
        let url = 'http://localhost:8090/' + tipo + '/total/' + anio + '/' + mes;
        const response = await axios.get(url);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.log(err.message);
    }
}

async function getTotalMontosPorMes(tipo) {
    const montos = [];
    const fecha = new Date();
    const anio = fecha.getFullYear();
    const mes = fecha.getMonth();

    for (let i = 1; i <= 12; i++) {
        const monto = getTotalPorMes(tipo, anio, i);
        montos.push(monto);
    }

    const datos = await Promise.all(montos);

    return datos;
}





const Flujo = () => {
    const [registros, setRegistros] = useState([]);
    const mes = new Date().getMonth() + 1;
    const anio = new Date().getFullYear();

    let saldo = 0;

    const idMes = mes.toLocaleString('es-ES', { month: 'long' });
    const nombreMes = (Sem1.concat(Sem2))[idMes]

    const [saldos, setSaldos] = useState([]);

    const getSaldos = async () => {
        try {
            let url = 'http://localhost:8090/saldo/' + anio;
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


    //otra forma de mostrar el saldo
    let tipo1 = 'ingresos';
    let tipo2 = 'egresos';

    const [montosIngresos, setMontosIngresos] = useState([]);
    const [montosEgresos, setMontosEgresos] = useState([]);
    let saldoCue = 0;

    const fetchMontos = async () => {
        const tipo1 = 'ingresos';
        const tipo2 = 'egresos';

        const montosIngresos = await getTotalMontosPorMes(tipo1);
        const montosEgresos = await getTotalMontosPorMes(tipo2);

        setMontosIngresos(montosIngresos);
        setMontosEgresos(montosEgresos);
    };


    useEffect(() => {
        getRegistros();
        getSaldos();
        fetchMontos(); // nuevo
    }, []);

    return (
        <Container fluid >
            <h1>Flujo de caja</h1>
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
                            <ListGroup.Item action variant="info" href="#otra_forma">
                                Otra forma de mostrar el saldo
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
                                                {Sem1.map((mes, index) => (
                                                    <th key={index} width={100}>{mes}</th>
                                                ))}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr style={{ background: "#E6F4DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                                {saldos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(mes.ingresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {saldos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(mes.egresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {saldos.map((mes, index) => {
                                                    if (index <= 5) {
                                                        saldo = saldo + mes.ingresos - mes.egresos
                                                        return <td key={index}>{formatoMonto(saldo)}</td>;
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
                                                {saldos.map((mes, index) => {
                                                    if (index > 5) {
                                                        return (<td key={index}>{formatoMonto(mes.ingresos)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {saldos.map((mes, index) => {
                                                    if (index > 5) {
                                                        return (
                                                            <td key={index}>{formatoMonto(mes.egresos)}</td>
                                                        )
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {saldos.map((mes, index) => {
                                                    if (index > 5) {
                                                        saldo = saldo + mes.ingresos - mes.egresos
                                                        //const saldoMesAnterior = index > 0 ? flujos[index - 1].saldoCuenta : 0;
                                                        //flujos[index].saldoCuenta = saldoMesAnterior + mes.ingresos - mes.egresos;
                                                        return <td key={index}>{formatoMonto(saldo)}</td>;
                                                    }
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
                                                    <th>{nombreMes}</th>
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
                                                        <PieChartIngreso />
                                                    </Tab>
                                                    <Tab eventKey="Egresos" title="Egresos" style={{ color: "black" }}>
                                                        <PieChartEgreso />
                                                    </Tab>
                                                </Tabs>
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="#otra_forma">
                                <h3>Otra forma de mostrar el saldo</h3>
                                <hr></hr>
                                <Row>
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
                                                {montosIngresos.map((monto, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(monto)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {montosEgresos.map((monto, index) => {
                                                    if (index <= 5) {
                                                        return (<td key={index}>{formatoMonto(monto)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {montosIngresos.map((monto, index) => {
                                                    if (index <= 5) {
                                                        saldoCue = saldoCue + monto - montosEgresos[index]
                                                        return <td key={index}>{formatoMonto(saldoCue)}</td>;
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
                                                {montosIngresos.map((monto, index) => {
                                                    if (index > 5) {
                                                        return (<td key={index}>{formatoMonto(monto)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#FBE6DD" }}>
                                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                                {montosEgresos.map((monto, index) => {
                                                    if (index > 5) {
                                                        return (<td key={index}>{formatoMonto(monto)}</td>)
                                                    }
                                                })}
                                            </tr>
                                            <tr style={{ background: "#B9F3E4" }}>
                                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                                {montosIngresos.map((monto, index) => {
                                                    if (index > 5) {
                                                        saldoCue = saldoCue + monto - montosEgresos[index]
                                                        return <td key={index}>{formatoMonto(saldoCue)}</td>;
                                                    }
                                                })}
                                            </tr>
                                        </tbody>
                                    </Table>
                                    <hr />
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
