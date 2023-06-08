import { Form,Button } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const CrearMotivoI = () => {
    const [motivoI, setMotivoI] = useState({
        id: null,
        nombre: '',
        descripcion: ''
    });

    const handleChange = (e) => {
        setMotivoI({
            ...motivoI,
            [e.target.name]: e.target.value
        });
    }

    const createMotivoI = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosI';
            const response = await axios.post(url, motivoI);
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
            <h1>Crear Motivo de ingreso</h1>
            <Form>
                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > motivoI.nombre.length && motivoI.nombre.length > 0}
                        isInvalid={motivoI.nombre.length > 255 || motivoI.nombre.length === 0}
                        as='textarea' row={3} value={motivoI.nombre} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDescripcion'>
                    <Form.Label>Descripción</Form.Label>
                    <Form.Control name="descripcion"
                        required
                        isValid={255 > motivoI.descripcion.length && motivoI.descripcion.length > 0}
                        isInvalid={motivoI.descripcion.length > 255 || motivoI.descripcion.length === 0}
                        as='textarea' row={3} value={motivoI.descripcion} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese una descripcion válida
                    </Form.Control.Feedback>
                </Form.Group>
                <Button variant='primary' href='/administracion' style={{ marginRight: 2 }}>Atrás</Button>
                <Button variant='success' onClick={createMotivoI}>Guardar</Button>
            </Form>

        </div>
    );
}

export default CrearMotivoI;