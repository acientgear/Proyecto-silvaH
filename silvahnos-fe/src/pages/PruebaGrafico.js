
import { useState, useCallback } from 'react';
import { ListGroup, Col, Row, Container, Badge, Tab, Tabs, Card } from 'react-bootstrap';
import PieChartIngreso from './ingreso/PieChart';
import PieChartEgreso from './egreso/PieChart';
import GraficoMensual from './GraficoMensual';
import GraficoAnual from './GraficoAnual';
import TablaMensual from './TablaMensual';
import TablaAnual from './TablaAnual';
import Registros from './Registros';
import urlweb from '../config/config';
import axios from 'axios';
import { AiOutlineUser, AiOutlineFall, AiOutlineFileText, AiOutlineMail } from 'react-icons/ai';

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
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Row>
                            <TablaAnual />
                        </Row>
                    </Col>
                </Row>
                <Row style={{ maxWidth: "800px" }}>
                    <Card style={{ width: "100%" }}>
                        <Card.Body>
                            <Card.Title>Ingresos y egresos</Card.Title>
                            <Card.Subtitle className="mb-2 text-muted">Anual</Card.Subtitle>
                            <div>
                                <GraficoAnual />
                            </div>
                        </Card.Body>
                    </Card>
                </Row>
            </div>
        )
    }

    const getResumenMensual = () => {
        setResumenMensualHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen mensual</h1>
                <Row xs={1} lg={3}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <TablaMensual />
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Registros />
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Card>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <div style={{ width: "100%", height: "250px", margin: "auto", justifyContent: "center" }}>
                                    <GraficoMensual />
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row >
                <Row xs={1} lg={2}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Row>
                            <Card>
                                <Card.Body>
                                    <Card.Title>Distribución motivos de egresos</Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                    <PieChartEgreso anio={anio} mes={mes} />
                                </Card.Body>
                            </Card>
                        </Row>
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Row>
                            <Card>
                                <Card.Body>
                                    <Card.Title>Distribución motivos de egresos</Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                    <PieChartIngreso anio={anio} mes={mes} />
                                </Card.Body>
                            </Card>
                        </Row>
                    </Col>
                </Row>
            </div >
        )
    }

    const mes = new Date().getMonth() + 1;
    const anio = new Date().getFullYear();
    const [tabType, setTabType] = useState('Anual');

    return (
        <div className='contenedor'>
            <aside className='sidebar'>
                <div className='nav'>
                    <ul className='nav-collapse'>
                        <li active={handleActive("resumen_anual")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#resumen_anual' onClick={() => handleSeccion("resumen_anual")}>
                                <AiOutlineUser />
                            </a>
                        </li>
                        <li active={handleActive("resumen_mensual")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#resumen_mensual' onClick={() => handleSeccion("resumen_mensual")}>
                                <AiOutlineFall />
                            </a>
                        </li>
                    </ul>

                    <ul className='nav-always'>
                        <li active={handleActive("resumen_anual")} className='link-always'>
                            <a className='link-anchor-always' href='#resumen_anual' onClick={() => handleSeccion("resumen_anual")}>
                                <AiOutlineUser /> Resumen anual
                            </a>
                        </li>
                        <li active={handleActive("resumen_mensual")} className='link-always'>
                            <a className='link-anchor-always' href='#resumen_mensual' onClick={() => handleSeccion("resumen_mensual")}>
                                <AiOutlineFall /> Resumen mensual
                            </a>
                        </li>
                    </ul>
                </div>
            </aside>
            <main className='contenido'>
                <Container>
                    {seccion === "resumen_anual" ? (resumenAnualHTML !== null ? resumenAnualHTML : getResumenAnual()) : null}
                    {seccion === "resumen_mensual" ? (resumenMensualHTML !== null ? resumenMensualHTML : getResumenMensual()) : null}
                </Container>
            </main>
        </div>
    );
};

export default Resumen;
