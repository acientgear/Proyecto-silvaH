import { Row, Col} from 'react-bootstrap'
import MotivoI from './MotivoI';
import MotivoE from './MotivoE';
import Empresas from './Empresas';
import Parametro from './Parametro';
import Correo from './Correo';

const AdminView = () => {
    return (
        <div>
            <h1>Administración</h1>
            <Row>
                <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                    <MotivoI />
                </Col>
                <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                    <MotivoE />
                </Col>
                <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                    <Empresas />
                </Col>
            </Row>
            <Row>
                <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                    <Parametro />
                </Col>
                <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
                    <Correo />
                </Col>
            </Row>
        </div >
    )
}

export default AdminView;