import axios from 'axios';
import { Col, Container, Row } from 'react-bootstrap';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

function CrearEgreso() {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    }; 

    const egreso = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        monto: 1,
        motivo: {
            id: 0
        },
        descripcion: ''
    }

    const createEgreso = async (egreso) => {
        try {
            let url = "http://"+urlweb+"/egresos";
            let response = await axios.post(url, egreso,config);
            if (response.status === 200) {   
                localStorage.setItem("alert", JSON.stringify({
                    show: true,
                    type: "egreso"
                }));             
                window.location.href = "/egresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
            <Row>
                <Col>
                    <h1>Crear Egreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormEgreso 
                        egreso={egreso}
                        postEgreso={createEgreso}
                        modal={false}
                    />
                </Col>
            </Row>
        </Container >
    );
}

export default CrearEgreso;