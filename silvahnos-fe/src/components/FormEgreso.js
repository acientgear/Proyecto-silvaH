import { Form, Button, Row, Col } from 'react-bootstrap';
import CategoriasEgreso from './data/CategoriasEgreso';

const FormEgreso = ({ egreso, setEgreso, validated, modal, handleChange, handleSubmit, handleCloseEdit }) => {
    const handleMotivo = (e) => {
        setEgreso({
            ...egreso,
            "motivo": {
                "id": e.target.value
            }
        });
    }

    const motivos = CategoriasEgreso();

    const modalFooter = () => {
        return (
            <Row>
                <Col>
                    <hr></hr>
                    <div className='d-flex justify-content-end'>
                        <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleCloseEdit}>Cerrar</Button>
                        <Button variant='primary' type='submit'>Guardar</Button>
                    </div>
                </Col>
            </Row>
        )
    }

    const formFooter = () => {
        return (
            <Row>
                <Col className='d-flex justify-content-end'>
                    <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                    <Button variant='success' type='submit'>Guardar</Button>
                </Col>
            </Row>
        )
    }

    return (
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Row xs={1} sm={2}>
                <Col>
                    <Form.Group className="mb-3" controlId="formEgreso">
                        <Form.Label>Motivo</Form.Label>
                        <Form.Select
                            aria-label="select"
                            required
                            onChange={handleMotivo}
                            value={egreso.motivo.id}
                        >
                            <option value="0" >Seleccione una opción</option>
                            {motivos.map((categoria) => (
                                <option key={categoria.id} value={categoria.id}>
                                    {categoria.nombre}
                                </option>
                            ))}
                        </Form.Select>
                        <Form.Control.Feedback type="invalid">
                            Seleccione una opción.
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Group className="mb-3" controlId="formEgreso">
                        <Form.Label>Monto</Form.Label>
                        <Form.Control name="monto" required
                            isValid={1000000000 > egreso.monto && egreso.monto > 0}
                            isInvalid={egreso.monto <= 0 || egreso.monto > 1000000000}
                            min={1}
                            max={1000000000}
                            type="number" placeholder="Ingrese monto" onChange={handleChange}
                            value={egreso.monto}
                        />
                        <Form.Control.Feedback type="invalid">
                            El monto debe ser mayor a $ 0 y menor a $ 1.000.000.000
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Descripción</Form.Label>
                        <Form.Control name="descripcion"
                            required
                            isValid={255 > egreso.descripcion.length && egreso.descripcion.length > 0}
                            isInvalid={egreso.descripcion.length > 255 || egreso.descripcion.length === 0}
                            as="textarea" placeholder="Ingrese descripción" onChange={handleChange}
                            value={egreso.descripcion}
                        />
                        <span style={{ color: "#adb5bd" }}>{egreso.descripcion.length + '/255'}</span>
                        <Form.Control.Feedback type="invalid">
                            Ingrese una descripción valida
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>
            </Row>
            {modal ? modalFooter() : formFooter()}
        </Form>
    )
}

export default FormEgreso;