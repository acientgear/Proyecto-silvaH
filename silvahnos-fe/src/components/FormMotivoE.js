import { Form, Button } from 'react-bootstrap';

const FormMotivoE = ({ motivoE, validated, modal, handleChange, handleSubmit, handleCloseEdit }) => {
    const modalFooter = () => {
        return (
            <div>
                <hr></hr>
                <div style={{ display: "flex", justifyContent: "end" }}>
                    <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleCloseEdit}>Cerrar</Button>
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
            <h1>Registrar motivo de egreso</h1>
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > motivoE.nombre.length && motivoE.nombre.length > 0}
                        isInvalid={motivoE.nombre.length > 255 || motivoE.nombre.length === 0}
                        type='text' row={3} value={motivoE.nombre} onChange={handleChange} />
                        <span style={{color: "#adb5bd"}}>{motivoE.nombre.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDescripcion'>
                    <Form.Label>Descripción</Form.Label>
                    <Form.Control name="descripcion"
                        required
                        isValid={255 > motivoE.descripcion.length && motivoE.descripcion.length > 0}
                        isInvalid={motivoE.descripcion.length > 255 || motivoE.descripcion.length === 0}
                        as='textarea' row={3} value={motivoE.descripcion} onChange={handleChange} />
                        <span style={{color: "#adb5bd"}}>{motivoE.descripcion.length + '/255'}</span>
                    <Form.Control.Feedback type="invalid">
                        Ingrese una descripcion válida
                    </Form.Control.Feedback>
                </Form.Group>
                {modal ? modalFooter() : formFooter()}
            </Form>

        </div>
    );
}

export default FormMotivoE;
