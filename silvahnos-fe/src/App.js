import { Container } from 'react-bootstrap';
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";
import Egreso from './pages/egreso/Egresos';
import CrearEgreso from './pages/egreso/CrearEgreso';
import Ingresos from './pages/ingreso/Ingresos';
import CrearIngreso from './pages/ingreso/CrearIngreso';
import Facturas from "./pages/factura/Facturas"
import CrearFactura from "./pages/factura/CrearFactura"
import Flujo from "./pages/Flujo"

function App() {
  return (
    <Layout>
      <Container style={{ marginTop: "20px" }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/egresos" element={<Egreso />} />
          <Route path="/ingresos" element={<Ingresos />} />
          <Route path="/crearEgreso" element={<CrearEgreso />} />
          <Route path="/crearIngreso" element={<CrearIngreso />} />
          <Route path="/facturas" element={<Facturas />} />
          <Route path="/crearFactura" element={<CrearFactura />} />
          <Route path="/flujo" element={<Flujo />} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
