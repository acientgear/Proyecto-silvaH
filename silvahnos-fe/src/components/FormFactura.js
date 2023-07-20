import { Form, Button, Row, Col } from "react-bootstrap"
import Empresas from './data/Empresas';
import * as yup from 'yup';
import * as formik from 'formik';

const FormFactura = ({ factura, postFactura, modal, handleClose }) => {
    const { Formik } = formik;
    const empresas = Empresas();

    const formSchema = yup.object().shape({
        numero_factura: yup.number().required("Ingrese un número de factura valido").min(1, "Mínimo 1 carácter"),
        empresa: yup.number().required("Seleccione una opción valida").min(1, "Seleccione una opción valida"),
        monto: yup.number().required("Ingrese un monto valido").min(1, "Mínimo $1 CLP").max(1000000000, "Máximo $1.000.000.000 CLP"),
        fecha_emision: yup.date().required("Ingrese una fecha de emisión valida")
            .test('Fecha de emisión',
                'La fecha de emisión debe ser menor a la fecha de vencimiento',
                function (value) {
                    return new Date(value) < new Date(this.parent.fecha_vencimiento);
                }),
        fecha_vencimiento: yup.date().required("Ingrese una fecha de vencimiento valida")
            .test('Fecha de vencimiento',
                'La fecha de vencimiento debe ser mayor a la fecha de emisión',
                function (value) {
                    return new Date(value) > new Date(this.parent.fecha_emision);
                }),
        observaciones: yup.string().required("Ingrese una observación valida").min(5, "Mínimo 5 caracteres").max(255, "Máximo 255 caracteres")
    });

    const modalFooter = () => {
        return (
            <Row>
                <Col>
                    <hr></hr>
                    <div className="d-flex justify-content-end">
                        <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleClose}>Cerrar</Button>
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
        <Formik
            validationSchema={formSchema}
            onSubmit={(values) => {
                const objetoActualizado = { ...factura, ...values };
                objetoActualizado.empresa = { id: values.empresa };
                postFactura(objetoActualizado);
            }}
            initialValues={{
                numero_factura: factura.numero_factura,
                empresa: factura.empresa.id,
                monto: factura.monto,
                fecha_emision: factura.fecha_emision.split('T')[0],
                fecha_vencimiento: factura.fecha_vencimiento.split('T')[0],
                observaciones: factura.observaciones,
            }}
        >
            {({ handleSubmit, handleChange, values, errors }) => (
                <Form noValidate onSubmit={handleSubmit}>
                    <Row xs={1} sm={3}>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>N° Factura</Form.Label>
                                <Form.Control
                                    name="numero_factura"
                                    type="number"
                                    onChange={handleChange}
                                    value={values.numero_factura}
                                    isInvalid={!!errors.numero_factura}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.numero_factura}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>Empresa</Form.Label>
                                <Form.Select
                                    aria-label="select"
                                    name="empresa"
                                    onChange={handleChange}
                                    value={values.empresa}
                                    isInvalid={!!errors.empresa}
                                >
                                    <option key={0} value={0}>Seleccione una opción</option>
                                    {empresas.map((categoria) => (
                                        <option key={categoria.id} value={categoria.id}>
                                            {categoria.nombre}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.empresa}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>Monto</Form.Label>
                                <Form.Control
                                    name="monto"
                                    type="number"
                                    onChange={handleChange}
                                    value={values.monto}
                                    isInvalid={!!errors.monto}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.monto}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row xs={1} sm={2}>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>Fecha emisión</Form.Label>
                                <Form.Control
                                    name="fecha_emision"
                                    type="date"
                                    onChange={handleChange}
                                    value={values.fecha_emision}
                                    isInvalid={!!errors.fecha_emision}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.fecha_emision}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>Fecha vencimiento</Form.Label>
                                <Form.Control 
                                    name="fecha_vencimiento" 
                                    type="date"  
                                    onChange={handleChange}
                                    value={values.fecha_vencimiento}
                                    isInvalid={!!errors.fecha_vencimiento}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.fecha_vencimiento}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Group className="mb-3" controlId="formFactura">
                                <Form.Label>Observaciones</Form.Label>
                                <Form.Control 
                                    name="observaciones"
                                    as="textarea"
                                    placeholder="Ingrese una observación" 
                                    onChange={handleChange}
                                    value={values.observaciones}
                                    isInvalid={!!errors.observaciones}
                                />
                                <span style={{ color: "#adb5bd" }}>{values.observaciones.length + '/255'}</span>
                                <Form.Control.Feedback type="invalid">
                                    {errors.observaciones}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    {modal ? modalFooter() : formFooter()}
                </Form>
            )}
        </Formik>
    )
}

export default FormFactura;