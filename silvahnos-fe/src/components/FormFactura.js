import { Form, Button } from "react-bootstrap"

const FormFactura = ({handleCloseEdit, validated, handleSubmit, factura, handleChange, modal}) => {
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

    return(
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formFactura">
                <Form.Label>N° Factura</Form.Label>
                <Form.Control name="numero_factura" required
                    isValid={1000000000 > factura.numero_factura && factura.numero_factura > 0}
                    isInvalid={factura.numero_factura <= 0 || factura.numero_factura > 1000000000}
                    min={1}
                    max={1000000000}
                    type="number" placeholder="Ingrese un número de factura" onChange={handleChange}
                    value={factura.numero_factura}
                />
                <Form.Control.Feedback type="invalid">
                    Se debe ingresar un número de factura valido.
                </Form.Control.Feedback>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formFactura">
                <Form.Label>Fecha emisión</Form.Label>
                <Form.Control name="fecha_emision" required
                    type="date" placeholder="Ingrese una fecha de emisión" onChange={handleChange}
                    value={factura.fecha_emision}
                />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formFactura">
                <Form.Label>Fecha vencimiento</Form.Label>
                <Form.Control name="fecha_vencimiento" required
                    type="date" placeholder="Ingrese una fecha de vencimiento" onChange={handleChange}
                    value={factura.fecha_vencimiento}
                />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formFactura">
                <Form.Label>Fecha pago</Form.Label>
                <Form.Control name="fecha_pago" required
                    type="date" placeholder="Ingrese una fecha de pago" onChange={handleChange}
                    value={factura.fecha_pago}
                />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formFactura">
                <Form.Label>Observaciones</Form.Label>
                <Form.Control name="observaciones"
                    required
                    isValid={255 > factura.observaciones.length && factura.observaciones.length > 0}
                    isInvalid={factura.observaciones.length > 255 || factura.observaciones.length === 0}
                    as="textarea" row={3} placeholder="Ingrese una observación" onChange={handleChange}
                    value={factura.observaciones}
                />
                <Form.Control.Feedback type="invalid">
                    Ingrese una observación valida
                </Form.Control.Feedback>
            </Form.Group>

            {modal ? modalFooter() : formFooter()}
        </Form>
    )
}

export default FormFactura;