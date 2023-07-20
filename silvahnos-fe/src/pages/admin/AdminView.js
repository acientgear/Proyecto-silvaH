import { Row, Col, Container } from 'react-bootstrap'
import Usuario from './Usuario';
import MotivoI from './MotivoI';
import MotivoE from './MotivoE';
import Empresas from './Empresas';
import Parametro from './Parametro';
import Correo from './Correo';
import './AdminView.css';
import './CardStyle.css'
import { AiOutlineUser, AiOutlineBarChart, AiOutlineFileText, AiOutlineMail, AiOutlineTag } from 'react-icons/ai';
import { useState } from 'react';
import Reporte from './Reporte';

const AdminView = () => {

    const [motivosHTML, setMotivosHTML] = useState(null);
    const [facturasHTML, setFacturasHTML] = useState(null);
    const [usuariosHTML, setUsuariosHTML] = useState(null);
    const [correosHTML, setCorreosHTML] = useState(null);
    const [reporteHTML, setReporteHTML] = useState(null);
    const [seccion, setSeccion] = useState("motivos");

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

    const getUsuarios = () => {
        setUsuariosHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Usuarios</h1>
                <Row xs={1} lg={2}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Usuario />
                    </Col>
                </Row>
            </div>
        );
    }

    const getMotivos = () => {
        setMotivosHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Motivos</h1>
                <Row xs={1} lg={2}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <MotivoI />
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <MotivoE />
                    </Col>
                </Row>
            </div>
        );
    }

    const getFacturas = () => {
        setFacturasHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Facturas</h1>
                <Row >
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Empresas />
                    </Col>
                </Row>
            </div>
        );
    }

    const getCorreos = () => {
        setCorreosHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Correo</h1>
                <Row xs={1} lg={2}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Parametro />
                    </Col>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Correo />
                    </Col>
                </Row>
            </div>
        );
    }

    const getReporte = () => {
        setReporteHTML(
            <div style={{ marginTop: "10px" }}>
                <h1>Reporte</h1>
                <Row xs={1}>
                    <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                        <Reporte />
                    </Col>
                </Row>
            </div>
        );
    }

    return (
        <div className='contenedor'>
            <aside className='sidebar'>
                <div className='nav'>
                    <ul className='nav-collapse'>
                        <li active={handleActive("usuarios")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#usuarios' onClick={() => handleSeccion("usuarios")}>
                                <AiOutlineUser />
                            </a>
                        </li>
                        <li active={handleActive("motivos")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#motivos' onClick={() => handleSeccion("motivos")}>
                                <AiOutlineTag />
                            </a>
                        </li>
                        <li active={handleActive("facturas")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#facturas' onClick={() => handleSeccion("facturas")}>
                                <AiOutlineFileText />
                            </a>
                        </li>
                        <li active={handleActive("correos")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#correos' onClick={() => handleSeccion("correos")}>
                                <AiOutlineMail />
                            </a>
                        </li>
                        <li active={handleActive("reporte")} className='link-collapse'>
                            <a className="link-anchor-collapse" href='#reporte' onClick={() => handleSeccion("reporte")}>
                                <AiOutlineBarChart />
                            </a>
                        </li>
                    </ul>
                    <ul className='nav-always'>
                        <li active={handleActive("usuarios")} className='link-always'>
                            <a className='link-anchor-always' href='#usuarios' onClick={() => handleSeccion("usuarios")}>
                                <AiOutlineUser /> Usuario
                            </a>
                        </li>
                        <li active={handleActive("motivos")} className='link-always'>
                            <a className='link-anchor-always' href='#motivos' onClick={() => handleSeccion("motivos")}>
                                <AiOutlineTag /> Motivo
                            </a>
                        </li>
                        <li active={handleActive("facturas")} className='link-always'>
                            <a className='link-anchor-always' href='#facturas' onClick={() => handleSeccion("facturas")}>
                                <AiOutlineFileText /> Factura
                            </a>
                        </li>
                        <li active={handleActive("correos")} className='link-always'>
                            <a className='link-anchor-always' href='#correos' onClick={() => handleSeccion("correos")}>
                                <AiOutlineMail /> Correo
                            </a>
                        </li>
                        <li active={handleActive("reporte")} className='link-always'>
                            <a className='link-anchor-always' href='#reporte' onClick={() => handleSeccion("reporte")}>
                                <AiOutlineBarChart /> Reporte
                            </a>
                        </li>
                    </ul>
                </div>
            </aside>
            <main className='contenido'>
                <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                    {/*Falta el de usuarios*/}
                    {seccion === "usuarios" ? (usuariosHTML !== null ? usuariosHTML : getUsuarios()) : null}
                    {seccion === "motivos" ? (motivosHTML !== null ? motivosHTML : getMotivos()) : null}
                    {seccion === "facturas" ? (facturasHTML !== null ? facturasHTML : getFacturas()) : null}
                    {seccion === "correos" ? (correosHTML !== null ? correosHTML : getCorreos()) : null}
                    {seccion === "reporte" ? (reporteHTML !== null ? reporteHTML : getReporte()) : null}
                </Container>
            </main>
        </div>
    );
}

export default AdminView;