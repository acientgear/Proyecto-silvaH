import { Form, Button } from 'react-bootstrap';

const FormMotivoI = ({ motivoI, validated, modal, handleChange, handleSubmit, handleClose }) => {
    const modalFooter = () => {
        return (
            <div>
                <hr></hr>
                <div style={{ display: "flex", justifyContent: "end" }}>
                    <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleClose}>Cerrar</Button>
                    <Button variant='primary' type='submit'>Guardar</Button>
                </div>
            </div>
        )
    }

    const formFooter = () => {
        return (
            <div>
                <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                <Button variant='success' type='submit'>Guardar</Button>
            </div>
        )
    }

    return (
        <div>
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > motivoI.nombre.length && motivoI.nombre.length > 0}
                        isInvalid={motivoI.nombre.length > 255 || motivoI.nombre.length === 0}
                        type='text' row={3} value={motivoI.nombre} onChange={handleChange} />
                        <span style={{color: "#adb5bd"}}>{motivoI.nombre.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDescripcion'>
                    <Form.Label>Descripción</Form.Label>
                    <Form.Control name="descripcion"
                        required
                        isValid={255 > motivoI.descripcion.length && motivoI.descripcion.length > 0}
                        isInvalid={motivoI.descripcion.length > 255 || motivoI.descripcion.length === 0}
                        as='textarea' row={3} value={motivoI.descripcion} onChange={handleChange} />
                        <span style={{color: "#adb5bd"}}>{motivoI.descripcion.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese una descripcion válida
                    </Form.Control.Feedback>
                </Form.Group>
                {modal ? modalFooter() : formFooter()}
            </Form>

        </div>
    );
}

export default FormMotivoI;
