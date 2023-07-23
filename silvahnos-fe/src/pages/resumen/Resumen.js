
import { useState } from 'react';
import { Col, Row, Container, Card } from 'react-bootstrap';
import PieChartIngreso from '../ingreso/PieChart';
import PieChartEgreso from '../egreso/PieChart';
import GraficoMensual from './GraficoMensual';
import GraficoAnual from './GraficoAnual';
import TablaMensual from './TablaMensual';
import TablaAnual from './TablaAnual';
import Registros from './Registros';
import { AiOutlineRise, AiOutlinePieChart } from 'react-icons/ai';

const Resumen = () => {
    const [resumenAnualHTML, setResumenAnualHTML] = useState(null);
    const [resumenMensualHTML, setResumenMensualHTML] = useState(null);

    const [seccion, setSeccion] = useState("resumen_anual");

    const handleSeccion = (seccion) => {
        setSeccion(seccion);
    }

    const handleActive = (sec) => {
        if (sec === seccion) {
            return "true";
        } else {
            return "false";
        }
    }

    const getResumenAnual = () => {
        setResumenAnualHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen anual</h1>
                <Row>
                    <Col md={12}>
                        <Card style={{ width: "100%" }}>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Col style={{ justifyContent: "center", alignItems: "start" }}>
                                    <Row>
                                        <TablaAnual />
                                    </Row>
                                </Col>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                <Row>
                    <p></p>
                    <Col md={7}>
                        <Card style={{ width: "100%" }}>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos anuales</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted"></Card.Subtitle>
                                <div>
                                    <GraficoAnual />
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </div>
        )
    }

    const getResumenMensual = () => {
        setResumenMensualHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen mensual</h1>
                <Row>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <p></p>
                        <Card >
                            <Card.Body>
                                <Card.Title>Distribución motivos de egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <PieChartEgreso anio={anio} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col xs={6} style={{ justifyContent: "center", alignItems: "start" }}>
                        <p></p>
                        <Card style={{ width: "100%" }}>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <br></br>
                                <div>
                                    <GraficoMensual />
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <p></p>
                        <Card>
                            <Card.Body>
                                <Card.Title>Distribución motivos de ingresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <PieChartIngreso anio={anio} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                <Row xs={1} lg={2}>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <TablaMensual />
                    </Col>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <Registros />
                    </Col>


                </Row >
            </div >
        )
    }

    const mes = new Date().getMonth() + 1;
    const anio = new Date().getFullYear();

    return (
        <div className='contenedor'>
            <aside className='sidebar'>
                <div className='nav'>
                    <ul className='nav-collapse'>
                        <li active={handleActive("resumen_anual")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#resumen_anual' onClick={() => handleSeccion("resumen_anual")}>
                                <AiOutlineRise />
                            </a>
                        </li>
                        <li active={handleActive("resumen_mensual")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#resumen_mensual' onClick={() => handleSeccion("resumen_mensual")}>
                                <AiOutlinePieChart />
                            </a>
                        </li>
                    </ul>

                    <ul className='nav-always'>
                        <li active={handleActive("resumen_anual")} className='link-always'>
                            <a className='link-anchor-always' href='#resumen_anual' onClick={() => handleSeccion("resumen_anual")}>
                                <AiOutlineRise /> Resumen anual
                            </a>
                        </li>
                        <li active={handleActive("resumen_mensual")} className='link-always'>
                            <a className='link-anchor-always' href='#resumen_mensual' onClick={() => handleSeccion("resumen_mensual")}>
                                <AiOutlinePieChart /> Resumen mensual
                            </a>
                        </li>
                    </ul>
                </div>
            </aside>
            <main className='contenido'>
                <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                    {seccion === "resumen_anual" ? (resumenAnualHTML !== null ? resumenAnualHTML : getResumenAnual()) : null}
                    {seccion === "resumen_mensual" ? (resumenMensualHTML !== null ? resumenMensualHTML : getResumenMensual()) : null}
                </Container>
            </main>
        </div>
    );
};

export default Resumen;
