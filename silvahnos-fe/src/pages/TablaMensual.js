import React, { useEffect } from 'react';
import { Table } from 'react-bootstrap';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';
import { useState } from 'react';
import axios from 'axios';

const mes = new Date().getMonth() + 1;
const idMes = mes.toLocaleString('es-ES', { month: 'long' });
const nombreMes = (Sem1.concat(Sem2))[idMes-1];

const TablaMensual = () => {
    const [montosOrigenIngresos, setMontosOrigenIngresos] = useState([]);
    const [montosOrigenEgresos, setMontosOrigenEgresos] = useState([]);
    const [totalIngresos,setTotalIngresos] = useState(0);
    const [totalEgresos,setTotalEgresos] = useState(0);
    const [anio, setAnio] = useState((new Date()).getFullYear());
    
    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getMontosOrigenIngresos = async () => {
        try {
            let url = 'http://localhost:8090/ingresos/origen/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setTotalIngresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };
    const getMontosOrigenEgresos = async () => {
        try {
            let url = 'http://localhost:8090/egresos/origen/' + anio + '/' + mes;
            const response = await axios.get(url);
            if (response.status === 200) {
                setTotalEgresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenEgresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getMontosOrigenIngresos();
        getMontosOrigenEgresos();
    }, []);

    console.log("fdfsdsad:",montosOrigenIngresos)

    return (
        <Table striped hover>
            <thead>
                <tr>
                    <th></th>
                    <th>{nombreMes}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td style={{ fontWeight: "bold" }}>Ingresos</td>
                    <td></td>
                </tr>
                {montosOrigenIngresos.map((montoOrigenIngreso) => (
                    <tr key={montoOrigenIngreso.id}>
                        <td>{montoOrigenIngreso.origen}</td>
                        <td>{formatoMonto(montoOrigenIngreso.monto_total)}</td>
                    </tr>
                ))}
                <tr>
                    <td style={{ fontWeight: "bold" }}>Ingresos totales</td>
                    <td>{formatoMonto(totalIngresos)}</td>
                </tr>
                <tr>
                    <td style={{ fontWeight: "bold" }}>Egresos</td>
                    <td></td>
                </tr>
                {montosOrigenEgresos.map((montoOrigenEgreso) => (
                    <tr key={montoOrigenEgreso.id}>
                        <td>{montoOrigenEgreso.origen}</td>
                        <td>{formatoMonto(montoOrigenEgreso.monto_total)}</td>
                    </tr>
                ))}
                <tr>
                    <td style={{ fontWeight: "bold" }}>Egresos totales</td>
                    <td>{formatoMonto(totalEgresos)}</td>
                </tr>
                <tr>
                    <td style={{ fontWeight: "bold" }}>Diferencia mes</td>
                    <td>{formatoMonto(totalIngresos-totalEgresos)}</td>
                </tr>
            </tbody>
        </Table>
    );
}

export default TablaMensual;