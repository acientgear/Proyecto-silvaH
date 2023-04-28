import { Col, Row, Card, Button, Container, Table, Badge, ListGroup } from "react-bootstrap";
import LineChartIngresos from './ingreso/Grafico';
import LineChartEgresos from './egreso/Grafico';

const Home = () => {
  return (
    <Container>
      <Row className="justify-content-center">
        <Col xs="auto" >
          <Button style={{ backgroundColor: "#D8E482", border: "none", color: "black",fontWeight:"bold" }} href="/flujo">Visualizar flujo de caja</Button>
        </Col>
        <Col xs="auto" >
          <ListGroup>
            <ListGroup.Item style={{fontWeight:"bold"}}>Saldo cuenta: $ 5.000</ListGroup.Item>
          </ListGroup>
        </Col>
      </Row>
      <Row xs={1} lg={2} xl={3}>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Últimos ingresos registrados</Card.Title>
              <Card.Text>
                <Table striped responsive="sm" hover bordered>
                  <thead>
                    <tr>
                      <th style={{ width: '100px' }}>Fecha</th>
                      <th style={{ width: '100px' }}>Patente</th>
                      <th style={{ width: '100px' }}>Monto</th>
                    </tr>

                  </thead>
                  <tbody>
                    <tr>
                      <td>04-03-2021</td>
                      <td>ASTARA</td>
                      <td>$ 14.000</td>
                    </tr>
                    <tr>
                      <td>05-03-2021</td>
                      <td>HSJD78</td>
                      <td>$ 15.000</td>
                    </tr>
                    <tr>
                      <td>06-03-2021</td>
                      <td>JD7823</td>
                      <td>$ 16.000</td>
                    </tr>
                  </tbody>
                </Table>
                <ListGroup>
                  <ListGroup.Item>Total ingresos: $ 60.000</ListGroup.Item>
                </ListGroup>
                <ListGroup>
                  <LineChartIngresos/>
                </ListGroup>
              </Card.Text>
              <Button variant="success" href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold" }}>Registrar un ingreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Últimos egresos registrados</Card.Title>
              <Card.Text>
                <Table striped responsive="sm" hover bordered>
                  <thead>
                    <tr>
                      <th style={{ width: '100px' }}>Fecha</th>
                      <th style={{ width: '100px' }}>Patente</th>
                      <th style={{ width: '100px' }}>Monto</th>
                    </tr>

                  </thead>
                  <tbody>
                    <tr>
                      <td>07-03-2021</td>
                      <td>TALLER</td>
                      <td>$ 17.000</td>
                    </tr>
                    <tr>
                      <td>08-03-2021</td>
                      <td>TALLER</td>
                      <td>$ 18.000</td>
                    </tr>
                    <tr>
                      <td>09-03-2021</td>
                      <td>TALLER</td>
                      <td>$ 19.000</td>
                    </tr>
                  </tbody>
                </Table>
                <ListGroup>
                  <ListGroup.Item>Total egresos: $ 55.000</ListGroup.Item>
                </ListGroup>
                <ListGroup>
                  <LineChartEgresos/>
                </ListGroup>
              </Card.Text>
              <Button variant="danger" href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar un egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Facturas más próximas a vencer</Card.Title>
              <Card.Text>
                <ListGroup as="ol" numbered>
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
              </Card.Text>
              <Button variant="primary" href="/crearFactura" style={{ backgroundColor: "#A5C0DD", border: "none", fontWeight: "bold", color: "black" }}>Registrar una factura</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container >
  );
};

export default Home;
