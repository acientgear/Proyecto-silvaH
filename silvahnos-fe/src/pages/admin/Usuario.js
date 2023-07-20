import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const Usuario = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };
    const [usuarios, setUsuarios] = useState([]);

    const getUsuarios = async () => {
        try {
            let url = 'http://' + urlweb + '/usuarios';
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setUsuarios(response.data);
                console.log(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getUsuarios();
    }, []);

    return (
        <div style={{ width: "100%" }}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Usuarios registrados</Card.Title>
                    <Table responsive="sm" hover >
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Correo</th>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Usuario ??</th>
                            </tr>
                        </thead>
                        <tbody>
                            {usuarios.map((usuario, index) => (
                                <tr key={usuario.id}>
                                    <td >{usuario.correo}</td>
                                    <td >{usuario.nombre}</td>
                                    <td >{usuario.usuario}</td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </div >
    )
}

export default Usuario;