import { Container } from 'react-bootstrap';
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";
import Egreso from './pages/egreso/Egreso';
import CrearEgreso from './pages/egreso/CrearEgreso';
import Ingresos from './pages/ingreso/Ingresos';

function App() {
  return (
    <Layout>
      <Container style={{ marginTop: "100px" }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/egresos" element={<Egreso />} />
          <Route path="/ingresos" element={<Ingresos />} />
          <Route path="/crearEgreso" element={<CrearEgreso />} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
