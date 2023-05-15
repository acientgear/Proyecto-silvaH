import CategoriasIngreso from "./data/CategoriasIngreso";
import { Button, Form } from "react-bootstrap";

const FormIngreso = ({ ingreso, validated, modal, handleChange, handleCloseEdit, handleSumbit}) => {

    const modalFooter = () => {
        return (
            <div>
                <hr></hr>
                <div style={{ display: "flex", justifyContent: "end" }}>
                    <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleCloseEdit}>Cerrar</Button>
                    <Button variant='primary' type='sumbit'>Guardar</Button>
                </div>
            </div>
        )
    }

    const formFooter = () => {
        return (
            <div>
                <Button variant='primary' href='/' style={{ marginRight: 2 }}>Atras</Button>
                <Button variant='success' type='sumbit'>Guardar</Button>
            </div>
        )
    }

    return (
        <Form noValidate validated={validated} onSubmit={handleSumbit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Patente con componente</Form.Label>
                <Form.Control name="patente"
                    required
                    isValid={6 > ingreso.patente.length && ingreso.patente.length > 0}
                    isInvalid={ingreso.patente.length > 6 || ingreso.patente.length === 0}
                    type="text" placeholder="Ingrese la patente" value={ingreso.patente} onChange={handleChange}
                />
                <Form.Control.Feedback type="invalid">
                    Ingrese una patente valida
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Origen</Form.Label>
                <Form.Select name="origen"
                    required
                    aria-label="select"
                    placeholder="Ingrese de donde viene"
                    onChange={handleChange}
                    value={ingreso.origen}
                >
                    {CategoriasIngreso.map((categoria) => (
                        <option key={categoria.id} value={categoria.nombre}>{categoria.nombre}</option>
                    ))}
                </Form.Select>
            </Form.Group>
            <Form.Group className='mb-3' controlId='formMonto'>
                <Form.Label>Monto</Form.Label>
                <Form.Control name="monto"
                    required
                    isValid={1000000000 > ingreso.monto && ingreso.monto > 0}
                    isInvalid={ingreso.monto <= 0 || ingreso.monto > 1000000000}
                    min={1}
                    max={1000000000}
                    type='number' value={ingreso.monto} onChange={handleChange} />
                <Form.Control.Feedback type="invalid">
                    Ingrese un monto entre $1 y $1.000.000.000
                </Form.Control.Feedback>
            </Form.Group>
            <Form.Group className='mb-3' controlId='formDescripcion'>
                <Form.Label>Descripción</Form.Label>
                <Form.Control name="descripcion"
                    required
                    isValid={255 > ingreso.descripcion.length && ingreso.descripcion.length > 0}
                    isInvalid={ingreso.descripcion.length > 255 || ingreso.descripcion.length === 0}
                    as='textarea' row={3} value={ingreso.descripcion} onChange={handleChange} />
                <Form.Control.Feedback type="invalid">
                    Ingrese una descripción valida
                </Form.Control.Feedback>
            </Form.Group>
            {modal ? modalFooter() : formFooter()}
        </Form>
    )
}

export default FormIngreso;