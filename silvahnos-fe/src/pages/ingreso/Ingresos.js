import axios from 'axios';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Modal, Row, Table } from 'react-bootstrap';

const Ingresos = () => {
    const [ingresos, setIngresos] = useState([]);

    const [showDelete, setShowDelete] = useState(false);
    const [showEdit, setShowEdit] = useState(false);

    const handleCloseDelete = () => setShowDelete(false);
    const handleShowDelete = () => setShowDelete(true);

    const handleCloseEdit = () => setShowEdit(false);
    const handleShowEdit = () => setShowEdit(true);

    const getIngresos = async () => {
        try {
            let url = 'http://localhost:8090/ingresos';
            const response = await axios.get(url);
            if (response.status === 200) {
                setIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getIngresos();
    }, []);

    return (
        <>
            <Container>
                <Row>
                    <Col>
                        <h1>Ingresos</h1>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped hover>
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Monto</th>
                                    <th>Origen</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {ingresos.map((ingreso) => (
                                    <tr key={ingreso.id}>
                                        <td>{ingreso.fecha_creacion}</td>
                                        <td>{ingreso.monto}</td>
                                        <td>{ingreso.patente}</td>
                                        <td>{ingreso.descripcion}</td>
                                        <td>
                                            <Button variant='primary' onClick={handleShowEdit} style={{marginRight: 2}}>Editar</Button>
                                            <Button variant='danger' onClick={handleShowDelete}>Eliminar</Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>

            {/*Modal para editar*/}
            <Modal show={showEdit} onHide={handleCloseEdit}>
                <Modal.Header closeButton>
                    <Modal.Title>Editar Ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>Editar ingreso</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseEdit}>Cerrar</Button>
                    <Button variant='primary' onClick={handleCloseEdit}>Guardar</Button>
                </Modal.Footer>
            </Modal>

            {/*Modal para eliminar*/}
            <Modal show={showDelete} onHide={handleCloseDelete}>
                <Modal.Header closeButton>
                    <Modal.Title>Eliminar Ingreso</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>¿Está seguro que desea eliminar el ingreso?</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant='secondary' onClick={handleCloseDelete}>Cerrar</Button>
                    <Button variant='danger' onClick={handleCloseDelete}>Eliminar</Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default Ingresos;