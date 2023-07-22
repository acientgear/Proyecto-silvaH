import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';
import FormUsuario from '../../components/FormUsuario';
import Alerta from '../../components/Alerta';

const Usuario = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };
    const [usuarios, setUsuarios] = useState([]);
    const [show, setShow] = useState(false);
    const usuario = {
        id: null,
        correo: '',
        nombre: '',
        usuario: '',
        contrasena: ''
    };

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const getUsuarios = async () => {
        try {
            let url = 'http://' + urlweb + '/usuarios';
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setUsuarios(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const createUsuario = async (usuario) => {
        try {
            let url = 'http://' + urlweb + '/usuarios';
            const response = await axios.post(url, usuario, config);
            if (response.status === 200) {
                handleClose();
                getUsuarios();
                Alerta.fire({
                    icon: 'success',
                    title: 'Usuario creado exitosamente'
                });
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
                                <th style={{ width: '100px' }}>Rol</th>
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
                <Card.Footer>
                    <Button variant="primary" onClick={handleShow}>Crear usuario</Button>
                </Card.Footer>
            </Card>

            {/* Modal para crear*/}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Crear usuario</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormUsuario
                        usuario={usuario}
                        postUsuario={createUsuario}
                        handleClose={handleClose}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default Usuario;