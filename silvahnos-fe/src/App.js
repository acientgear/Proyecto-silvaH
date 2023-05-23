import { Container } from 'react-bootstrap';
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";
import Egreso from './pages/egreso/Egresos';
import CrearEgreso from './pages/egreso/CrearEgreso';
import Ingresos from './pages/ingreso/Ingresos';
import CrearIngreso from './pages/ingreso/CrearIngreso';
import Flujo from './pages/Flujo';

function App() {
  return (
    <Layout>
      <Container style={{ paddingTop: "20px"}}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/egresos" element={<Egreso />} />
          <Route path="/ingresos" element={<Ingresos />} />
          <Route path="/crearEgreso" element={<CrearEgreso />} />
          <Route path="/crearIngreso" element={<CrearIngreso />} />
          <Route path="/flujo" element={<Flujo />} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
