import React, { Component, useState } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';
import axios from 'axios';
import Dias from '../components/data/Dias';

const getMontoPorDia = async (tipo, anio, mes, dia) => {
    try {
        let url = 'http://localhost:8090/' + tipo + '/monto/' + anio + '/' + mes + '/' + dia;
        const response = await axios.get(url);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.log(err.message);
    }
};

async function obtenerMontosMes(tipo) {
    const montos = [];
    const fecha = new Date();
    const dias = Dias(fecha.getFullYear(), fecha.getMonth());

    for (let i = 1; i <= dias.length; i++) {
        const monto = getMontoPorDia(tipo, fecha.getFullYear(), fecha.getMonth() + 1, i);
        montos.push(monto);
    }

    const datos = await Promise.all(montos);

    return datos;
}

const getFlujos = async () => {
    try {
        let url = 'http://localhost:8090/usuario/flujo';
        const response = await axios.get(url);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.log(err.message);
    }
}

class LineChart extends Component {

    chartRef = React.createRef();
    myChart = null;

    async componentDidMount() {

        const year = new Date().getFullYear();
        const month = new Date().getMonth();
        const dias = Dias(year, month);

        const myChartRef = this.chartRef.current.getContext('2d');

        let tipo1 = 'ingresos';
        let tipo2 = 'egresos';
        const montosIngresos = await obtenerMontosMes(tipo1);
        const montosEgresos = await obtenerMontosMes(tipo2);

        if (this.myChart) {
            this.myChart.destroy();
        }

        this.myChart = new Chart(myChartRef, {
            type: 'line',
            data: {
                labels: dias,
                datasets: [
                    {
                        label: 'Ingresos',
                        data: montosIngresos,
                        backgroundColor: 'rgba(184, 231, 225, 0.2)',
                        borderColor: 'rgba(184, 231, 225, 1)',
                        borderWidth: 1,
                    },
                    {
                        label: 'Egresos',
                        data: montosEgresos,
                        backgroundColor: 'rgba(242, 182, 160, 0.2)',
                        borderColor: 'rgba(242, 182, 160, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true,
                    }
                }
            },
        });
    }

    render() {
        return (
            <canvas
                id="myChart"
                ref={this.chartRef}
            />
        );
    }
}

export default LineChart;