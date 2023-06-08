import { Container, Row, Col } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import FormMotivoE from '../../components/FormMotivoE';

const CrearMotivoE = () => {
    const [validated, setValidated] = useState(false);

    const [motivoE, setMotivoE] = useState({
        id: null,
        nombre: '',
        descripcion: '',
        borrado: 0
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            createMotivoE();
            setValidated(true);
        }
    };

    const handleChange = (e) => {
        setMotivoE({
            ...motivoE,
            [e.target.name]: e.target.value
        });
    }

    const createMotivoE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.post(url, motivoE);
            if (response.status === 200) {
                console.log(response.data);
                window.location.href = "/administracion";
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    return (
        <Container>
            <Row>
                <Col>
                    <FormMotivoE
                        motivoE={motivoE}
                        validated={validated}
                        modal={false}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                    />
                </Col>
            </Row>
        </Container >
    );
}

export default CrearMotivoE;