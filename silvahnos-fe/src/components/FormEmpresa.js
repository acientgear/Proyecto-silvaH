import { Form, Button } from 'react-bootstrap';

const FormEmpresa = ({ empresa, validated, modal, handleChange, handleSubmit, handleCloseEdit }) => {
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
            <h1>Registrar empresa</h1>
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="formRut">
                    <Form.Label>Rut</Form.Label>
                    <Form.Control name="rut" required
                        isValid={9000000 > empresa.rut && empresa.rut > 0}
                        isInvalid={empresa.rut <= 0 || empresa.rut > 1000000000}
                        min={1}
                        max={1000000000}
                        type="number" placeholder="Ingrese rut" onChange={handleChange}
                        value={empresa.rut}
                    />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un rut válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > empresa.nombre.length && empresa.nombre.length > 0}
                        isInvalid={empresa.nombre.length > 255 || empresa.nombre.length === 0}
                        type='text' row={3} value={empresa.nombre} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDirección'>
                    <Form.Label>Dirección</Form.Label>
                    <Form.Control name="direccion"
                        required
                        isValid={255 > empresa.direccion.length && empresa.direccion.length > 0}
                        isInvalid={empresa.direccion.length > 255 || empresa.direccion.length === 0}
                        type='text' row={3} value={empresa.direccion} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese una dirección válida
                    </Form.Control.Feedback>
                </Form.Group>
                {modal ? modalFooter() : formFooter()}
            </Form>

        </div>
    );
}

export default FormEmpresa;
