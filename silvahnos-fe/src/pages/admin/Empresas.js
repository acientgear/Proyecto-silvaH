import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";
import {BsBoxArrowRight} from "react-icons/bs";
import FormEmpresa from '../../components/FormEmpresa';
import Cookies from 'js-cookie';
import Alerta from '../../components/Alerta';

const Empresas = () => {
    const [empresas, setempresas] = useState([]);
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

    const handleShowDelete = (empresa) => {
        setEditedItem(empresa);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false);
    };

    const handleShowEdit = (empresa) => {
        setEditedItem(empresa);
        setShowEdit(true);
    };

    const createEmpresa = async (editedItem) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseCreate();
                getEmpresas();
                Alerta.fire({
                    icon: 'success',
                    title: 'Empresa creada exitosamente'
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const updateEmpresa = async (editedItem) => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseEdit();
                getEmpresas();
                Alerta.fire({
                    icon: 'success',
                    title: 'Empresa editada exitosamente'
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteEmpresa();
    };

    const deleteEmpresa = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, editedItem,config);
            if (response.status === 200) {
                handleCloseDelete();
                getEmpresas();
                Alerta.fire({
                    icon: 'success',
                    title: 'Empresa eliminada exitosamente'
                });
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const [editedItem, setEditedItem] = useState({
        id: null,
        nombre: '',
        rut: '',
        direccion: '',
        borrado: false,
    });

    const defaultItem = {
        id: null,
        nombre: '',
        rut: '',
        direccion: '',
        borrado: false,
    }

    const getEmpresas = async () => {
        try {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            }; 
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.get(url,config);
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
        <div style={{width: "100%"}}>
            <Card className="cardsH">
                <Card.Body>
                    <Card.Title >Empresas registradas</Card.Title>
                    <Table  responsive="sm" hover >
                        <thead>
                            <tr>
                                <th style={{ width: '100px' }}>Rut</th>
                                <th style={{ width: '100px' }}>Nombre</th>
                                <th style={{ width: '100px' }}>Dirección</th>
                                <th style={{ width: '100px' }}>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {empresas.map((empresa, index) => (
                                <tr key={empresa.id}>
                                    <td >{empresa.rut}</td>
                                    <td >{empresa.nombre}</td>
                                    <td >{empresa.direccion}</td>
                                    <td>
                                        <a href="#se" 
                                            onClick={() => handleShowEdit(empresa)}
                                            style={{ cursor: "pointer", 
                                                    marginRight: 2, 
                                                    color: "#0d6efd" }}
                                        >
                                            <AiFillEdit />
                                        </a>
                                        <a href="#sd"
                                            onClick={() => handleShowDelete(empresa)} 
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
                            onClick={handleShowCreate}  
                        >
                            Registrar empresa <BsBoxArrowRight/>
                        </a>
                    </div>
                </Card.Body>
            </Card>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar empresa</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar la empresa?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>

            {/*Modal para crear*/}
            <Modal show={showCreate} onHide={handleCloseCreate}>
                <Modal.Header closeButton>
                    <Modal.Title>Crear Empresa</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormEmpresa
                        empresa={editedItem}
                        postEmpresa={createEmpresa}
                        handleClose={handleCloseCreate}
                    />
                </Modal.Body>
            </Modal>
                                
            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Empresa</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormEmpresa
                        empresa={editedItem}
                        postEmpresa={updateEmpresa}
                        handleClose={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default Empresas;