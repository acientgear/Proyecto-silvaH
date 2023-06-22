import { Form, Button } from 'react-bootstrap';
import { checkRut, prettifyRut } from "react-rut-formatter";

const FormEmpresa = ({ empresa, validated, modal, handleChange, handleSubmit, handleCloseEdit, setempresa }) => {

    const handleRut = (e) => {
        setempresa({
            ...empresa,
            rut: prettifyRut(e.target.value)
        });
    }

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
                        isValid={checkRut(empresa.rut)}
                        isInvalid={!checkRut(empresa.rut) || empresa.rut.length > 12}
                        type="text" placeholder="Ingrese un rut" onChange={handleRut}
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
                        <span style={{color: "#adb5bd"}}>{empresa.nombre.length + '/255'}</span>
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
                        <span style={{color: "#adb5bd"}}>{empresa.direccion.length + '/255'}</span>
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
