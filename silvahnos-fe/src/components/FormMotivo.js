import { Form, Button } from 'react-bootstrap';
import * as yup from 'yup';
import * as formik from 'formik';

const FormMotivo = ({ motivo, postMotivo, handleClose }) => {
    const { Formik } = formik;

    const formSchema = yup.object().shape({
        nombre: yup.string().required('Ingrese un nombre valido').min(1, 'Mínimo 1 carácter').max(255, 'Máximo 255 caracteres'),
        descripcion: yup.string().required('Ingrese una descripción valida').min(10, 'Mínimo 10 carácter').max(255, 'Máximo 255 caracteres')
    });

    return (
        <div>
            <Formik
                validationSchema={formSchema}
                onSubmit={(values) => {
                    const objetoActualizado = { ...motivo, ...values };
                    postMotivo(objetoActualizado);
                }}
                initialValues={{
                    nombre: motivo.nombre,
                    descripcion: motivo.descripcion,
                }}
            >
                {({ handleSubmit, handleChange, values, errors }) => (
                    <Form noValidate onSubmit={handleSubmit}>
                        <Form.Group className='mb-3' controlId='formNombre'>
                            <Form.Label>Nombre</Form.Label>
                            <Form.Control 
                                name="nombre"
                                type='text' 
                                value={values.nombre} 
                                onChange={handleChange} 
                                isInvalid={!!errors.nombre}
                                />
                            <span style={{ color: "#adb5bd" }}>{values.nombre.length + '/255'}</span>
                            <Form.Control.Feedback type="invalid">
                                {errors.nombre}
                            </Form.Control.Feedback>
                        </Form.Group>

                        <Form.Group className='mb-3' controlId='formDescripcion'>
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control 
                                name="descripcion"
                                as='textarea'  
                                value={values.descripcion} 
                                onChange={handleChange} 
                                isInvalid={!!errors.descripcion}
                                />
                            <span style={{ color: "#adb5bd" }}>{values.descripcion.length + '/255'}</span>
                            <Form.Control.Feedback type="invalid">
                                {errors.descripcion}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <div>
                            <hr></hr>
                            <div style={{ display: "flex", justifyContent: "end" }}>
                                <Button variant='secondary' style={{ marginRight: 2 }} onClick={handleClose}>Cerrar</Button>
                                <Button variant='primary' type='submit'>Guardar</Button>
                            </div>
                        </div>
                    </Form>
                )}
            </Formik>
        </div>
    );
}

export default FormMotivo;
