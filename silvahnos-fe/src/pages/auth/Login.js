import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import urlweb from '../../config/config';

const Login = () => {
    const [validated, setValidated] = useState(false);
    const [seePassword, setSeePassword] = useState(false);
    const [tipo, setTipo] = useState("password");

    const [login, setLogin] = useState({
        usuario: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setLogin({
            ...login,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        handleLogin();
    }

    const handleSee = () => {
        if (seePassword) {
            setSeePassword(false);
            setTipo("password");
        } else {
            setSeePassword(true);
            setTipo("text");
        }
    }

    const handleLogin = async () => {
        try {
            let url = "http://"+urlweb+"/iniciar_sesion";
            console.log(login, url)
            const response = await axios.post(url, login);
            if (response.status === 200) {
                localStorage.setItem("token", response.data.jwtToken);
                window.location.href = "/";
            }

        } catch (err) {
            console.log("Credenciales incorrectas");
            console.error(err.message);
        }
    };

    return (
        <div className="d-flex"
            style={{
                height: "calc(100vh - 76px)",
                justifyContent: "center",
                alignItems: "center"
            }}>
            <div style={{
                width: "auto",
                height: "360px",
                backgroundColor: "rgb(217, 217, 217)",
                padding: "50px",
                borderRadius: 10
            }}>
                <h1>Iniciar Sesion</h1>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="formUsuario">
                        <Form.Label>Usuario</Form.Label>
                        <Form.Control type="text"
                            name="usuario"
                            onChange={handleInputChange}
                            placeholder="Ingrese su usuario"
                            required />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formPassword">
                        <Form.Label>Contraseña</Form.Label>
                        <Form.Control type={tipo}
                            name="password"
                            onChange={handleInputChange}
                            placeholder="Ingrese su contraseña"
                            required />
                        <Form.Text className="text-muted"
                            style={{ cursor: "pointer" }}
                            onClick={handleSee}>Mostrar contraseña</Form.Text>
                    </Form.Group>
                    <Button variant="primary" type="submit" style={{ marginRight: '10px' }}>
                        Ingresar
                    </Button>
                </Form>
            </div>
        </div>
    );
};

export default Login;