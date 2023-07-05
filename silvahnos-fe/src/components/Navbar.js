import { Container, Nav, Navbar } from "react-bootstrap";
import Logo from "./images/logo.png";

function BasicExample() {

  return (
    <Navbar collapseOnSelect expand="md" style={{ background: "#D9D9D9", color: "red" }}>
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
            <Nav.Link href="/login">Iniciar sesión</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>


  );
}

export default BasicExample;
