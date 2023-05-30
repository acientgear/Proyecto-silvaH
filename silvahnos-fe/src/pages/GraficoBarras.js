import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';
import axios from 'axios';
import urlweb from '../config/config';

const getTotalPorMes = async (tipo, anio, mes) => {
    try {
        let url = 'http://'+urlweb+'/' + tipo + '/total/' + anio + '/' + mes;
        const response = await axios.get(url);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.log(err.message);
    }
}

async function getTotalMontosPorMes(tipo) {
    const montos = [];
    const fecha = new Date();
    const anio = fecha.getFullYear();

    for (let i = 1; i <= 12; i++) {
        const monto = getTotalPorMes(tipo, anio, i);
        montos.push(monto);
    }

    const datos = await Promise.all(montos);

    return datos;
}

const DoubleBarChart = () => {
    const [montosIngresos, setMontosIngresos] = useState([]);
    const [montosEgresos, setMontosEgresos] = useState([]);

    useEffect(() => {
        const fetchMontos = async () => {
            const tipo1 = 'ingresos';
            const tipo2 = 'egresos';

            const montosIngresos = await getTotalMontosPorMes(tipo1);
            const montosEgresos = await getTotalMontosPorMes(tipo2);

            setMontosIngresos(montosIngresos);
            setMontosEgresos(montosEgresos);
        };

        fetchMontos();
    }, []);

    const data = {
        labels: Sem1.concat(Sem2),
        datasets: [
            {
                label: 'Ingresos',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
                hoverBackgroundColor: 'rgba(255, 99, 132, 0.4)',
                hoverBorderColor: 'rgba(255, 99, 132, 1)',
                data: montosIngresos
            },
            {
                label: 'Egresos',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
                hoverBackgroundColor: 'rgba(54, 162, 235, 0.4)',
                hoverBorderColor: 'rgba(54, 162, 235, 1)',
                data: montosEgresos
            }
        ]
    };

    const options = {};

    return (
        <>
            <div key="grafico-barras">
                <div>
                    <Bar
                        data={data}
                        options={options}
                    />
                </div>
            </div>
        </>
    );
};

export default DoubleBarChart;

