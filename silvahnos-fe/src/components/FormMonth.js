import { Form, InputGroup, Button } from 'react-bootstrap';
import * as yup from 'yup';
import * as formik from 'formik';


const FormMonth = ({ mes, anio, get }) => {
    const { Formik } = formik;

    const schema = yup.object({
        anio: yup.number().min(1900).required(),
        mes: yup.number().required()
    });

    return (
        <Formik
            validationSchema={schema}
            onSubmit={(values) => {
                //console.log(values);
                get(values.anio, values.mes);
            }}
            initialValues={{
                anio: anio,
                mes: mes
            }}
        >
            {({
                handleSubmit,
                handleChange,
                values,
                errors,
            }) => (
                <Form className='d-flex gap-2 mb-2' noValidate onSubmit={handleSubmit}>
                    <InputGroup size='sm'>
                        <Form.Select
                            name="mes"
                            aria-label="select"
                            value={values.mes}
                            onChange={handleChange}
                            isInvalid={!!errors.mes}
                        >
                            <option key={1} value={1}>Enero</option>
                            <option key={2} value={2}>Febrero</option>
                            <option key={3} value={3}>Marzo</option>
                            <option key={4} value={4}>Abril</option>
                            <option key={5} value={5}>Mayo</option>
                            <option key={6} value={6}>Junio</option>
                            <option key={7} value={7}>Julio</option>
                            <option key={8} value={8}>Agosto</option>
                            <option key={9} value={9}>Septiembre</option>
                            <option key={10} value={10}>Octubre</option>
                            <option key={11} value={11}>Noviembre</option>
                            <option key={12} value={12} >Diciembre</option>
                        </Form.Select>
                        <InputGroup.Text id="inputGroup-sizing-sm">de</InputGroup.Text>
                        <Form.Control
                            name='anio'
                            type='number' 
                            value={values.anio} 
                            onChange={handleChange} 
                            isInvalid={!!errors.anio}
                            />
                    </InputGroup>
                    <Button type="submit" size='sm'>
                        Buscar
                    </Button>
                </Form>
            )}
        </Formik>
    )
};

export default FormMonth;
