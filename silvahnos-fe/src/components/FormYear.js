import { Form, Button, Row, Col } from 'react-bootstrap';
import * as yup from 'yup';
import * as formik from 'formik';

const FormYear = ({ anio, handleAnio }) => {
    const { Formik } = formik;

    const schema = yup.object({
        anio: yup.number().min(1900, "El año debe ser mayor a 1900").required()
    });

    return (
        <Formik
            validationSchema={schema}
            onSubmit={(values) => {
                handleAnio(values.anio);
            }}
            initialValues={{
                anio: anio
            }}
        >
            {({
                handleSubmit,
                handleChange,
                values,
                errors,
            }) => (
                <Form className='mb-2' noValidate onSubmit={handleSubmit}>
                    <Row>
                        <Form.Label column xs={1}>
                            Año:
                        </Form.Label>
                        <Col xs={1}>
                            <Form.Control
                                type='number'
                                name='anio'
                                value={values.anio}
                                onChange={handleChange}
                                isInvalid={!!errors.anio}
                            />
                        </Col>
                        <Col xs={2}>
                            <Button type='submit'>Buscar</Button>
                        </Col>
                    </Row>
                </Form>
            )}
        </Formik>
    )
};

export default FormYear;