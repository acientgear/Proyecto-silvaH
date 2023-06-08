import { Row, Col} from 'react-bootstrap'
import MotivoI from './MotivoI';
import MotivoE from './MotivoE';
import Empresas from './Empresas';

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
        </div >
    )
}

export default AdminView;