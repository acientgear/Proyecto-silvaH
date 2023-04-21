import { Col, Container, Row, Card, Button, Carousel } from "react-bootstrap";
import Ingresos from "../components/images/ingresos.png";
import Egresos from "../components/images/egresos.png";

const Home = () => {
  return (
    <Container style={{ textAlign: "center" }}>
      <Row>
        <Col>
        <Button style={{ backgroundColor: "#77E079", border: "none" }} href="/#flujo">Visualizar flujo de caja</Button>
        </Col>
      </Row>
      <Row>
        
        <Col>
          <Card style={{ width: '18rem', border: "none" }}>

            <Card.Body>
              <Card.Title>Ingresos de este mes</Card.Title>
              <Card.Text>
                $ 4.000.000
              </Card.Text>
            </Card.Body>

            <p></p>
            <Button style={{ backgroundColor: "#77E079", border: "none" }} href="/crearIngreso">Registrar ingreso</Button>
          </Card>
        </Col>
        
        <Col>
          <Card style={{ width: '18rem', border: "none" }}>

            <Card.Body>
              <Card.Title>Egresos de este mes</Card.Title>
              <Card.Text>
                $ 2.000.000
              </Card.Text>
            </Card.Body>

            <p></p>
            <Button style={{ backgroundColor: "#E08C77", border: "none" }} href="/crearEgreso">Registrar egreso</Button>
          </Card>
        </Col>
        
        <Col>
          <Card style={{ width: '18rem', border: "none" }}>

            <Card.Body>
              <Card.Title>$ 2.000.000</Card.Title>
            </Card.Body>
          </Card>
        </Col>
        
      </Row>
    </Container>
  );
};

export default Home;
