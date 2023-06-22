import { Container, Row, Col } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import FormEmpresa from '../../components/FormEmpresa';
import { checkRut } from 'react-rut-formatter';

const CrearEmpresa = () => {
    const [validated, setValidated] = useState(false);

    const [empresa, setempresa] = useState({
        id: null,
        rut: '',
        nombre: '',
        direccion: '',
        borrado: 0
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            if(!checkRut(empresa.rut) || empresa.rut.length > 12){
                e.stopPropagation();
                return;
            }
            createEmpresa();
            setValidated(true);
        }
    };

    const handleChange = (e) => {
        setempresa({
            ...empresa,
            [e.target.name]: e.target.value
        });
    }

    const createEmpresa = async () => {
        try {
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, empresa);
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
                    <FormEmpresa
                        empresa={empresa}
                        validated={validated}
                        modal={false}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        setempresa={setempresa}
                    />
                </Col>
            </Row>
        </Container >
    );
}

export default CrearEmpresa;