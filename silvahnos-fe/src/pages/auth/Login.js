import { useState } from "react";
import { Button, Form } from "react-bootstrap";

const Login = () => {
    const [validated, setValidated] = useState(false); 
    const [seePassword, setSeePassword] = useState(false);
    const [tipo, setTipo] = useState("password");

    const [usuario, setUsuario] = useState({
        correo: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setUsuario({
            ...usuario,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;
        if(form.checkValidity() === false){
            e.stopPropagation();
        }else{
            console.log(usuario);
            setValidated(true);
        }
    }

    const handleSee = () => {
        if(seePassword){
            setSeePassword(false);
            setTipo("password");
        }else{
            setSeePassword(true);
            setTipo("text");
        }
    }

    return (
        <div className="d-flex" 
            style={{height: "calc(100vh - 76px)",
                    justifyContent: "center",
                    alignItems:"center"}}>
            <div style={{width: "auto", 
                        height: "360px",
                        backgroundColor: "rgb(217, 217, 217)", 
                        padding: "50px", 
                        borderRadius:10}}>
                <h1>Iniciar Sesion</h1>
                <Form noValidate validated={validated} onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="formCorreo">
                        <Form.Label>Correo</Form.Label>
                        <Form.Control type="email" 
                            name="correo"
                            onChange={handleInputChange}
                            placeholder="Ingrese su correo" 
                            required/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formPassword">
                        <Form.Label>Contraseña</Form.Label>
                        <Form.Control type={tipo}
                            name="password"
                            onChange={handleInputChange}
                            placeholder="Ingrese su contraseña"
                            required/>
                        <Form.Text className="text-muted" 
                            style={{cursor: "pointer"}}
                            onClick={handleSee}>Mostrar contraseña</Form.Text>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Iniciar Sesión
                    </Button>
                </Form>
            </div>
        </div>
    );
};

export default Login;