import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";
import {BsBoxArrowRight} from "react-icons/bs";
import FormMotivo from '../../components/FormMotivo';
import Cookies from 'js-cookie';

const MotivoI = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };  
    const [motivosI, setMotivosI] = useState([]);
    const [showCreate, setShowCreate] = useState(false);
    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

    const handleCloseCreate = () => {
        setEditedItem(defaultItem);
        setShowCreate(false);
    }

    const handleShowCreate = () => {

        setEditedItem(defaultItem);
        setShowCreate(true);
    }

    const handleCloseDelete = () => {
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (motivoI) => {
        setEditedItem(motivoI);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false);
    };

    const handleShowEdit = (motivoI) => {
        setEditedItem(motivoI);
        setShowEdit(true);
    };

    const updatemotivoI = async (editedItem) => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseEdit();
                handleCloseCreate();
                getMotivosI();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const formatearObservacion = (observacion) => {
        return (
            <div 
                style={{ 
                    maxWidth: "100px",
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                    whiteSpace: "nowrap"
                    }}
                title={observacion}
                >
                {observacion}
            </div>
        );
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteMotivoI();
    };

    const deleteMotivoI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseDelete();
                getMotivosI();
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

    const getMotivosI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.get(url,config);
            if (response.status === 200) {
                setMotivosI(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getMotivosI();
    });


    return (
        <div style={{width: "100%"}} >
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Motivos de ingresos registrados</Card.Title>
                    <Table responsive="sm" hover>
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Descripción</th>
                                <th style={{ width: '100px' }}>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {motivosI.map((motivo, index) => (
                                <tr key={motivo.id}>
                                    <td >{motivo.nombre}</td>
                                    <td >{formatearObservacion(motivo.descripcion)}</td>
                                    <td>
                                        <a href="#se"
                                            onClick={() => handleShowEdit(motivo)} 
                                            style={{ cursor: "pointer", 
                                                    marginRight: 2, 
                                                    color: "#0d6efd" }} >
                                                <AiFillEdit />
                                        </a>
                                        <a href="#sd"
                                            onClick={() => handleShowDelete(motivo)} 
                                            style={{ cursor: "pointer", 
                                                    marginRight: 2, 
                                                    color: "#dc3545" }}>
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
                                Registrar motivo de ingreso <BsBoxArrowRight/>
                        </a>
                    </div>
                </Card.Body>
            </Card>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar motivo de ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar el motivo de ingreso?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>

            {/*Modal para crear*/}
            <Modal show={showCreate} onHide={handleCloseCreate}>
                <Modal.Header closeButton>
                    <Modal.Title>Crear motivo de ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormMotivo
                        motivo={editedItem}
                        postMotivo={updatemotivoI}
                        handleClose={handleCloseCreate}
                    />
                </Modal.Body>
            </Modal>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar motivo de ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormMotivo
                        motivo={editedItem}
                        postMotivo={updatemotivoI}
                        handleClose={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default MotivoI;