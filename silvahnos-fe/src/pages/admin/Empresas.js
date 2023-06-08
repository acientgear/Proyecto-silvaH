import { Row, Col, Table, Card, Button } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const Empresas = () => {
    const [empresas, setempresas] = useState([]);

    const getEmpresas = async () => {
        try {
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.get(url);
            if (response.status === 200) {
                setempresas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getEmpresas();
    }, []);

    return (
        <div>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Empresas registradas</Card.Title>
                    <Table striped responsive="sm" hover bordered>
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Rut</th>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Dirección</th>
                            </tr>
                        </thead>
                        <tbody>
                            {empresas.map((empresa, index) => (
                                <tr key={empresa.id}>
                                    <td >{empresa.rut}</td>
                                    <td >{empresa.nombre}</td>
                                    <td >{empresa.direccion}</td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <Button href="/crearEmpresa">Registrar empresa</Button>
                </Card.Body>
            </Card>
        </div >
    )
}

export default Empresas;