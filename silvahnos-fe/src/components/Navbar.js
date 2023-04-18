import { Container, Nav, Navbar } from "react-bootstrap";
import Logo from "./images/logo.png";

function BasicExample() {

  return (
    <Navbar fixed="top" style={{ background: "#D9D9D9", color: "red" }}>
      <Container>
        <Navbar.Brand href="#home" style={{ color: "white", fontWeight: "bold" }}>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">

          <Nav className="ml-auto"  style={{ color: "black", fontWeight: "bold",}}>
            <a href="/">
              {" "}
              <img
                src={Logo}
                alt="Logo"
                width="100"
                height="50"
                marginLeft="5px"
              />
            </a>
            <Nav.Link href="/#ingresos">
              Ingresos{" "}
            </Nav.Link>
            <Nav.Link href="/#egresos">
              Egresos{" "}
            </Nav.Link>
            <Nav.Link href="/#facturas">
              Facturas{" "}
            </Nav.Link>
            <Nav.Link href="/#logout" style={{position:"absolute", right:"80px"}}>
              Cerrar sesión{" "}
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default BasicExample;
