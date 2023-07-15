import { useState } from "react";
import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import { startOfMonth, endOfMonth, format } from 'date-fns';

const Reporte = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };

    const [tipo, setTipo] = useState("ingresos");
    const [fechaI, setFechaI] = useState(format(startOfMonth(new Date()), 'yyyy-MM-dd'));
    const [fechaF, setFechaF] = useState(format(endOfMonth(new Date()), 'yyyy-MM-dd'));

    const handleReport = () => {
        console.log(tipo);
        console.log(fechaI);
        console.log(fechaF);
    }

    return (
        <div style={{ width: "100%" }}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title>Seleccione el rango de fecha</Card.Title>
                    <br></br>
                    <Row style={{ gap: 10 }}>
                        <Col xs={12} sm={3}>
                            <Form.Select onChange={(e) => setTipo(e.target.value)}
                                aria-label="tipo"
                                value={tipo}
                            >
                                <option value="ingresos">Ingresos</option>
                                <option value="egresos">Egresos</option>
                                <option value="facturas">Facturas</option>
                            </Form.Select>
                        </Col>
                        <Col xs={12} sm={9}>
                            <Row>
                                <Col xs={3} style={{paddingRight: 0}}>
                                    <InputGroup.Text id="basic-addon1">Desde</InputGroup.Text>
                                </Col>
                                <Col xs={9} sm={3} style={{padding: (window.innerWidth < 576 ? "0 12px 0 0" : "0 0 0 0")}}>
                                    <Form.Control
                                        type="date"
                                        placeholder="Fecha Inicial"
                                        aria-label="Fecha Inicial"
                                        aria-describedby="basic-addon1"
                                        value={fechaI}
                                        onChange={(e) => setFechaI(e.target.value)}
                                    />
                                </Col>
                                <Col xs={3} style={{padding: (window.innerWidth < 576 ? "0 0 0 12px" : "0 0 0 0")}}>
                                    <InputGroup.Text id="basic-addon1">Hasta</InputGroup.Text>
                                </Col>
                                <Col xs={9} sm={3} style={{paddingLeft: 0}}>
                                    <Form.Control
                                        type="date"
                                        placeholder="Fecha Final"
                                        aria-label="Fecha Final"
                                        aria-describedby="basic-addon1"
                                        value={fechaF}
                                        onChange={(e) => setFechaF(e.target.value)}
                                    />
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                    <br></br>
                    <Row>
                        <Col>
                            <Button onClick={handleReport} variant="primary">Generar reporte</Button>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
        </div>
    );
}

export default Reporte;