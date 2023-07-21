import { Container } from 'react-bootstrap';
import { Routes, Route, Navigate } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";
import Egreso from './pages/egreso/Egresos';
import CrearEgreso from './pages/egreso/CrearEgreso';
import Ingresos from './pages/ingreso/Ingresos';
import CrearIngreso from './pages/ingreso/CrearIngreso';
import Facturas from './pages/factura/Facturas';
import CrearFactura from './pages/factura/CrearFactura';
import Resumen from './pages/Resumen';
import AdminView from './pages/admin/AdminView';
import Login from './pages/auth/Login';
import Cookies from 'js-cookie';

function App() {
  const token = localStorage.getItem("token");

  return (
    <Layout token={token}>
      <Container fluid style={{padding: 0}}>
        <Routes>
          <Route path="/" element={token ? <Home /> : <Navigate to={"/login"}/>} />
          <Route path="/egresos" element={token ? <Egreso /> : <Navigate to={"/login"}/>} />
          <Route path="/ingresos" element={token ? <Ingresos /> : <Navigate to={"/login"}/>} />
          <Route path="/facturas" element={token ? <Facturas /> : <Navigate to={"/login"}/>} />
          <Route path="/crearEgreso" element={token ? <CrearEgreso /> : <Navigate to={"/login"}/>} />
          <Route path="/crearIngreso" element={token ? <CrearIngreso /> : <Navigate to={"/login"}/>} />
          <Route path='/crearFactura' element={token ? <CrearFactura /> : <Navigate to={"/login"}/>} />
          <Route path="/resumen" element={token ? <Resumen /> : <Navigate to={"/login"}/>} />
          <Route path="/administracion" element={token ? <AdminView /> : <Navigate to={"/login"}/>} />
          <Route path="/login" element={<Login/>} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
