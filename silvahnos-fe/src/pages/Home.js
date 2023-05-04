import axios from 'axios';
import { Col, Row, Card, Button, Container, Table, Badge, ListGroup } from "react-bootstrap";
import { useState, useEffect } from 'react';
import LineChartIngresos from './ingreso/Grafico';
import LineChartEgresos from './egreso/Grafico';

const Home = () => {
  const [ingresos, setIngresos] = useState([]);
  const [egresos, setEgresos] = useState([]);

  const [totalIngresos, setTotalIngresos] = useState(0);
  const [totalEgresos, setTotalEgresos] = useState(0);

  const getIgresos = async () => {
    try {
      let url = 'http://localhost:8090/ingresos/ultimos';
      const response = await axios.get(url);
      if (response.status === 200) {
        setIngresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const getEgresos = async () => {
    try {
      let url = 'http://localhost:8090/egresos/ultimos';
      const response = await axios.get(url);
      if (response.status === 200) {
        setEgresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const formatearFecha = (fecha) => {
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
  };

  const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
  };

  let fechaAcual = new Date();
  let anio = fechaAcual.getFullYear();
  let mes = fechaAcual.getMonth() + 1;

  const totalIngresosMes = async () => {
    try {
      let url = 'http://localhost:8090/ingresos/total/' + anio + '/' + mes;
      const response = await axios.get(url);
      if (response.status === 200) {
        setTotalIngresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const totalEgresosMes = async () => {
    try {
      let url = 'http://localhost:8090/egresos/total/' + anio + '/' + mes;
      const response = await axios.get(url);
      if (response.status === 200) {
        setTotalEgresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  useEffect(() => {
    getIgresos();
    totalIngresosMes();
    getEgresos();
    totalEgresosMes();
  }, []);

  return (
    <Container>
      <Row className="justify-content-center">
        <Col xs="auto" >
          <Button style={{ backgroundColor: "#D8E482", border: "none", color: "black", fontWeight: "bold" }} href="/flujo">Visualizar flujo de caja</Button>
        </Col>
        <Col xs="auto" >
          <ListGroup>
            <ListGroup.Item style={{ fontWeight: "bold" }}>Saldo cuenta: $ 8.000.000</ListGroup.Item>
          </ListGroup>
        </Col>
      </Row>
      <Row xs={1} lg={2} xl={3}>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Últimos ingresos registrados</Card.Title>
              <Table striped responsive="sm" hover bordered>
                <thead>
                  <tr>
                    <th style={{ width: '100px' }}>Fecha</th>
                    <th style={{ width: '100px' }}>Patente</th>
                    <th style={{ width: '100px' }}>Monto</th>
                  </tr>

                </thead>
                <tbody>
                  {ingresos.map((ingreso) => (
                    <tr key={ingreso.id}>
                      <td>{formatearFecha(ingreso.fecha_creacion)}</td>
                      <td>{ingreso.patente}</td>
                      <td>{formatoMonto(ingreso.monto)}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              <ListGroup>
                <ListGroup.Item>Total ingresos: {formatoMonto(totalIngresos)}</ListGroup.Item>
              </ListGroup>
              <ListGroup>
                <LineChartIngresos />
              </ListGroup>
              <Button href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold" }}>Registrar un ingreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Últimos egresos registrados</Card.Title>
              <Table striped responsive="sm" hover bordered>
                <thead>
                  <tr>
                    <th style={{ width: '100px' }}>Fecha</th>
                    <th style={{ width: '100px' }}>Patente</th>
                    <th style={{ width: '100px' }}>Monto</th>
                  </tr>
                </thead>
                <tbody>
                  {egresos.map((egreso) => (
                    <tr key={egreso.id}>
                      <td>{formatearFecha(egreso.fecha_creacion)}</td>
                      <td>{egreso.patente}</td>
                      <td>{formatoMonto(egreso.monto)}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              <ListGroup>
                <ListGroup.Item>Total egresos: {formatoMonto(totalEgresos)}</ListGroup.Item>
              </ListGroup>
              <ListGroup>
                <LineChartEgresos />
              </ListGroup>
              <Button href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar un egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Facturas más próximas a vencer</Card.Title>
              <ListGroup numbered>
                <ListGroup.Item
                  as="li"
                  className="d-flex justify-content-between align-items-start"
                >
                  <div className="ms-2 me-auto">
                    <div className="fw-bold">N° Factura: 131</div>
                    Fecha de vencimiento: 04-03-2021
                  </div>
                  <Badge bg="warning" pill style={{ color: "black" }}>
                    Vence en 2 días
                  </Badge>
                </ListGroup.Item>
                <ListGroup.Item
                  as="li"
                  className="d-flex justify-content-between align-items-start"
                >
                  <div className="ms-2 me-auto">
                    <div className="fw-bold">N° Factura: 132</div>
                    Fecha de vencimiento: 04-04-2021
                  </div>
                  <Badge bg="warning" pill style={{ color: "black" }}>
                    Vence en 3 días
                  </Badge>
                </ListGroup.Item>
                <ListGroup.Item
                  as="li"
                  className="d-flex justify-content-between align-items-start"
                >
                  <div className="ms-2 me-auto">
                    <div className="fw-bold">N° Factura: 133</div>
                    Fecha de vencimiento: 04-05-2021
                  </div>
                  <Badge bg="warning" pill style={{ color: "black" }}>
                    Vence en 4 días
                  </Badge>
                </ListGroup.Item>
              </ListGroup>
              <Button variant="primary" href="/crearFactura" style={{ backgroundColor: "#A5C0DD", border: "none", fontWeight: "bold", color: "black" }}>Registrar una factura</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container >
  );
};

export default Home;
