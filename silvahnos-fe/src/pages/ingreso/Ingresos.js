import axios from 'axios';
import { Col, Container, Row, Table } from 'react-bootstrap';

const Ingresos = () => {
    const [ingresos, setIngresos] = useState([]);

    const getIngresos = async () => {
        try {
            let url = 'http://localhost:8090/ingresos';
            const response = await axios.get(url);
            if (response.status === 200) {
                setIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getIngresos();
    }, []);

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Ingresos</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha</th>
                                <th>Monto</th>
                                <th>Origen</th>
                                <th>Descripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            {ingresos.map((ingreso) => (
                                <tr key={ingreso.id}>
                                    <td>{ingreso.id}</td>
                                    <td>{ingreso.fecha}</td>
                                    <td>{ingreso.monto}</td>
                                    <td>{ingreso.patente}</td>
                                    <td>{ingreso.descripcion}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
};

export default Ingresos;