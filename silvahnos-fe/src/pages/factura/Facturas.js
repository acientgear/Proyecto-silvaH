import axios from 'axios';
import { useCallback, useEffect, useState } from 'react';
import { Button, Col, Container, Modal, Pagination, Row, Table } from 'react-bootstrap';
import urlweb from '../../config/config';
import InputMonth from '../../components/InputMonth';
import FormFactura from '../../components/FormFactura';

const Facturas = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const pageSize = 6;

    const [mes, setMes] = useState((new Date()).getMonth() + 1);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    const [facturas, setFacturas] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const [validated, setValidated] = useState(false);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    }

    const paginatedData = facturas.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
    );

    const handleCloseDelete = () => {
        setEditedItem(defaultItem);
        setShowDelete(false);
    }

    const handleShowDelete = (factura) => {
        setEditedItem(factura);
        setShowDelete(true);
    }

    const handleCloseEdit = () => {
        setEditedItem(defaultItem);
        setShowEdit(false)
    };

    const handleShowEdit = (factura) => {
        setEditedItem(factura);
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
            updateFactura();
            setValidated(true);
        }
    };

    const handleDelete = () => {
        editedItem.borrado = true;
        deleteFactura();
    };

    const handleChangeMes = (e) => {
        setMes(e.target.value);
    };

    const handleChangeAnio = (e) => {
        setAnio(e.target.value);
    };

    const getFacturas = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/facturas/' + anio + '/' + mes;
            console.log(url);
            const response = await axios.get(url);
            if (response.status === 200) {
                setFacturas(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio, mes]);

    const updateFactura = async () => {
        try {
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseEdit();
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const deleteFactura = async () => {
        try {
            let url = 'http://' + urlweb + '/facturas';
            const response = await axios.post(url, editedItem);
            if (response.status === 200) {
                handleCloseDelete();
                getFacturas();
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const formatearFecha = (fecha) => {
        if (fecha === null) {
            return "---"
        }
        let fechaC = fecha.split('T')[0];
        fechaC = fechaC.split('-');
        return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
    };


    const [editedItem, setEditedItem] = useState({
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        observaciones: '',
        fecha_emision: null,
        fecha_vencimiento: null,
        fecha_pago: null,
        numero_factura: null,
    });

    const defaultItem = {
        id: null,
        borrado: false,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        observaciones: '',
        fecha_emision: null,
        fecha_vencimiento: null,
        fecha_pago: null,
        numero_factura: null,
    }

    useEffect(() => {
        getFacturas();
    }, [getFacturas]);

    return (
        <>
            <Container>
                <Row>
                    <Col><h1>Facturas</h1></Col>
                    <Col className='d-flex align-items-center'>
                        <InputMonth
                            mes={mes}
                            anio={anio}
                            onChangeAnio={handleChangeAnio}
                            onChangeMes={handleChangeMes}
                            get={getFacturas}
                        />
                    </Col>
                    <Col className='d-flex align-items-center justify-content-md-end'><Button href="/crearFactura" style={{ backgroundColor: "rgb(165, 192, 221)", color: "black", border: "none", fontWeight: "bold" }}>Registrar una factura</Button></Col>
                </Row>
                <Row>
                    <Col>
                        <Table responsive="sm" striped hover>
                            <thead>
                                <tr>
                                    <th>N° factura</th>
                                    <th>Fecha emision</th>
                                    <th>Fecha vencimiento</th>
                                    <th>Fecha pago</th>
                                    <th>Observaciones</th>
                                    <th style={{width:"200px"}}>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {paginatedData.map((factura) => (
                                    <tr key={factura.id}>
                                        <td>{factura.numero_factura}</td>
                                        <td>{formatearFecha(factura.fecha_emision)}</td>
                                        <td>{formatearFecha(factura.fecha_vencimiento)}</td>
                                        <td>{formatearFecha(factura.fecha_pago)}</td>
                                        <td>{factura.observaciones}</td>
                                        <td>
                                            <Button variant='primary' onClick={() => handleShowEdit(factura)} style={{ marginRight: 2 }}>Editar</Button>
                                            <Button variant='danger' onClick={() => handleShowDelete(factura)}>Eliminar</Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        <Pagination>
                            {[...Array(Math.ceil(facturas.length / pageSize)).keys()].map((page) => (
                                <Pagination.Item
                                    key={page + 1}
                                    active={page + 1 === currentPage}
                                    onClick={() => handlePageChange(page + 1)}
                                >
                                    {page + 1}
                                </Pagination.Item>
                            ))}
                        </Pagination>
                    </Col>
                </Row>
            </Container>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Factura</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormFactura 
                        factura={editedItem}
                        validated={validated}
                        modal={true}
                        handleChange={handleChange}
                        handleSubmit={handleSubmit}
                        handleCloseEdit={handleCloseEdit}    
                    />
                </Modal.Body>
            </Modal>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar Factura</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar La factura?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default Facturas;