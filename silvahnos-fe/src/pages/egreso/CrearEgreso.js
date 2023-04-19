import axios from 'axios';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';

function CrearEgreso() {

    const [egresos, setEgresos] = useState([]);
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [nuevoEgreso, setNuevoEgreso] = useState({
        id: null,
        monto: null,
        patente: null,
        descripcion: null,
        fecha_creacion: null,
        fecha_modificacion: null,
        fecha_borrado: null,
        borrado: false
    });


    const handleChange = (e) => {
        setNuevoEgreso({
            ...nuevoEgreso,
            [e.target.name]: e.target.value
        });
    };

    const crearEgreso = async () => {
        try {
            const fechaActual = new Date();
            const fechaCreacion = fechaActual.getFullYear() + "-" + (fechaActual.getMonth() + 1) + "-" + fechaActual.getDate() + " " + fechaActual.getHours() + ":" + fechaActual.getMinutes() + ":" + fechaActual.getSeconds() + "." + fechaActual.getMilliseconds();
            console.log(fechaCreacion);
            //nuevoEgreso.fecha_creacion = fechaCreacion;
            let url = "http://localhost:8090/egresos";
            let response = await axios.post(url, nuevoEgreso);
            if (response.status === 200) {
                setEgresos(response.data);
            }
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <Form>
            <Form.Group className="mb-3" controlId="formEgreso">
                <Form.Label>Descripcion</Form.Label>
                <Form.Control as="textarea" rows={3} name="descripcion" type="textarea" placeholder="Ingrese descripcion" onChange={handleChange} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formEgreso">
                <Form.Label>Monto</Form.Label>
                <Form.Control name="monto" type="text" placeholder="Ingrese monto" onChange={handleChange} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formEgreso">
                <Form.Label>Patente</Form.Label>
                <Form.Control name="patente" type="text" placeholder="Ingrese patente" onChange={handleChange} />
            </Form.Group>
            <Button variant="secondary" onClick={handleClose}>Cerrar</Button>
            <Button variant="primary" onClick={crearEgreso}>Crear</Button>
        </Form>
    );
}

export default CrearEgreso;