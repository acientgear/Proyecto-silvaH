import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';

const PieChartIngreso = ({anio,mes}) => {

    const [montosOrigen, setMontosOrigen] = useState([]);

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://localhost:8090/montos/ingreso/' + anio + '/' + mes;
                const response = await axios.get(url);
                if (response.status === 200) {
                    setMontosOrigen(response.data);
                }
            } catch (err) {
                console.log(err.message);
            }
        };

        getMontoOrigen();
    }, [anio,mes]);

    const data = {
        labels: montosOrigen.map((item) => item.origen),
        datasets: [
            {
                label: 'Monto total',
                data: montosOrigen.map((item) => item.monto_total),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(240 , 168, 60, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(240 , 168, 60, 1)',
                ],
                borderWidth: 1,
            },
        ],
    };

    const options = {
        plugins: {
            datalabels: {
                formatter: (value, ctx) => {
                    let sum = 0;
                    let dataArr = ctx.chart.data.datasets[0].data;
                    dataArr.map((data) => {
                        sum += data;
                        return sum;
                    });
                    let percentage = ((value * 100) / sum).toFixed(2) + "%";
                    return percentage;
                },
                color: '#000',
            },
        },
    };

    return (
        <>
            <Pie data={data} options={options} />
        </>
    );
};

export default PieChartIngreso;