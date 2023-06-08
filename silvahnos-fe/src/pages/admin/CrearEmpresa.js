import { Form, Button } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const CrearEmpresa = () => {
    const [empresa, setempresa] = useState({
        id: null,
        rut: '',
        nombre: '',
        direccion: ''
    });

    const handleChange = (e) => {
        setempresa({
            ...empresa,
            [e.target.name]: e.target.value
        });
    }

    const createEmpresa = async () => {
        try {
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.post(url, empresa);
            if (response.status === 200) {
                console.log(response.data);
                window.location.href = "/administracion";
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    return (
        <div>
            <h1>Crear Motivo de engreso</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formRut">
                    <Form.Label>Rut</Form.Label>
                    <Form.Control name="rut" required
                        isValid={9000000 > empresa.rut && empresa.rut > 0}
                        isInvalid={empresa.rut <= 0 || empresa.rut > 1000000000}
                        min={1}
                        max={1000000000}
                        type="number" placeholder="Ingrese rut" onChange={handleChange}
                        value={empresa.rut}
                    />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un rut válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > empresa.nombre.length && empresa.nombre.length > 0}
                        isInvalid={empresa.nombre.length > 255 || empresa.nombre.length === 0}
                        as='textarea' row={3} value={empresa.nombre} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDirección'>
                    <Form.Label>Dirección</Form.Label>
                    <Form.Control name="direccion"
                        required
                        isValid={255 > empresa.direccion.length && empresa.direccion.length > 0}
                        isInvalid={empresa.direccion.length > 255 || empresa.direccion.length === 0}
                        as='textarea' row={3} value={empresa.direccion} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese una dirección válida
                    </Form.Control.Feedback>
                </Form.Group>
                <Button variant='primary' href='/administracion' style={{ marginRight: 2 }}>Atrás</Button>
                <Button variant='success' onClick={createEmpresa}>Guardar</Button>
            </Form>

        </div>
    );
}

export default CrearEmpresa;