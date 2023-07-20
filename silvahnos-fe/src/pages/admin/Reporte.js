import { useState } from "react";
import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";
import { startOfMonth, endOfMonth, format } from 'date-fns';
import urlweb from "../../config/config";
import FileSaver from "file-saver";

const Reporte = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };

    const [tipo, setTipo] = useState("ingresos");
    const [fechaI, setFechaI] = useState(format(startOfMonth(new Date()), 'yyyy-MM-dd'));
    const [fechaF, setFechaF] = useState(format(endOfMonth(new Date()), 'yyyy-MM-dd'));

    const handleFechaInicioChange = (e) => {
        const fecha = e.target.value;
        setFechaI(fecha);
        setFechaF((value) => {
            if (fecha > value) {
                return fecha;
            }
            return value;
        });
    };

    const handleFechaFinChange = (e) => {
        const fecha = e.target.value;
        setFechaF(fecha);
        setFechaI((value) => {
            if (fecha < value) {
                return fecha;
            }
            return value;
        });
    };

    const handleReport = async (e, web, tipo, fi, ff) => {
        e.preventDefault();
        try{
            const url = `http://${web}/${tipo}/export-pdf/${fi}/${ff}`;
            const response = await fetch(url, config);
            if (response.ok) {
                const contentDisposition = response.headers.get('content-disposition');
                const filenameMatch = contentDisposition && contentDisposition.match(/filename=(["'])(.*?)\1/);
                const filename = filenameMatch && filenameMatch[2];
                
                const blob = await response.blob();
                FileSaver.saveAs(blob, filename);
            } else {
                console.log('Error al descargar el archivo');
            }
        }catch(error){
            console.log(error);
        }
    }

    return (
        <div style={{ width: "100%" }}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title>Seleccione el rango de fecha</Card.Title>
                    <br></br>
                    <Form onSubmit={(e) => handleReport(e, urlweb, tipo, fechaI, fechaF)}>
                        <Row className="justify-content-center">
                            <Col xs={12} sm="auto">
                                <Form.Group className="text-left">
                                    <Form.Label>Tipo de reporte</Form.Label>
                                    <Form.Select onChange={(e) => setTipo(e.target.value)}
                                        aria-label="tipo"
                                        value={tipo}
                                        label="tipo"
                                    >
                                        <option value="ingresos">Ingresos</option>
                                        <option value="egresos">Egresos</option>
                                        <option value="facturas">Facturas</option>
                                    </Form.Select>
                                </Form.Group>
                            </Col>
                            <Col xs={12} sm="auto">
                                <Form.Group className="text-left">
                                    <Form.Label>Fechas</Form.Label>
                                    <InputGroup>
                                        <InputGroup.Text id="basic-addon1">Desde</InputGroup.Text>
                                        <Form.Control
                                            type="date"
                                            value={fechaI}
                                            onChange={handleFechaInicioChange}
                                        />
                                        <InputGroup.Text id="basic-addon1">Hasta</InputGroup.Text>
                                        <Form.Control
                                            type="date"
                                            value={fechaF}
                                            onChange={handleFechaFinChange}
                                        />

                                    </InputGroup>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Button className="mt-3" variant="primary" type="submit">
                            Generar reporte
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
}

export default Reporte;