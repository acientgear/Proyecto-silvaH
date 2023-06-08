import { Container, Row, Col } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import FormMotivoI from '../../components/FormMotivoI';

const CrearMotivoI = () => {
    const [validated, setValidated] = useState(false);

    const [motivoI, setMotivoI] = useState({
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
            createMotivoI();
            setValidated(true);
        }
    };

    const handleChange = (e) => {
        setMotivoI({
            ...motivoI,
            [e.target.name]: e.target.value
        });
    }

    const createMotivoI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, motivoI);
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
                    <FormMotivoI
                        motivoI={motivoI}
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

export default CrearMotivoI;