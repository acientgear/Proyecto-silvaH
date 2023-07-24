import { Form, InputGroup, Button } from 'react-bootstrap';

const InputYear = ({anio, onChange}) => {
    return (
        <Form className='d-flex gap-3'>
            <InputGroup size='sm' hasValidation>
                <Form.Control type='number' min={1900} value={anio} onChange={onChange} />
            </InputGroup>
        </Form>
    )
};

export default InputYear;