import * as yup from 'yup';
import * as formik from 'formik';
import { Button, Form } from 'react-bootstrap';

const FormUsuario = ({usuario, postUsuario, handleClose}) => {
  const { Formik } = formik;

  const formSchema = yup.object().shape({
    correo: yup.string().required('Campo requerido').email('Correo inválido'),
    nombre: yup.string().required('Campo requerido').min(3, 'Mínimo 3 caracteres'),
    usuario: yup.string().required('Campo requerido').min(3, 'Mínimo 3 caracteres'),
    contrasenna: yup.string().required('Campo requerido')
                             .min(6, 'Mínimo 6 caracteres')
                             .max(16, 'Máximo 16 caracteres'),
    repetirContrasenna: yup.string().required('Campo requerido')
                                    .oneOf([yup.ref('contrasenna'), null], 'Las contraseñas deben coincidir')
                                    .min(6, 'Mínimo 6 caracteres')
                                    .max(16, 'Máximo 16 caracteres'),
  });

  return (
    <Formik
      validationSchema={formSchema}
      onSubmit={(values) => {
        const objetoActualizado = {
          id: usuario.id, 
          correo: values.correo,
          nombre: values.nombre,
          usuario: values.usuario,
          contrasenna: values.contrasenna,
        };
        postUsuario(objetoActualizado);
      }}
      initialValues={{
        usuario: usuario.usuario,
        correo: usuario.correo,
        nombre: usuario.nombre,
        contrasenna: usuario.contrasenna,
        repetirContrasenna: "",
      }}
    >
      {({ handleSubmit, handleChange, values, errors }) => (
        <Form noValidate onSubmit={handleSubmit}>
          <Form.Group className='mb-3' controlId='formUsuario'>
            <Form.Label>Nombre de usuario</Form.Label>
            <Form.Control
              name="usuario"
              type='text'
              value={values.usuario}
              onChange={handleChange}
              isInvalid={!!errors.usuario}
            />
            <Form.Control.Feedback type="invalid">
              {errors.usuario}
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group className='mb-3' controlId='formCorreo'>
            <Form.Label>Correo</Form.Label>
            <Form.Control
              name="correo"
              type='email'
              value={values.correo}
              onChange={handleChange}
              isInvalid={!!errors.correo}
            />
            <Form.Control.Feedback type="invalid">
              {errors.correo}
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
            <Form.Control.Feedback type="invalid">
              {errors.nombre}
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group className='mb-3' controlId='formContrasena'>
            <Form.Label>Contraseña</Form.Label>
            <Form.Control
              name="contrasenna"
              type='password'
              value={values.contrasenna}
              onChange={handleChange}
              isInvalid={!!errors.contrasenna}
            />
            <Form.Control.Feedback type="invalid">
              {errors.contrasenna}
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group className='mb-3' controlId='formRepetirContrasena'>
            <Form.Label>Repetir contraseña</Form.Label>
            <Form.Control
              name="repetirContrasenna"
              type='password'
              value={values.repetirContrasenna}
              onChange={handleChange}
              isInvalid={!!errors.repetirContrasenna}
            />
            <Form.Control.Feedback type="invalid">
              {errors.repetirContrasenna}
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
  );
};

export default FormUsuario;