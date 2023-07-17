import axios from 'axios';
import { Col, Container, Row } from 'react-bootstrap';
import { useState, useCallback, useEffect } from 'react';
import FormEgreso from '../../components/FormEgreso';
import urlweb from '../../config/config';

function CrearEgreso() {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 
    const [validated, setValidated] = useState(false);
    const [egresos, setEgresos] = useState([]);
    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());

    const [egreso, setEgreso] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        monto: '',
        motivo: {
            id: 0
        },
        descripcion: ''
    });

    const [movimiento, setMovimiento] = useState({
        id: null,
        tipo: "",
        usuario: {
            id: null
        },
        egreso: {
            id: null
        },
        ingreso: {
            id: null
        },
        factura:{
            id: null
        },
        fecha_creacion: null
    });

    const handleChange = (e) => {
        setEgreso({
            ...egreso,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            createEgreso();
            setValidated(true);
        }
    };

    const createEgreso = async () => {
        try {
            let url = "http://"+urlweb+"/egresos";
            let response = await axios.post(url, egreso,config);
            if (response.status === 200) {                
                getEgresos();
                createMovimiento();
                window.location.href = "/egresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    const createMovimiento = async () => {
        movimiento.tipo = "Creación";
        console.log(egresos[0].id);
        movimiento.egreso = egresos[0];
        movimiento.ingreso = null;
        try {
            let url = "http://"+urlweb+"/movimientos";
            let response = await axios.post(url, movimiento,config);
            if (response.status === 200) {
                window.location.href = "/egresos";
            }
        } catch (err) {
            console.log(err);
        }
    }

    const getEgresos = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/egresos/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setEgresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    useEffect(() => {
        getEgresos();
    }, [getEgresos]);

    return (
        <Container>
            <Row>
                <Col>
                    <h1>Crear Egreso</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <FormEgreso 
                        egreso={egreso}
                        setEgreso={setEgreso}
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

export default CrearEgreso;