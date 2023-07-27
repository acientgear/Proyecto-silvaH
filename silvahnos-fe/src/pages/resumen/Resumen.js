
import { useState } from 'react';
import { Col, Row, Container, Card, Toast } from 'react-bootstrap';
import PieChartIngreso from '../ingreso/PieChart';
import PieChartEgreso from '../egreso/PieChart';
import GraficoMensual from './GraficoMensual';
import GraficoAnual from './GraficoAnual';
import TablaMensual from './TablaMensual';
import TablaAnual from './TablaAnual';
import Registros from './Registros';
import FormYear from '../../components/FormYear';
import FormMonth from '../../components/FormMonth';
import { AiOutlineRise, AiOutlinePieChart, AiOutlineDown } from 'react-icons/ai';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';

const Resumen = () => {
    const [seccion, setSeccion] = useState("resumen_anual");

    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio_ra, setAnio] = useState((new Date()).getFullYear()); // para resumen anual
    const [anio_rm, setAnio2] = useState((new Date()).getFullYear()); // para resumen mensual
    const meses = Sem1.concat(Sem2);

    const [showToastRA, setShowToastRA] = useState(false);
    const [showToastRM, setShowToastRM] = useState(false);

    const toggleToastRA = () => setShowToastRA(!showToastRA);
    const toggleToastRM = () => setShowToastRM(!showToastRM);


    const handleSeccion = (seccion) => {
        setSeccion(seccion);
    }

    const handleChangeRA = (anio) => {
        toggleToastRA();
        setAnio(anio);
    };

    const handleChangeRM = (anio, mes) => {
        toggleToastRM();
        setAnio2(anio);
        setMes(mes);
    };

    const handleActive = (sec) => {
        if (sec === seccion) {
            return "true";
        } else {
            return "false";
        }
    }

    const resumenAnual = () => {
        return (
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen anual</h1>
                <Row>
                    <Col className='d-flex align-items-center gap-2'>
                        {`Del año ${anio_ra}`}
                        <AiOutlineDown
                            style={{ cursor: 'pointer' }}
                            onClick={toggleToastRA} />
                        <Toast
                            show={showToastRA}
                            onClose={toggleToastRA}
                            style={{
                                padding: 0,
                                position: "absolute",
                                top: 171,
                                zIndex: 999
                            }}
                        >
                            <Toast.Header>
                                <strong className="me-auto">Filtrar fecha: </strong>
                            </Toast.Header>
                            <Toast.Body style={{ backgroundColor: "white" }}>
                                <FormYear anio={anio_ra} handleAnio={handleChangeRA}/>
                            </Toast.Body>
                        </Toast>
                    </Col>
                </Row>
                <p></p>
                <Row>
                    <Col md={12}>
                        <Card style={{ width: "100%" }}>
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Col style={{ justifyContent: "center", alignItems: "start" }}>
                                    <Row>
                                        <TablaAnual anio={anio_ra}/>
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
                                    <GraficoAnual anio={anio_ra}/>
                                </div>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </div>
        )
    };
                            
    const resumenMensual = () => {
        return (
            <div style={{ marginTop: "10px" }}>
                <h1>Resumen mensual</h1>
                <Row>
                    <Col className='d-flex align-items-center gap-2'>
                        {`De ${meses[mes - 1]} del año ${anio_rm}`}
                        <AiOutlineDown
                            style={{ cursor: 'pointer' }}
                            onClick={toggleToastRM} />
                        <Toast
                            show={showToastRM}
                            onClose={toggleToastRM}
                            style={{
                                padding: 0,
                                position: "absolute",
                                top: 171,
                                zIndex: 999
                            }}
                        >
                            <Toast.Header>
                                <strong className="me-auto">Filtrar fecha: </strong>
                            </Toast.Header>
                            <Toast.Body style={{ backgroundColor: "white" }}>
                                <FormMonth mes={mes} anio={anio_rm} get={handleChangeRM}/>
                            </Toast.Body>
                        </Toast>
                    </Col>
                </Row>
                <Row>
                    <Col className="column-spacing" style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Card style={{ width: "100%", margin: "10px 0 10px 0" }}>
                            <Card.Body>
                                <Card.Title>Distribución motivos de ingresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <PieChartIngreso anio={anio_rm} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col className="column-spacing" style={{ justifyContent: "center", alignItems: "start" }}>
                        <Registros anio={anio_rm} mes={mes}/>
                    </Col>
                    <Col className="column-spacing" style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Card style={{ width: "100%", margin: "10px 0 10px 0" }}>
                            <Card.Body>
                                <Card.Title>Distribución motivos de egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <PieChartEgreso anio={anio_rm} mes={mes} />
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                <Row>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <TablaMensual anio={anio_rm} mes={mes}/>
                    </Col>
                    <Col style={{ justifyContent: "center", alignItems: "start" }}>
                        <Card className="cardsH">
                            <Card.Body>
                                <Card.Title>Ingresos y egresos</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">Mensual</Card.Subtitle>
                                <GraficoMensual anio={anio_rm} mes={mes}/>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row >
            </div >
        );
    };

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
                    {seccion === "resumen_anual" ? resumenAnual() : null}
                    {seccion === "resumen_mensual" ? resumenMensual() : null}
                </Container>
            </main>
        </div>
    );
};

export default Resumen;
