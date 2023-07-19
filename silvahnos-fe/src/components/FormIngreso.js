import CategoriasIngreso from "./data/CategoriasIngreso";
import { Button, Col, Form, Row } from "react-bootstrap";

const FormIngreso = ({ ingreso, setIngreso, validated, modal, handleChange, handleCloseEdit, handleSubmit }) => {

    const handleMotivo = (e) => {
        setIngreso({
            ...ingreso,
            "motivo": {
                "id": e.target.value
            }
        });
    }

    const motivos = CategoriasIngreso();

    const modalFooter = () => {
        return (
            <Row>
                <Col>
                    <hr></hr>
                    <div className="d-flex justify-content-end">
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
                <Col className="d-flex justify-content-end">
                    <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                    <Button variant='success' type='submit'>Guardar</Button>
                </Col>
            </Row>
        )
    }

    return (
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Row xs={1} sm={3}>
                <Col>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Patente</Form.Label>
                        <Form.Control name="patente"
                            required
                            isValid={6 > ingreso.patente.length && ingreso.patente.length > 0}
                            isInvalid={ingreso.patente.length > 6 || ingreso.patente.length === 0}
                            type="text"
                            placeholder="Ingrese la patente"
                            value={ingreso.patente}
                            onChange={handleChange}
                        />
                        <span style={{ color: "#adb5bd" }}>{ingreso.patente.length + '/6'}</span>
                        <Form.Control.Feedback type="invalid">
                            Ingrese una patente valida
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>

                <Col>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Motivo</Form.Label>
                        <Form.Select name="motivo"
                            required
                            aria-label="select"
                            onChange={handleMotivo}
                            value={ingreso.motivo.id}
                        >
                            <option key={0} value="">Seleccione una opción</option>
                            {motivos.map((categoria) => (
                                <option key={categoria.id} value={categoria.id}>{categoria.nombre}</option>
                            ))}
                        </Form.Select>
                        <Form.Control.Feedback type="invalid">
                            Seleccione una opción valida
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>

                <Col>
                    <Form.Group className='mb-3' controlId='formMonto'>
                        <Form.Label>Monto</Form.Label>
                        <Form.Control name="monto"
                            required
                            isValid={1000000000 > ingreso.monto && ingreso.monto > 0}
                            isInvalid={ingreso.monto <= 0 || ingreso.monto > 1000000000}
                            min={1}
                            max={1000000000}
                            type='number'
                            value={ingreso.monto}
                            onChange={handleChange} />
                        <Form.Control.Feedback type="invalid">
                            Ingrese un monto entre $1 y $1.000.000.000
                        </Form.Control.Feedback>
                    </Form.Group>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form.Group className='mb-3' controlId='formDescripcion'>
                        <Form.Label>Descripción</Form.Label>
                        <Form.Control name="descripcion"
                            required
                            isValid={255 > ingreso.descripcion.length && ingreso.descripcion.length > 0}
                            isInvalid={ingreso.descripcion.length > 255 || ingreso.descripcion.length === 0}
                            as='textarea' row={3} value={ingreso.descripcion} onChange={handleChange} />
                        <span style={{ color: "#adb5bd" }}>{ingreso.descripcion.length + '/255'}</span>
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

export default FormIngreso;