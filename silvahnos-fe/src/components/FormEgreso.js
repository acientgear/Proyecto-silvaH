import {Form, Button} from 'react-bootstrap';
import CategoriasEgreso from './data/CategoriasEgreso';

const FormEgreso = ({egreso, validated, modal, handleChange, handleSubmit, handleCloseEdit}) => {
    
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
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formEgreso">
                <Form.Label>Origen</Form.Label>
                <Form.Select
                    aria-label="select"
                    name="origen"
                    required
                    placeholder="Ingrese de donde viene el egreso"
                    onChange={handleChange}
                    value={egreso.origen}
                >
                    <option value="">Seleccione una opción</option>
                    {CategoriasEgreso.map((categoria) => (
                        <option key={categoria.id} value={categoria.nombre}>
                            {categoria.nombre}
                        </option>
                    ))}
                </Form.Select>
                {egreso.patente === "Otros" && (
                    <div className="mt-3">
                        <Form.Control
                            type="text"
                            placeholder="Ingrese patente personalizada"
                            name="otraPatente"
                            onChange={handleChange}
                            required
                        />
                        <Form.Control.Feedback type="invalid">
                            Ingrese una patente válida.
                        </Form.Control.Feedback>
                    </div>
                )}
            </Form.Group>

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

            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Descripción</Form.Label>
                <Form.Control name="descripcion"
                    required
                    isValid={255 > egreso.descripcion.length && egreso.descripcion.length > 0}
                    isInvalid={egreso.descripcion.length > 255 || egreso.descripcion.length === 0}
                    as="textarea" row={3} placeholder="Ingrese descripción" onChange={handleChange}
                    value={egreso.descripcion}
                    />
                <Form.Control.Feedback type="invalid">
                    Ingrese una descripción valida
                </Form.Control.Feedback>
            </Form.Group>
            {modal ? modalFooter() : formFooter()}
        </Form>
    )
}

export default FormEgreso;