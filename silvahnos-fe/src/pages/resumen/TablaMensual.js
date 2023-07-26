import React, { useEffect, useCallback } from 'react';
import { Table, Card } from 'react-bootstrap';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';
import { useState } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const TablaMensual = ({anio,mes}) => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };
    const [montosOrigenIngresos, setMontosOrigenIngresos] = useState([]);
    const [montosOrigenEgresos, setMontosOrigenEgresos] = useState([]);
    const [totalIngresos, setTotalIngresos] = useState(0);
    const [totalEgresos, setTotalEgresos] = useState(0);
    //const [anio] = useState((new Date()).getFullYear());

    const idMes = mes.toLocaleString('es-ES', { month: 'long' });
    const nombreMes = (Sem1.concat(Sem2))[idMes - 1];

    const formatoMonto = (monto) => {
        const montoFormateado = monto.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
        return montoFormateado;
    };

    const getMontosOrigenIngresos = async (anio,mes) => {
        try {
            let url = 'http://' + urlweb + '/motivoMonto/ingreso/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setTotalIngresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenIngresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    const getMontosOrigenEgresos = async (anio,mes) => {
        try {
            let url = 'http://' + urlweb + '/motivoMonto/egreso/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                setTotalEgresos(response.data.reduce((total, monto) => total + monto.monto_total, 0));
                setMontosOrigenEgresos(response.data);
            }
        } catch (err) {
            console.log(err.message);
        }
    };

    useEffect(() => {
        getMontosOrigenIngresos(anio,mes);
        getMontosOrigenEgresos(anio,mes);
    }, [anio,mes]);

    return (
        <>
            <div key="tabla-mensual">
                <Card className="cardsH">
                    <Card.Body>
                        <Card.Title>Registros por categoria</Card.Title>
                        <Table responsive="sm" hover style={{ boxShadow: "initial" }}>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>{nombreMes}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style={{ fontWeight: "bold", width: "50%" }}>Ingresos</td>
                                    <td style={{ width: "50%" }}></td>
                                </tr>
                            </tbody>
                        </Table>
                        <div style={{ maxHeight: "162.16px", overflowY: "auto" }}>
                            <Table responsive="sm" hover>
                                <tbody>
                                    {montosOrigenIngresos.map((montoOrigenIngreso, index) => (
                                        <tr key={index}>
                                            <td style={{ textAlign: "center", width: "50%" }}>{montoOrigenIngreso.motivo}</td>
                                            <td style={{ width: "50%" }}>{formatoMonto(montoOrigenIngreso.monto_total)}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </Table>
                        </div>
                        <Table responsive="sm" hover style={{ boxShadow: "initial" }}>
                            <tbody>
                                <tr style={{ fontWeight: "bold" }}>
                                    <td style={{ width: "50%" }}>Ingresos totales</td>
                                    <td style={{ width: "50%" }}>{formatoMonto(totalIngresos)}</td>
                                </tr>
                                <tr>
                                    <td style={{ fontWeight: "bold", width: "50%" }}>Egresos</td>
                                    <td style={{ width: "50%" }}></td>
                                </tr>
                            </tbody>
                        </Table>
                        <div style={{ maxHeight: "162.16px", overflowY: "auto" }}>
                            <Table responsive="sm" hover>
                                <tbody>
                                    {montosOrigenEgresos.map((montoOrigenEgreso, index) => (
                                        <tr key={index}>
                                            <td style={{ textAlign: "center", width: "50%" }}>{montoOrigenEgreso.motivo}</td>
                                            <td style={{ width: "50%" }}>{formatoMonto(montoOrigenEgreso.monto_total)}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </Table>
                        </div>
                        <Table responsive="sm" hover style={{ boxShadow: "initial" }}>
                            <tbody>
                                <tr style={{ fontWeight: "bold" }}>
                                    <td style={{ width: "50%" }}>Egresos totales</td>
                                    <td style={{ width: "50%" }}>{formatoMonto(totalEgresos)}</td>
                                </tr>
                                <tr style={{ fontWeight: "bold" }}>
                                    <td style={{ width: "50%" }}>Diferencia mes</td>
                                    <td style={{ width: "50%" }}>{formatoMonto(totalIngresos - totalEgresos)}</td>
                                </tr>
                            </tbody>
                        </Table>

                    </Card.Body>
                </Card>
            </div>
        </>
    );

}

export default TablaMensual;