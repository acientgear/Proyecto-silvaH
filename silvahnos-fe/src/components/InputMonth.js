import { Form, InputGroup, Button } from 'react-bootstrap';

const InputMonth = ({ mes, anio, onChangeMes, onChangeAnio, get}) => {
    return (
        <Form className='d-flex gap-3'>
                            <InputGroup size='sm'>
                                <Form.Select aria-label="mes" value={mes} onChange={onChangeMes}>
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option>
                                    <option value="9">Septiembre</option>
                                    <option value="10">Octubre</option>
                                    <option value="11">Noviembre</option>
                                    <option value="12" >Diciembre</option>
                                </Form.Select>
                                <InputGroup.Text id="inputGroup-sizing-sm">de</InputGroup.Text>
                                <Form.Control type='number' value={anio} onChange={onChangeAnio} />
                            </InputGroup>
                            <Button variant='primary' size='sm' onClick={get}>
                                Buscar
                            </Button>
                        </Form>
    )
};

export default InputMonth;