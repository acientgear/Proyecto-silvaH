import axios from 'axios';
import { Col, Row, Card, Button, Container, Table, Badge, ListGroup } from "react-bootstrap";
import { useState, useEffect, useCallback } from 'react';
import LineChartIngresos from './ingreso/Grafico';
import LineChartEgresos from './egreso/Grafico';
import urlweb from '../config/config';
import Cookies from 'js-cookie';

const Home = () => {
  const config = {
    headers: { Authorization: `Bearer ${Cookies.get("token")}` }
  };
  const [ingresos, setIngresos] = useState([]);
  const [egresos, setEgresos] = useState([]);
  const [facturas, setFacturas] = useState([]);
  const [saldo, setSaldo] = useState(0);
  const [iva, setIva] = useState(0);

  let fechaAcual = new Date();
  let anio = fechaAcual.getFullYear();
  let mes = fechaAcual.getMonth() + 1;

  const [totalIngresos, setTotalIngresos] = useState(0);
  const [totalEgresos, setTotalEgresos] = useState(0);

  const getIgresos = async () => {
    try {
      let url = 'http://' + urlweb + '/ingresos/ultimos';
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setIngresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const getEgresos = async () => {
    try {
      let url = 'http://' + urlweb + '/egresos/ultimos';
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setEgresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const getSaldoCuenta = useCallback(async () => {
    try {
      let url = 'http://' + urlweb + '/ingresos/total/' + mes;
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setSaldo(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  }, [mes]);

  const getIva = async () => {
    try {
      let url = 'http://' + urlweb + '/facturas/iva/' + anio + '/' + mes;
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setIva(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const getProximasFacturasVencer = async () => {
    try {
      let url = 'http://' + urlweb + '/facturas/proximasVencer/' + anio + '/' + mes;
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setFacturas(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  };

  const formatearFecha = (fecha) => {
    if (fecha === null) {
      return "---"
    }
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    return fechaC[2] + '/' + fechaC[1] + '/' + fechaC[0];
  };

  const calcularDiasPorVencer = (fecha) => {
    let fechaC = fecha.split('T')[0];
    fechaC = fechaC.split('-');
    let fechaVencimiento = new Date(fechaC[0], fechaC[1] - 1, fechaC[2]);
    let fechaActual = new Date();
    let diferencia = fechaVencimiento.getTime() - fechaActual.getTime();
    let dias = Math.round(diferencia / (1000 * 60 * 60 * 24));
    dias = dias + 1;
    console.log("venc", fechaVencimiento)
    console.log("act", fechaActual);
    if (dias === 0) {
      return "Vence hoy";
    } else if (dias < 0) {
      return "Vencida";
    }
    else {
      if (dias === 1) {
        return "Vence en " + dias + " día";
      } else {
        return "Vence en " + dias + " días";
      }
    }
  };

  const formatoMonto = (monto) => {
    const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
    return montoFormateado;
  };

  const totalIngresosMes = useCallback(async () => {
    try {
      let url = 'http://' + urlweb + '/ingresos/total/' + anio + '/' + mes;
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setTotalIngresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  }, [anio, mes]);


  const totalEgresosMes = useCallback(async () => {
    try {
      let url = 'http://' + urlweb + '/egresos/total/' + anio + '/' + mes;
      const response = await axios.get(url, config);
      if (response.status === 200) {
        setTotalEgresos(response.data);
      }
    } catch (err) {
      console.log(err.message);
    }
  }, [anio, mes]);


  const colorIngreso = (i) => {
    if (i % 2 === 0) {
      return "#B8E7E1"
    }
    return "#FFF"
  }

  const colorEgreso = (i) => {
    if (i % 2 === 0) {
      return "#F2B6A0"
    }
    return "#FFF"
  }

  useEffect(() => {
    getIgresos();
    totalIngresosMes();
    getEgresos();
    totalEgresosMes();
    getSaldoCuenta();
    getIva();
    getProximasFacturasVencer();
  }, [getSaldoCuenta, totalEgresosMes, totalIngresosMes]);

  return (
    <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
      <Row className="justify-content-center">
        <Col xs="auto" style={{paddingTop:"7px"}}>
          <ListGroup>
            <ListGroup.Item style={{ fontWeight: "bold" }}>Saldo cuenta: {formatoMonto(saldo)}</ListGroup.Item>
          </ListGroup>
        </Col>
        <Col xs="auto" style={{paddingTop:"7px"}}>
          <ListGroup>
            <ListGroup.Item style={{ fontWeight: "bold" }}>IVA a pagar: {formatoMonto(iva)}</ListGroup.Item>
          </ListGroup>
        </Col>
      </Row>
      <Row xs={1} lg={2} xl={3}>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title >Últimos ingresos registrados</Card.Title>
              <Table striped responsive="sm" hover bordered style={{ borderColor: "#B8E7E1" }}>
                <thead>
                  <tr>
                    <th style={{ width: '100px' }}>Fecha</th>
                    <th style={{ width: '100px' }}>Patente</th>
                    <th style={{ width: '100px' }}>Monto</th>
                  </tr>
                </thead>
                <tbody>
                  {ingresos.map((ingreso, index) => (
                    <tr style={{ backgroundColor: colorIngreso(index) }} key={ingreso.id}>
                      <td >{formatearFecha(ingreso.fecha_creacion)}</td>
                      <td>{ingreso.patente}</td>
                      <td>{formatoMonto(ingreso.monto)}</td>
                    </tr>
                  )
                  )}
                </tbody>
              </Table>
              <ListGroup>
                <ListGroup.Item>Total ingresos de este mes: {formatoMonto(totalIngresos)}</ListGroup.Item>
              </ListGroup>
              <ListGroup>
                <LineChartIngresos />
              </ListGroup>
              <Button href="/crearIngreso" style={{ backgroundColor: "#B8E7E1", color: "black", border: "none", fontWeight: "bold", marginTop: "5px" }}>Registrar ingreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title >Últimos egresos registrados</Card.Title>
              <Table striped responsive="sm" hover bordered style={{ borderColor: "#F2B6A0" }}>
                <thead>
                  <tr>
                    <th style={{ width: '100px' }}>Fecha</th>
                    <th style={{ width: '100px' }}>Motivo</th>
                    <th style={{ width: '100px' }}>Monto</th>
                  </tr>
                </thead>
                <tbody>
                  {egresos.map((egreso, index) => (
                    <tr style={{ backgroundColor: colorEgreso(index) }} key={egreso.id}>
                      <td>{formatearFecha(egreso.fecha_creacion)}</td>
                      <td>{egreso.motivo.nombre}</td>
                      <td>{formatoMonto(egreso.monto)}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
              <ListGroup>
                <ListGroup.Item>Total egresos de este mes: {formatoMonto(totalEgresos)}</ListGroup.Item>
              </ListGroup>
              <ListGroup>
                <LineChartEgresos />
              </ListGroup>
              <Button href="/crearEgreso" style={{ backgroundColor: "#F2B6A0", fontWeight: "bold", border: "none", color: "black", marginTop: "5px" }}>Registrar egreso</Button>
            </Card.Body>
          </Card>
        </Col>
        <Col style={{ display: "flex", justifyContent: "center", alignItems: "start" }}>
          <Card className="cardsH">
            <Card.Body>
              <Card.Title>Facturas no pagadas próximas a vencer</Card.Title>
              <ListGroup numbered>
                {facturas.map((factura) => (
                  <ListGroup.Item
                    key={factura.id}
                    as="li"
                    className="d-flex justify-content-between align-items-start"
                  >
                    <div className="ms-2 me-auto">
                      <div className="fw-bold">N° Factura: {factura.numero_factura}</div>
                      Fecha de vencimiento: {formatearFecha(factura.fecha_vencimiento)}
                    </div>
                    <Badge bg="warning" pill style={{ color: "black" }}>
                      {calcularDiasPorVencer(factura.fecha_vencimiento)}
                    </Badge>
                  </ListGroup.Item>
                ))}
              </ListGroup>
              <Button variant="primary" href="/crearFactura" style={{ backgroundColor: "#A5C0DD", border: "none", fontWeight: "bold", color: "black", marginTop: "5px" }}>Registrar factura</Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container >
  );
};

export default Home;
