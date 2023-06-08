import { Row, Col, Table, Card, Button } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const MotivoE = () => {
    const [motivosE, setmotivosE] = useState([]);

    const getmotivosE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.get(url);
            if (response.status === 200) {
                setmotivosE(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getmotivosE();
    }, []);

    return (
        <div>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Motivos de egresos registrados</Card.Title>
                    <Table striped responsive="sm" hover bordered>
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Descripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            {motivosE.map((motivo, index) => (
                                <tr key={motivo.id}>
                                    <td >{motivo.nombre}</td>
                                    <td >{motivo.descripcion}</td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <Button href="/crearMotivoE">Registrar motivo de egreso</Button>
                </Card.Body>
            </Card>
        </div >
    )
}

export default MotivoE;