import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';

const options = {
    tooltips: {
        callbacks: {
            label: function (tooltipItem, data) {
                var dataset = data.datasets[tooltipItem.datasetIndex];
                var total = dataset.data.reduce(function (previousValue, currentValue, currentIndex, array) {
                    return previousValue + currentValue;
                });
                var currentValue = dataset.data[tooltipItem.index];
                var percentage = Math.floor((currentValue / total) * 100 + 0.5);
                return percentage + '%';
            },
        },
    },
};

const PieChartEgreso = () => {

    const [montosOrigen, setMontosOrigen] = useState([]);

    useEffect(() => {
        const getMontoOrigen = async (anio, mes) => {
            try {
                let url = 'http://localhost:8090/egresos/origen/' + anio + '/' + mes;
                const response = await axios.get(url);
                if (response.status === 200) {
                    setMontosOrigen(response.data);
                }
            } catch (err) {
                console.log(err.message);
            }
        };

        getMontoOrigen(anio, mes);
    }, []);

    console.log(montosOrigen);
    console.log(montosOrigen.map((item) => item.monto_total));

    const fecha = new Date();
    const anio = fecha.getFullYear();
    const mes = fecha.getMonth() + 1;

    const data = {
        labels: montosOrigen.map((item) => item.origen),
        datasets: [
            {
                label: '# of Votes',
                data: montosOrigen.map((item) => item.monto_total),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(240 , 168, 60, 0.2)',
                    'rgba(228, 240, 60, 0.2)',
                    'rgba(97, 226, 118, 0.2)',
                    'rgba(60, 240, 228, 0.2)',
                    'rgba(60, 90, 240, 0.2)',
                    'rgba(203, 87, 226, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(240 , 168, 60, 1)',
                    'rgba(228, 240, 60, 1)',
                    'rgba(97, 226, 118, 1)',
                    'rgba(60, 240, 228, 1)',
                    'rgba(60, 90, 240, 1)',
                    'rgba(203, 87, 226, 1)',

                ],
                borderWidth: 1,
            },
        ],
    };

    return (
        <>
            <Pie data={data} options={options} />
        </>
    );
};

export default PieChartEgreso;