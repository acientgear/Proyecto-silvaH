import { Container, Nav, Navbar } from "react-bootstrap";
import Logo from "./images/logo.png";
import { useEffect, useState } from "react";

function BasicExample() {
  const [logged, setLogged] = useState(false);

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href = "/login";
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setLogged(true);
    }
  }, []);

  return (
    <Navbar collapseOnSelect expand="md" style={{ background: "#D9D9D9", color: "red", boxShadow: "0px 2px 6px 0px rgba(0,0,0,0.5)"}}>
      <Container>
        <Navbar.Brand href="/" style={{ color: "white", fontWeight: "bold" }}>
          <div href="/">
            {" "}
            <img
              src={Logo}
              alt="Logo"
              width="100"
              height="50"
            />
          </div></Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto" style={{ color: "black", fontWeight: "bold" }}>
            <Nav.Link href="/ingresos">Ingresos{" "}</Nav.Link>
            <Nav.Link href="/egresos">Egresos{" "}</Nav.Link>
            <Nav.Link href="/facturas">Facturas{" "}</Nav.Link>
            <Nav.Link href="/flujo">Resumen{" "}</Nav.Link>
            <Nav.Link href="/administracion">Administración{" "}</Nav.Link>
          </Nav>
          <Nav style={{ color: "black", fontWeight: "bold" }}>
            {logged ? (
              <Nav.Link onClick={handleLogout}>Cerrar sesión</Nav.Link>
            ) : (
              <Nav.Link href="/login">Iniciar sesión</Nav.Link>
            )  
            }
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>


  );
}

export default BasicExample;
