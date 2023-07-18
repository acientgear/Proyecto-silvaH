import { Form, Button } from 'react-bootstrap';

const FormMotivo = ({ motivo, validated, handleChange, handleSubmit, handleClose }) => {
    return (
        <div>
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > motivo.nombre.length && motivo.nombre.length > 0}
                        isInvalid={motivo.nombre.length > 255 || motivo.nombre.length === 0}
                        type='text' row={3} value={motivo.nombre} onChange={handleChange} />
                    <span style={{ color: "#adb5bd" }}>{motivo.nombre.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDescripcion'>
                    <Form.Label>Descripción</Form.Label>
                    <Form.Control name="descripcion"
                        required
                        isValid={255 > motivo.descripcion.length && motivo.descripcion.length > 0}
                        isInvalid={motivo.descripcion.length > 255 || motivo.descripcion.length === 0}
                        as='textarea' row={3} value={motivo.descripcion} onChange={handleChange} />
                    <span style={{ color: "#adb5bd" }}>{motivo.descripcion.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese una descripcion válida
                    </Form.Control.Feedback>
                </Form.Group>
                <div>
                    <hr></hr>
                    <div style={{ display: "flex", justifyContent: "end" }}>
                        <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleClose}>Cerrar</Button>
                        <Button variant='primary' type='submit'>Guardar</Button>
                    </div>
                </div>
            </Form>

        </div>
    );
}

export default FormMotivo;
