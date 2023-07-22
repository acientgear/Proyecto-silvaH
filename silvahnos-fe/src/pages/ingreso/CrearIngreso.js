import axios from 'axios';
import { Col, Container, Row } from 'react-bootstrap';
import FormIngreso from '../../components/FormIngreso';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const CrearIngreso = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };  

    const ingreso = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        patente: '',
        monto: 1,
        motivo: {
            id: 0
        },
        descripcion: '',
    }

    const createIngreso = async (ingreso) => {
        try {
            let url = "http://"+urlweb+"/ingresos";
            let response = await axios.post(url,ingreso,config);
            if (response.status === 200) {
                localStorage.setItem("alert", true);
                window.location.href = "/ingresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
            <Row>
                <Col>
                    <h1>Crear Ingreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormIngreso
                        ingreso={ingreso}
                        postIngreso={createIngreso}
                        modal={false}
                    />
                </Col>
            </Row>
        </Container>
    );
};

export default CrearIngreso;