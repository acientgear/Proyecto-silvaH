import { Col, Row, Card, Button, Container, Table } from "react-bootstrap";
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
        <Col>
          
            <Card >

              <Card.Body>
                <Card.Title>Últimos Ingresos</Card.Title>
                <Card.Text>
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
                </Card.Text>
                <Button variant="success" href="/crearIngreso">Registrar un ingreso</Button>
              </Card.Body>
            </Card>
        </Col>
        <Col>
          <Card>
            <Card.Body>
              <Card.Title>Últimos Egresos</Card.Title>
              <Card.Text>
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
              </Card.Text>
              <Button variant="danger" href="/crearEgreso">Registrar un egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card>
            <Card.Body>
              <Card.Title>Facturas por vencer</Card.Title>
              <Card.Text>

              </Card.Text>
              <Button variant="primary" href="/crearFactura">Registrar una factura</Button>
            </Card.Body>
          </Card>

        </Col>
      </Row>
    </Container >
  );
};

export default Home;
