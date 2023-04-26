import { Col, Row, Card, Button, Container, Table } from "react-bootstrap";
import Ingresos from "../components/images/ingresos.png";
import Egresos from "../components/images/egresos.png";
import Silvahnos from "../components/images/silvahnos.jpg";

const Flujo = () => {
    return (
        <Container>
            <Row>
                <Col><h1>Flujo de caja</h1></Col>
            </Row>
            <Row className="justify-content-md-center">
                <Col md="auto">
                    <Table responsive bordered style={{borderColor:"black",width:"400px"}}>
                        <thead>
                            <tr style={{ background: "#FF6D60" }}>
                                <th>Total egresos</th>
                                <th>$ 20.690.022</th>
                            </tr>
                            <tr style={{ background: "#F7D060" }}>
                                <th>Total ingresos</th>
                                <th>$ 22.268.362</th>
                            </tr>
                            <tr style={{ background: "#F3E99F" }}>
                                <th>Cta Ctte Febrero 2023</th>
                                <th>$ -6.168.454</th>
                            </tr>
                            <tr style={{ background: "#98D8AA" }}>
                                <th>Flujo</th>
                                <th>$ -4.590.114</th>
                            </tr>
                        </thead>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
};

export default Flujo;
