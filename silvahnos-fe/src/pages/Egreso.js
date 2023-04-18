import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button, Table } from 'react-bootstrap';

const Egreso = () => {

    const [egreso, setEgreso] = useState({
        id: null,
        monto: "",
        patente: "",
        descripcion: "",
        borrado: false,
        fechaBorrado: "",
        fechaCreacion: "",
        fechaModificacion: ""
    });

    const getEgresos = async () => {
        try {
            let url = "http://localhost:8090/egresos";
            let response = await axios.get(url);
            if (response.statur === 200) {
                setEgreso(response.data);
            }
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getEgresos();
    }, []);

    return (
        <Container>
            <Row>
                <Col><h1>Egresos</h1></Col>
            </Row>
            <Row>
                <Col>
                    <Table striped className='mt-4'>
                        <thead>
                            <tr>
                                <th>Monto</th>
                                <th>Patente</th>
                                <th>Descripcion</th>
                                <th>Fecha Creacion</th>
                                <th>Fecha Modificacion</th>
                                <th>Fecha Borrado</th>
                            </tr>
                        </thead>
                        <tbody>
                            {egreso.map((egreso) => (
                                <tr key={egreso.id}>
                                    <td>{egreso.monto}</td>
                                    <td>{egreso.patente}</td>
                                    <td>{egreso.descripcion}</td>
                                    <td>{egreso.fechaCreacion}</td>
                                    <td>{egreso.fechaModificacion}</td>
                                    <td>{egreso.fechaBorrado}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}