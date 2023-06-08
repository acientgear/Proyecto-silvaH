import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";
import FormEmpresa from '../../components/FormEmpresa';

const Empresas = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [empresas, setempresas] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    }

    const paginatedData = empresas.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

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
        setValidated(false);
        setEditedItem(empresa);
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
            updateEmpresa();
            setValidated(true);
        }
    };

    const updateEmpresa = async () => {
        try {
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseEdit();
                getEmpresas();
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
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseDelete();
                getEmpresas();
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

    console.log(empresas);

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
                                        <a style={{ cursor: "pointer", marginRight: 2, color: "#0d6efd" }} onClick={() => handleShowEdit(empresa)}><AiFillEdit /></a>
                                        <a style={{ cursor: "pointer", marginRight: 2, color: "#dc3545" }} onClick={() => handleShowDelete(empresa)}><AiFillDelete /></a>
                                    </td>

                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <Button href="/crearEmpresa">Registrar empresa</Button>
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

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Empresa</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormEmpresa
                        empresa={editedItem}
                        validated={validated}
                        modal={true}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleCloseEdit={handleCloseEdit}
                    />
                </Modal.Body>
            </Modal>
        </div >
    )
}

export default Empresas;