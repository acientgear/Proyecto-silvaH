import { Col, Row, Card, Button, Container } from "react-bootstrap";
import Ingresos from "../components/images/ingresos.png";
import Egresos from "../components/images/egresos.png";
import Silvahnos from "../components/images/silvahnos.jpg";

const Home = () => {
  return (
    <Container>
      <Row xs={1}  className="justify-content-md-center">
        <Col md="auto">
          <Button style={{ backgroundColor: "#D8E482", border: "none", color:"black"}} href="/flujo">Visualizar flujo de caja</Button>
        </Col>
      </Row>
      <Row xs={1} sm={2} md={3}>
        <Col>
          <Card className="text-center">
            <Card.Body>
              <Card.Title style={{ fontSize: "30px" }}>Ingresos</Card.Title>
              <Card.Text>
                <img className="img-fluid" src={Ingresos} alt="Ingresos" />
              </Card.Text>
              <Button variant="success" href="/crearIngreso">Registrar un ingreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card className="text-center">
            <Card.Body>
              <Card.Title style={{ fontSize: "30px" }}>Egresos</Card.Title>
              <Card.Text>
                <img className="img-fluid" src={Egresos} alt="Egresos" />
              </Card.Text>
              <Button variant="danger" href="/crearEgreso">Registrar un egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col>
          <Card className="text-center">
            <Card.Body>
              <Card.Title style={{ fontSize: "30px" }}>Facturas</Card.Title>
              <Card.Text>
                <img className="img-fluid" src={Silvahnos} alt="Egresos" />
              </Card.Text>
              <Button variant="primary" href="/crearFactura">Registrar una factura</Button>
            </Card.Body>
          </Card>

        </Col>
      </Row>
    </Container>
  );
};

export default Home;
