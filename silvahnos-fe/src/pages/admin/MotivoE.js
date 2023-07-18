import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";
import {BsBoxArrowRight} from "react-icons/bs";
import FormMotivo from '../../components/FormMotivo';

const MotivoE = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    };  
    const [motivosE, setMotivosE] = useState([]);
    const [showCreate, setShowCreate] = useState(false);
    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handleCloseCreate = () => {
        setEditedItem(defaultItem);
        setShowCreate(false);
    }

    const handleShowCreate = () => {
        setValidated(false);
        setEditedItem(defaultItem);
        setShowCreate(true);
    }

    const handleCloseDelete = () => {
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (motivoE) => {
        setEditedItem(motivoE);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false);
    };

    const handleShowEdit = (motivoE) => {
        setValidated(false);
        setEditedItem(motivoE);
        setShowEdit(true);
    };

    const handleChange = (e) => {
        setEditedItem({
            ...editedItem,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.stopPropagation();
        } else {
            updatemotivoE();
            handleCloseEdit();
            handleCloseCreate();
            setValidated(true);
        }
    };

    const updatemotivoE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                getMotivosE();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteMotivoE();
    };

    const deleteMotivoE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseDelete();
                getMotivosE();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        nombre: '',
        descripcion: '',
        borrado: false,
    });

    const defaultItem = {
        id: null,
        nombre: '',
        descripcion: '',
        borrado: false,
    }

    const getMotivosE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.get(url,config);
            if (response.status === 200) {
                setMotivosE(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getMotivosE();
    }, []);

    return (
        <div style={{width: "100%"}}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Motivos de egresos registrados</Card.Title>
                    <Table  responsive="sm" hover >
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Descripción</th>
                                <th style={{ width: '100px' }}>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {motivosE.map((motivo, index) => (
                                <tr key={motivo.id}>
                                    <td >{motivo.nombre}</td>
                                    <td >{motivo.descripcion}</td>
                                    <td>
                                        <a href="#se"
                                            onClick={() => handleShowEdit(motivo)} 
                                            style={{ cursor: "pointer",
                                                    marginRight: 2, 
                                                    color: "#0d6efd" }} 
                                        >
                                            <AiFillEdit />
                                        </a>
                                        <a href="#sd"
                                            onClick={() => handleShowDelete(motivo)} 
                                            style={{ cursor: "pointer", 
                                                    marginRight: 2, 
                                                    color: "#dc3545" }} 
                                        >
                                            <AiFillDelete />
                                        </a>
                                    </td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <div className="registrar" >
                        <a className="registrar-anchor" 
                            href="#"
                            onClick={handleShowCreate}>
                                Registrar motivo de egreso <BsBoxArrowRight/>
                        </a>
                    </div>
                    </Card.Body>
            </Card>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar motivo de egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar el motivo de egreso?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>

            {/*Modal para crear*/}
            <Modal show={showCreate} onHide={handleCloseCreate}>
                <Modal.Header closeButton>
                    <Modal.Title>Crear motivo de egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormMotivo
                        motivo={editedItem}
                        validated={validated}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleClose={handleCloseCreate}
                    />
                </Modal.Body>
            </Modal>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar motivo de egreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormMotivo
                        motivo={editedItem}
                        validated={validated}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleClose={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default MotivoE;