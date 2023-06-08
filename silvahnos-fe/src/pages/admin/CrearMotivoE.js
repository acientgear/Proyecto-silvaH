import { Form,Button } from 'react-bootstrap';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const CrearMotivoE = () => {
    const [motivoE, setmotivoE] = useState({
        id: null,
        nombre: '',
        descripcion: ''
    });

    const handleChange = (e) => {
        setmotivoE({
            ...motivoE,
            [e.target.name]: e.target.value
        });
    }

    const createmotivoE = async () => {
        try {
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.post(url, motivoE);
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
                <Form.Group className='mb-3' controlId='formNombre'>
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control name="nombre"
                        required
                        isValid={255 > motivoE.nombre.length && motivoE.nombre.length > 0}
                        isInvalid={motivoE.nombre.length > 255 || motivoE.nombre.length === 0}
                        type='text' row={3} value={motivoE.nombre} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese un nombre válido
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group className='mb-3' controlId='formDescripcion'>
                    <Form.Label>Descripción</Form.Label>
                    <Form.Control name="descripcion"
                        required
                        isValid={255 > motivoE.descripcion.length && motivoE.descripcion.length > 0}
                        isInvalid={motivoE.descripcion.length > 255 || motivoE.descripcion.length === 0}
                        as='textarea' row={3} value={motivoE.descripcion} onChange={handleChange} />
                    <Form.Control.Feedback type="invalid">
                        Ingrese una descripcion válida
                    </Form.Control.Feedback>
                </Form.Group>
                <Button variant='primary' href='/administracion' style={{ marginRight: 2 }}>Atrás</Button>
                <Button variant='success' onClick={createmotivoE}>Guardar</Button>
            </Form>

        </div>
    );
}

export default CrearMotivoE;