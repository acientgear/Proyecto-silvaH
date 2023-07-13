import React, { useEffect,useCallback } from 'react';
import { Table } from 'react-bootstrap';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../config/config';

const mes = new Date().getMonth() + 1;
const idMes = mes.toLocaleString('es-ES', { month: 'long' });
const nombreMes = (Sem1.concat(Sem2))[idMes - 1];

const TablaMensual = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 
    const [montosOrigenIngresos, setMontosOrigenIngresos] = useState([]);
    const [montosOrigenEgresos, setMontosOrigenEgresos] = useState([]);
    const [totalIngresos, setTotalIngresos] = useState(0);
    const [totalEgresos, setTotalEgresos] = useState(0);
    const [anio] = useState((new Date()).getFullYear());

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getMontosOrigenIngresos = useCallback(async () => {
        try {
            let url = 'http://'+urlweb+'/montos/ingreso/' + anio + '/' + mes;
            const response = await axios.get(url,config);
            if (response.status === 200) {
                setTotalIngresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio]);
    
    const getMontosOrigenEgresos = useCallback(async () => {
        try {
            let url = 'http://'+urlweb+'/montos/egreso/' + anio + '/' + mes;
            const response = await axios.get(url,config);
            if (response.status === 200) {
                setTotalEgresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenEgresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    }, [anio]);

    useEffect(() => {
        getMontosOrigenIngresos();
        getMontosOrigenEgresos();
    }, [getMontosOrigenIngresos, getMontosOrigenEgresos]);

    return (
        <>
            <div key="tabla-mensual">
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
                        {montosOrigenIngresos.map((montoOrigenIngreso, index) => (
                            <tr key={index}>
                                <td style={{textAlign:"center"}}>{montoOrigenIngreso.motivo}</td>
                                <td>{formatoMonto(montoOrigenIngreso.monto_total)}</td>
                            </tr>
                        ))}

                        <tr style={{ fontWeight: "bold" }}>
                            <td >Ingresos totales</td>
                            <td>{formatoMonto(totalIngresos)}</td>
                        </tr>
                        <tr>
                            <td style={{ fontWeight: "bold" }}>Egresos</td>
                            <td></td>
                        </tr>
                        {montosOrigenEgresos.map((montoOrigenEgreso, index) => (
                            <tr key={index}>
                                <td style={{textAlign:"center"}}>{montoOrigenEgreso.motivo}</td>
                                <td>{formatoMonto(montoOrigenEgreso.monto_total)}</td>
                            </tr>
                        ))}
                        <tr style={{ fontWeight: "bold" }}>
                            <td>Egresos totales</td>
                            <td>{formatoMonto(totalEgresos)}</td>
                        </tr>
                        <tr style={{ fontWeight: "bold" }}s>
                            <td>Diferencia mes</td>
                            <td>{formatoMonto(totalIngresos - totalEgresos)}</td>
                        </tr>
                    </tbody>
                </Table>
            </div>
        </>
    );
}

export default TablaMensual;