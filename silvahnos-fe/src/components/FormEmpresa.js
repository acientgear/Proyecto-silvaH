import { Form, Button } from 'react-bootstrap';
import { checkRut, prettifyRut } from "react-rut-formatter";
import * as yup from 'yup';
import * as formik from 'formik';

const FormEmpresa = ({ empresa, postEmpresa, handleClose }) => {
    const { Formik } = formik;
    const formSchema = yup.object().shape({
        rut: yup.string().required('Ingrese un rut válido').min(12, 'Ingrese un rut válido').max(12, 'Ingrese un rut válido').test('Rut valido', 'Ingrese un rut válido', (rut) => checkRut(rut)),
        nombre: yup.string().required('Ingrese un nombre válido').min(1, 'Mínimo 1 carácter').max(255, 'Máximo 255 caracteres'),
        direccion: yup.string().required('Ingrese una dirección válida').min(5, 'Mínimo 5 carácter').max(255, 'Máximo 255 caracteres')
    });

    return (
        <div>
            <Formik
                validationSchema={formSchema}
                onSubmit={(values) => {
                    const objetoActualizado = { ...empresa, ...values };
                    objetoActualizado.rut = prettifyRut(values.rut);
                    postEmpresa(objetoActualizado);
                }}
                initialValues={{
                    rut: empresa.rut,
                    nombre: empresa.nombre,
                    direccion: empresa.direccion,
                }}
            >
                {({ handleSubmit, handleChange, values, errors, setFieldValue }) => (
                    <Form noValidate onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="formRut">
                            <Form.Label>Rut</Form.Label>
                            <Form.Control 
                                name="rut"
                                type="text" 
                                placeholder="Ingrese un rut" 
                                onChange={(e) => {
                                    handleChange(e);
                                    setFieldValue("rut", prettifyRut(e.target.value));
                                }}
                                value={values.rut}
                                isInvalid={!!errors.rut}
                            />
                            <Form.Control.Feedback type="invalid">
                                {errors.rut}
                            </Form.Control.Feedback>
                        </Form.Group>

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

                        <Form.Group className='mb-3' controlId='formDirección'>
                            <Form.Label>Dirección</Form.Label>
                            <Form.Control 
                                name="direccion"
                                type='text' 
                                value={values.direccion} 
                                onChange={handleChange} 
                                isInvalid={!!errors.direccion}
                                />
                            <span style={{ color: "#adb5bd" }}>{values.direccion.length + '/255'}</span>
                            <Form.Control.Feedback type="invalid">
                                {errors.direccion}
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

export default FormEmpresa;
