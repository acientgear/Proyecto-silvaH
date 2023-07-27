import { Table, Card, Button, Modal, Row, Col } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';
import FormUsuario from '../../components/FormUsuario';
import Alerta from '../../components/Alerta';
import { BiUserCircle } from "react-icons/bi";

const Usuario = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [usuarioLogged, setUsuarioLogged] = useState({});

    const [show, setShow] = useState(false);
    const [showUpdate, setShowUpdate] = useState(false);

    const usuario = {
        id: null,
        correo: '',
        nombre: '',
        usuario: '',
        contrasena: ''
    };

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleCloseUpdate = () => setShowUpdate(false);
    const handleShowUpdate = () => setShowUpdate(true);

    const getUsuarios = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
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
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
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

    const updateUsuario = async (usuario) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/usuarios';
            const response = await axios.post(url, usuario, config);
            if (response.status === 200) {
                handleCloseUpdate();
                getUsuarios();
                Alerta.fire({
                    icon: 'success',
                    title: 'Usuario actualizado exitosamente'
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const getUsuarioLogged = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };
            let url = 'http://' + urlweb + '/usuarios/getUserLogueado';
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setUsuarioLogged(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getUsuarioLogged();
        getUsuarios();
    }, []);

    return (
        <div style={{ width: "100%" }}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Usuario logueado</Card.Title>
                    <Row>
                        <Col xs="auto">
                            <BiUserCircle size={100} />
                        </Col>
                        <Col>
                            <div >
                                <p style={{ textAlign: "left" }}><strong>Nombre completo: </strong></p>
                                <p style={{ textAlign: "left" }}>{usuarioLogged.nombre}</p>
                            </div>
                            <div>
                                <p style={{ textAlign: "left" }}><strong>Nombre de usuario: </strong></p>
                                <p style={{ textAlign: "left" }}>{usuarioLogged.usuario}</p>
                            </div>
                        </Col>
                        <Col>
                            <div>
                                <p style={{ textAlign: "left" }}><strong>Correo: </strong></p>
                                <p style={{ textAlign: "left" }}>{usuarioLogged.correo}</p>
                            </div>
                            <div>
                                <Button variant="primary" onClick={handleShowUpdate}>Editar</Button>
                            </div>
                        </Col>
                    </Row>


                </Card.Body>
            </Card>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Usuarios registrados</Card.Title>
                    <Table responsive="sm" hover >
                        <thead>
                            <tr>
                                <th style={{ width: '33%' }}>Correo</th>
                                <th style={{ width: '33%' }}>Nombre completo</th>
                                <th style={{ width: '33%' }}>Nombre usuario</th>
                            </tr>
                        </thead>
                        <tbody>
                            {usuarios.map((usuario) => (
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

            {/*Modal para actualizar*/}
            <Modal show={showUpdate} onHide={handleCloseUpdate}>
                <Modal.Header closeButton>
                    <Modal.Title>Actualizar usuario</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormUsuario
                        usuario={usuarioLogged}
                        postUsuario={updateUsuario}
                        handleClose={handleCloseUpdate}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default Usuario;