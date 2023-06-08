import { Row, Col, Table, Card, Button } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const MotivoI = () => {
    const [motivosI, setMotivosI] = useState([]);

    const getMotivosI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.get(url);
            if (response.status === 200) {
                setMotivosI(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getMotivosI();
    }, []);

    return (
        <div>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Motivos de ingresos registrados</Card.Title>
                    <Table striped responsive="sm" hover bordered>
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Descripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            {motivosI.map((motivo, index) => (
                                <tr key={motivo.id}>
                                    <td >{motivo.nombre}</td>
                                    <td >{motivo.descripcion}</td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <Button href="/crearMotivoI">Registrar motivo de ingreso</Button>
                </Card.Body>
            </Card>
        </div >
    )
}

export default MotivoI;