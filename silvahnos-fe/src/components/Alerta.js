import { useEffect } from 'react';
import Alert from 'react-bootstrap/Alert';

const Alerta = ({ mensaje, tipo, show, setShow }) => {
    useEffect(() => {
        const timer = setTimeout(() => {
            setShow(false);
        }, 5000);
        return () => clearTimeout(timer);
    }, [setShow]);

    return (
        <Alert show={show} variant={tipo} className='text-center' dismissible style={{ position: 'absolute', bottom: 0, zIndex: 1000 }} >
            {mensaje}
        </Alert>
    );
}
export default Alerta;