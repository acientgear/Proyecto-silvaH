import { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const TablaAnual = () => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };

    const [montosIngresosSem1, setMontosIngresosSem1] = useState([]);
    const [montosEgresosSem1, setMontosEgresosSem1] = useState([]);
    const [montosIngresosSem2, setMontosIngresosSem2] = useState([]);
    const [montosEgresosSem2, setMontosEgresosSem2] = useState([]);
    let saldoCue = 0;

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getTotalPorMes = useCallback(async (tipo, anio, mes) => {
        try {
            let url = 'http://' + urlweb + '/' + tipo + '/total/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                return response.data;
            }
        } catch (err) {
            console.log(err.message);
        }
    }, []);

    const getTotalMontosPorMes = useCallback(async (tipo) => {
        const montos = [];
        const fecha = new Date();
        const anio = fecha.getFullYear();

        for (let i = 1; i <= 12; i++) {
            const monto = await getTotalPorMes(tipo, anio, i);
            montos.push(monto);
        }

        const datos = await Promise.all(montos);

        return datos;
    }, [getTotalPorMes]);

    const fetchMontos = useCallback(async () => {
        const tipo1 = 'ingresos';
        const tipo2 = 'egresos';

        const montosIngresos = await getTotalMontosPorMes(tipo1);
        const montosEgresos = await getTotalMontosPorMes(tipo2);

        setMontosIngresosSem1(montosIngresos.slice(0, 6));
        setMontosIngresosSem2(montosIngresos.slice(6, 12));
        setMontosEgresosSem1(montosEgresos.slice(0, 6));
        setMontosEgresosSem2(montosEgresos.slice(6, 12));
    }, [getTotalMontosPorMes]);

    useEffect(() => {
        fetchMontos();
    }, [fetchMontos]);

    return (
        <>
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
                        {montosIngresosSem1.map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                    </tr>
                    <tr style={{ background: "#FBE6DD" }}>
                        <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                        {montosEgresosSem1.map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                    </tr>
                    <tr style={{ background: "#B9F3E4" }}>
                        <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                        {montosIngresosSem1.map((monto, index) => {
                            saldoCue = saldoCue + monto - montosEgresosSem1[index];
                            return <td key={index}>{formatoMonto(saldoCue)}</td>;
                        })}
                    </tr>
                </tbody>
            </Table>
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
                        {montosIngresosSem2.map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                    </tr>
                    <tr style={{ background: "#FBE6DD" }}>
                        <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                        {montosEgresosSem2.map((monto, index) => (<td key={index}>{formatoMonto(monto)}</td>))}
                    </tr>
                    <tr style={{ background: "#B9F3E4" }}>
                        <td style={{ fontWeight: "bold" }}>Saldo cuenta</td>
                        {montosIngresosSem2.map((monto, index) => {
                            saldoCue = saldoCue + monto - montosEgresosSem2[index];
                            return <td key={index}>{formatoMonto(saldoCue)}</td>;
                        })}
                    </tr>
                </tbody>
            </Table>
        </>
    )
};

export default TablaAnual;