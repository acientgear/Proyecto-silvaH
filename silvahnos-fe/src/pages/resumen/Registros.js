import { React, useCallback, useEffect, useState } from 'react'
import { Card, ListGroup, Badge } from 'react-bootstrap'
import urlweb from '../../config/config';
import axios from 'axios';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';
import Cookies from 'js-cookie';

const Registros = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };

    let fechaAcual = new Date();
    let anio = fechaAcual.getFullYear();
    let mes = fechaAcual.getMonth() + 1;
    const idMes = mes.toLocaleString('es-ES', { month: 'long' });
    const nombreMes = (Sem1.concat(Sem2))[idMes - 1];

    const formatearFecha = (fecha) => {
        let fechaC = fecha.split('T')[0];
        fechaC = fechaC.split('-');
        return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
    };

    const [registros, setRegistros] = useState([]);

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getRegistros = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/registros/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setRegistros(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    useEffect(() => {
        getRegistros();
    }, [getRegistros]);

    console.log(registros);

    return (
        <Card  style={{ margin: "10px 0 10px 0", maxHeight: '469px', overflowY: 'scroll', scrollbarWidth: 'thin', scrollbarColor: 'gray lightgray' }}>
            <Card.Body>
                <Card.Subtitle className="mb-2 text-muted" style={{textAlign:"center"}}>Registros de {nombreMes} del {anio}</Card.Subtitle>
                {registros.map((registro, index) => (
                    <div key={index}>
                        <ListGroup.Item key={`registro-${index}`} as="li" className="d-flex justify-content-between align-items-start">
                            <div className="ms-2 me-auto">
                                <div className="fw-bold">{formatearFecha(registro.fecha)}</div>
                                {registro.descripcion}
                            </div>
                            <Badge bg={registro.tipo === 'egreso' ? '#FBE6DD' : '#E6F4DD'} pill style={{ color: "black", backgroundColor: registro.tipo === 'egreso' ? '#FBE6DD' : '#E6F4DD' }}>
                                {formatoMonto(registro.monto)}
                            </Badge>
                        </ListGroup.Item>
                        <p></p>
                    </div>
                ))}
            </Card.Body>
        </Card>
    )
}

export default Registros
