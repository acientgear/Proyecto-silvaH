
import { useState } from 'react';
import { Col, Row, Container, Card, Form } from 'react-bootstrap';
import PieChartIngreso from '../ingreso/PieChart';
import PieChartEgreso from '../egreso/PieChart';
import GraficoMensual from './GraficoMensual';
import GraficoAnual from './GraficoAnual';
import TablaMensual from './TablaMensual';
import TablaAnual from './TablaAnual';
import Registros from './Registros';
import FormYear from '../../components/FormYear';
import FormMonth from '../../components/FormMonth';
import { AiOutlineRise, AiOutlinePieChart } from 'react-icons/ai';

const Resumen = () => {
    const [resumenAnualHTML, setResumenAnualHTML] = useState(null);
    const [resumenMensualHTML, setResumenMensualHTML] = useState(null);

    const [seccion, setSeccion] = useState("resumen_anual");

    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    const [anio2, setAnio2] = useState((new Date()).getFullYear());

    const handleSeccion = (seccion) => {
        setSeccion(seccion);
    }

    const handleChangeAnio = (anio) => {
        setAnio(anio);
        getResumenAnual(anio);
    };
    
    const handleChangeAnio2 = (anio2) => {
        setAnio2(anio2);
        console.log(anio2);
        getResumenMensual(anio2,mes);
    };

    const handleChangeMes = (e) => {
        setMes(e.target.value);
        console.log(e.target.value);
        getResumenMensual(anio2,e.target.value);
    };

    const handleActive = (sec) => {
        if (sec === seccion) {
            return "true";
        } else {
            return "false";
        }
    }

    const getResumenAnual = (anio) => {
        setResumenAnualHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen anual</h1>
                <Row>
                    <FormYear anio={anio} handleAnio={handleChangeAnio}/>
                </Row>
                <Row>
                    <Col md={12}>
                        <Card style={{ width: "100%" }}>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Col style={{ justifyContent: "center", alignItems: "start" }}>
                                    <Row>
                                        <TablaAnual anio={anio}/>
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
                                    <GraficoAnual anio={anio}/>
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </div>
        )
    }

    const getResumenMensual = (anio2,mes) => {
        console.log("1",anio2,mes);
        setResumenMensualHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen mensual</h1>
                <Row>
                    <FormMonth mes={mes} anio={anio2} get={getResumenMensual}/>
                    <Col className="column-spacing" style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Card style={{ width: "100%", margin: "10px 0 10px 0" }}>
                            <Card.Body>
                                <Card.Title>Distribución motivos de ingresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                {console.log("2",anio2,mes)}
                                <PieChartIngreso anio={anio2} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col className="column-spacing" style={{ justifyContent: "center", alignItems: "start" }}>
                        <Registros anio={anio2} mes={mes}/>
                    </Col>
                    <Col className="column-spacing" style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Card style={{ width: "100%", margin: "10px 0 10px 0" }}>
                            <Card.Body>
                                <Card.Title>Distribución motivos de egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <PieChartEgreso anio={anio2} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                <Row>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <TablaMensual anio={anio2} mes={mes}/>
                    </Col>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <Card className="cardsH">
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <GraficoMensual anio={anio2} mes={mes}/>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row >
            </div >
        )
    }

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
                    {seccion === "resumen_anual" ? (resumenAnualHTML !== null ? resumenAnualHTML : getResumenAnual(anio)) : null}
                    {seccion === "resumen_mensual" ? (resumenMensualHTML !== null ? resumenMensualHTML : getResumenMensual(anio2,mes)) : null}
                </Container>
            </main>
        </div>
    );
};

export default Resumen;
