import { Col, Row, Card, Button, Container, Table, Accordion, Badge, ListGroup, Tabs, Tab } from "react-bootstrap";
import Ingresos from "../components/images/ingresos.png";
import Egresos from "../components/images/egresos.png";
import Facturas from "../components/images/facturas.png";

const Home = () => {
  return (
    <Container>
      <Row xs={1} className="justify-content-md-center">
        <Col md="auto">
          <Button style={{ backgroundColor: "#D8E482", border: "none", color: "black" }} href="/flujo">Visualizar flujo de caja</Button>
        </Col>
      </Row>
      <Row xs={1} sm={2} md={3}>
        <Col >
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Últimos Ingresos</Card.Title>
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
                      <td>04-03-2021</td>
                      <td>TALLER</td>
                      <td>$ 14.000</td>
                    </tr>
                    <tr>
                      <td>04-03-2021</td>
                      <td>TALLER</td>
                      <td>$ 14.000</td>
                    </tr>
                  </tbody>
                </Table>
                <ListGroup>
                  <ListGroup.Item>Total ingresos: $ 42.000</ListGroup.Item>
                </ListGroup>
              </Card.Text>
              <Button variant="success" href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold" }}>Registrar un ingreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Egresos del mes de abril</Card.Title>
              <Card.Text>
                <Accordion>
                  <Accordion.Item eventKey="0">
                    <Accordion.Header>Últimos registros</Accordion.Header>
                    <Accordion.Body>
                      <Table striped responsive="sm" hover>
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
                            <td>04-03-2021</td>
                            <td>TALLER</td>
                            <td>$ 14.000</td>
                          </tr>
                          <tr>
                            <td>04-03-2021</td>
                            <td>TALLER</td>
                            <td>$ 14.000</td>
                          </tr>
                        </tbody>
                      </Table>
                    </Accordion.Body>
                  </Accordion.Item>
                  <Accordion.Item eventKey="1">
                    <Accordion.Header>Total egresos</Accordion.Header>
                    <Accordion.Body>
                      $ 42.000
                    </Accordion.Body>
                  </Accordion.Item>
                </Accordion>
              </Card.Text>
              <Button variant="danger" href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black" }}>Registrar un egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col>
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
                    <Badge bg="primary" pill>
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
                    <Badge bg="primary" pill>
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
                    <Badge bg="primary" pill>
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
