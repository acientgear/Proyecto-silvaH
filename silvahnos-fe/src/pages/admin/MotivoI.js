import { Table, Card, Button, Modal } from 'react-bootstrap'
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import { AiFillEdit, AiFillDelete } from "react-icons/ai";
import FormMotivoI from '../../components/FormMotivoI';

const MotivoI = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [motivosI, setMotivosI] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    }

    const paginatedData = motivosI.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

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
        setValidated(false);
        setEditedItem(motivoI);
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
            updatemotivoI();
            setValidated(true);
        }
    };

    const updatemotivoI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseEdit();
                getMotivosI();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteMotivoI();
    };

    const deleteMotivoI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, editedItem);
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
                                <th style={{ width: '100px' }}>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {motivosI.map((motivo, index) => (
                                <tr key={motivo.id}>
                                    <td >{motivo.nombre}</td>
                                    <td >{motivo.descripcion}</td>
                                    <td>
                                        <a style={{ cursor: "pointer", marginRight: 2, color: "#0d6efd" }} onClick={() => handleShowEdit(motivo)}><AiFillEdit /></a>
                                        <a style={{ cursor: "pointer", marginRight: 2, color: "#dc3545" }} onClick={() => handleShowDelete(motivo)}><AiFillDelete /></a>
                                    </td>
                                </tr>
                            )
                            )}
                        </tbody>
                    </Table>
                    <Button href="/crearMotivoI">Registrar motivo de ingreso</Button>
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

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar motivo de ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormMotivoI
                        motivoI={editedItem}
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

export default MotivoI;