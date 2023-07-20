import { Form, Button, Row, Col } from 'react-bootstrap';
import CategoriasEgreso from './data/CategoriasEgreso';
import * as yup from 'yup';
import * as formik from 'formik';

const FormEgreso = ({ egreso, postEgreso, modal, handleClose }) => {
    const { Formik } = formik;
    const motivos = CategoriasEgreso();

    const formSchema = yup.object().shape({
        monto: yup.number().required('Ingrese un monto válido').min(1, 'Mínimo $1 CLP').max(1000000000, 'Máximo $1000000000 CLP'),
        motivo: yup.number().required('Seleccione una opción válida').min(1, 'Seleccione una opción válida'),
        descripcion: yup.string().required('Ingrese una descripción válida').min(5, 'Mínimo 5 carácter').max(255, 'Máximo 255 caracteres')
    });

    const modalFooter = () => {
        return (
            <Row>
                <Col>
                    <hr></hr>
                    <div className='d-flex justify-content-end'>
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
                <Col className='d-flex justify-content-end'>
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
                const objetoActualizado = { ...egreso, ...values };
                objetoActualizado.motivo = { id: values.motivo };
                postEgreso(objetoActualizado);
            }}
            initialValues={{
                monto: egreso.monto,
                motivo: egreso.motivo.id,
                descripcion: egreso.descripcion,
            }}
        >
            {({ handleSubmit, handleChange, values, errors }) => (
                <Form noValidate onSubmit={handleSubmit}>
                    <Row xs={1} sm={2}>
                        <Col>
                            <Form.Group className="mb-3" controlId="formEgreso">
                                <Form.Label>Motivo</Form.Label>
                                <Form.Select
                                    name="motivo"
                                    aria-label="select"
                                    onChange={handleChange}
                                    value={values.motivo}
                                    isInvalid={!!errors.motivo}
                                >
                                    <option key={0} value={0}>Seleccione una opción</option>
                                    {motivos.map((categoria) => (
                                        <option key={categoria.id} value={categoria.id}>
                                            {categoria.nombre}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.motivo}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                        <Col>
                            <Form.Group className="mb-3" controlId="formEgreso">
                                <Form.Label>Monto</Form.Label>
                                <Form.Control 
                                    name="monto" 
                                    type="number"
                                    value={values.monto}
                                    onChange={handleChange}
                                    isInvalid={!!errors.monto}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.monto}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Descripción</Form.Label>
                                <Form.Control 
                                    name="descripcion"
                                    required
                                    as="textarea" 
                                    placeholder="Ingrese una descripción" 
                                    onChange={handleChange}
                                    value={values.descripcion}
                                    isInvalid={!!errors.descripcion}
                                />
                                <span style={{ color: "#adb5bd" }}>{values.descripcion.length + '/255'}</span>
                                <Form.Control.Feedback type="invalid">
                                    {errors.descripcion}
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

export default FormEgreso;