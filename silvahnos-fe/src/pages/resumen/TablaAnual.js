import { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import { Table, Row, Col } from 'react-bootstrap';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';
import InputYear from '../../components/InputYear';

const TablaAnual = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };

    const [anio, setAnio] = useState((new Date()).getFullYear());
    const [montosIngresosSem1, setMontosIngresosSem1] = useState([]);
    const [montosEgresosSem1, setMontosEgresosSem1] = useState([]);
    const [montosIngresosSem2, setMontosIngresosSem2] = useState([]);
    const [montosEgresosSem2, setMontosEgresosSem2] = useState([]);
    const [ingresosMensualesAnio, setIngresosMensualesAnio] = useState([]);
    const [egresosMensualesAnio, setEgresosMensualesAnio] = useState([]);

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getTotalPorMesAnualIngresos = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/montos/ingresos/totalMesAnual/' + anio;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setIngresosMensualesAnio(response.data);
                setMontosIngresosSem1(response.data.slice(0, 6));
                setMontosIngresosSem2(response.data.slice(6, 12));
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio]);

    const getTotalPorMesAnualEgresos = useCallback(async () => {
        try {
            let url = 'http://' + urlweb + '/montos/egresos/totalMesAnual/' + anio;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setEgresosMensualesAnio(egresosMensualesAnio);
                setMontosEgresosSem1(response.data.slice(0, 6));
                setMontosEgresosSem2(response.data.slice(6, 12));
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio]);

    const calcularSaldoCuenta = (montosIngresos, montosEgresos, x) => {
        let saldoCue = 0;
        if (x === 0) {
            saldoCue = 0;
        } else {
            saldoCue = saldoCueSem1[5];
        }
        return montosIngresos.map((monto, index) => {
            const ingreso = monto.monto_total || 0;
            const egreso = montosEgresos[index]?.monto_total || 0;
            saldoCue += (ingreso - egreso);
            return saldoCue;
        });
    };

    const saldoCueSem1 = calcularSaldoCuenta(montosIngresosSem1, montosEgresosSem1, 0);
    const saldoCueSem2 = calcularSaldoCuenta(montosIngresosSem2, montosEgresosSem2, 1);

    useEffect(() => {
        getTotalPorMesAnualIngresos();
        getTotalPorMesAnualEgresos();
    }, [getTotalPorMesAnualIngresos, getTotalPorMesAnualEgresos]);

    return (
        <>
            <Row>

                <Col xs="{12}">
                    <Table responsive hover>
                        <thead>
                            <tr style={{ background: "#ACB1D6" }}>
                                <th width={125}></th>
                                {Sem1.map((mes) => (
                                    <th key={mes} width={100}>{mes}</th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            <tr style={{ background: "#E6F4DD" }}>
                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                {montosIngresosSem1.map((monto, index) => (
                                    <td key={index}>{formatoMonto(monto.monto_total)}</td>
                                ))}
                            </tr>
                            <tr style={{ background: "#FBE6DD" }}>
                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                {montosEgresosSem1.map((monto, index) => (
                                    <td key={index}>{formatoMonto(monto.monto_total)}</td>
                                ))}
                            </tr>
                            <tr style={{ background: "#B9F3E4" }}>
                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                {saldoCueSem1.map((saldo, index) => (
                                    <td key={index}>{formatoMonto(saldo)}</td>
                                ))}
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <Row>
                <Col xs={12}>
                    <Table responsive hover>
                        <thead>
                            <tr style={{ background: "#ACB1D6" }}>
                                <th width={125}></th>
                                {Sem2.map((mes) => (
                                    <th key={mes} width={100}>{mes}</th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            <tr style={{ background: "#E6F4DD" }}>
                                <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                                {montosIngresosSem2.map((monto, index) => (
                                    <td key={index}>{formatoMonto(monto.monto_total)}</td>
                                ))}
                            </tr>
                            <tr style={{ background: "#FBE6DD" }}>
                                <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                                {montosEgresosSem2.map((monto, index) => (
                                    <td key={index}>{formatoMonto(monto.monto_total)}</td>
                                ))}
                            </tr>
                            <tr style={{ background: "#B9F3E4" }}>
                                <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                                {saldoCueSem2.map((saldo, index) => (
                                    <td key={index}>{formatoMonto(saldo)}</td>
                                ))}
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </>
    )
};

export default TablaAnual;