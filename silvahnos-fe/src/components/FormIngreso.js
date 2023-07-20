import CategoriasIngreso from "./data/CategoriasIngreso";
import { Button, Col, Form, Row } from "react-bootstrap";
import * as yup from 'yup';
import * as formik from 'formik';

const FormIngreso = ({ ingreso, postIngreso, modal, handleClose }) => {
    const { Formik } = formik;
    const motivos = CategoriasIngreso();

    const formSchema = yup.object().shape({
        patente: yup.string().required("Ingrese una patente valida").min(6, "Son 6 caracteres").max(6, "Son 6 caracteres"),
        monto: yup.number().required("Ingrese un monto valido").min(1, "Mínimo $1 CLP").max(1000000000, "Máximo $1.000.000.000 CLP"),
        motivo: yup.number().required("Seleccione una opción valida").min(1, "Seleccione una opción valida"),
        descripcion: yup.string().required("Ingrese una descripción valida").min(10, "Mínimo 10 carácter").max(255, "Máximo 255 caracteres")
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
                const objetoActualizado = {...ingreso, ...values};
                objetoActualizado.motivo = {id: values.motivo};
                postIngreso(objetoActualizado);
            }}
            initialValues={{
                patente: ingreso.patente,
                monto: ingreso.monto,
                motivo: ingreso.motivo.id,
                descripcion: ingreso.descripcion,
            }}
        >
            {({ handleSubmit, handleChange, values, errors}) => (
                <Form noValidate onSubmit={handleSubmit}>
                <Row xs={1} sm={3}>
                    <Col>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Patente</Form.Label>
                            <Form.Control 
                                type="text"
                                name="patente"
                                placeholder="Ingrese una patente"
                                value={values.patente}
                                onChange={handleChange}
                                isInvalid={!!errors.patente}
                            />
                            <span style={{ color: "#adb5bd" }}>{values.patente.length + '/6'}</span>
                            <Form.Control.Feedback type="invalid">
                                {errors.patente}
                            </Form.Control.Feedback>
                        </Form.Group>
                    </Col>
    
                    <Col>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
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
                                    <option key={categoria.id} value={categoria.id}>{categoria.nombre}</option>
                                ))}
                            </Form.Select>
                            <Form.Control.Feedback type="invalid">
                                {errors.motivo}
                            </Form.Control.Feedback>
                        </Form.Group>
                    </Col>
    
                    <Col>
                        <Form.Group className='mb-3' controlId='formMonto'>
                            <Form.Label>Monto</Form.Label>
                            <Form.Control 
                                name="monto"
                                type='number'
                                value={values.monto}
                                onChange={handleChange} 
                                isInvalid={!!errors.monto}/>
                            <Form.Control.Feedback type="invalid">
                                {errors.monto}
                            </Form.Control.Feedback>
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group className='mb-3' controlId='formDescripcion'>
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control 
                                name="descripcion"
                                as='textarea' 
                                placeholder="Ingrese una descripción"
                                value={values.descripcion} 
                                onChange={handleChange} 
                                isInvalid={!!errors.descripcion}/>
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

export default FormIngreso;