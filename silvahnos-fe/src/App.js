import { Container } from 'react-bootstrap';
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";
import Egreso from './pages/egreso/Egresos';
import CrearEgreso from './pages/egreso/CrearEgreso';
import Ingresos from './pages/ingreso/Ingresos';
import CrearIngreso from './pages/ingreso/CrearIngreso';
import Facturas from './pages/factura/Facturas';
import CrearFactura from './pages/factura/CrearFactura';
import Flujo from './pages/Flujo';
import AdminView from './pages/admin/AdminView';
import CrearMotivoI from './pages/admin/CrearMotivoI';
import CrearMotivoE from './pages/admin/CrearMotivoE';
import CrearEmpresa from './pages/admin/CrearEmpresa';
import Login from './pages/auth/Login';
import PruebaGrafico from './pages/PruebaGrafico';

function App() {
  return (
    <Layout>
      <Container fluid style={{padding: 0}}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/egresos" element={<Egreso />} />
          <Route path="/ingresos" element={<Ingresos />} />
          <Route path="/facturas" element={<Facturas />} />
          <Route path="/crearEgreso" element={<CrearEgreso />} />
          <Route path="/crearIngreso" element={<CrearIngreso />} />
          <Route path='/crearFactura' element={<CrearFactura />} />
          <Route path="/flujo" element={<Flujo />} />
          <Route path="/administracion" element={<AdminView />} />
          <Route path="/crearMotivoI" element={<CrearMotivoI />} />
          <Route path="/crearMotivoE" element={<CrearMotivoE />} />
          <Route path="/crearEmpresa" element={<CrearEmpresa />} />
          <Route path="/login" element={<Login/>} />
          <Route path="/prueba" element={<PruebaGrafico/>} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
