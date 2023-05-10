import React, { Component, useState } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';
import axios from 'axios';
import Dias from '../components/data/Dias';

const data = [
    { month: 'Enero', ingresos: 18000000, egresos: 26168454, saldoCuenta: -8168454 },
    { month: 'Febrero', ingresos: 20000000, egresos: 18000000, saldoCuenta: 0 },
    { month: 'Marzo', ingresos: 22268362, egresos: 20690022, saldoCuenta: 0 },
    { month: 'Abril', ingresos: 0, egresos: 0, saldoCuenta: 0 },
    { month: 'Mayo', ingresos: 0, egresos: 0, saldoCuenta: 0 }
];

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

class LineChart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            flujos: []
        };
    }

    chartRef = React.createRef();
    myChart = null;

    async componentDidMount() {

        const year = new Date().getFullYear();
        const month = new Date().getMonth();
        const dias = Dias(year, month);

        const flujosDB = await getFlujos();
        this.setState({ flujos: flujosDB });
        console.log(this.state.flujos);

        const myChartRef = this.chartRef.current.getContext('2d');

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
                        data: [this.state.flujos[0].total_ingresos, this.state.flujos[1].total_ingresos, this.state.flujos[2].total_ingresos],
                        backgroundColor: 'rgba(184, 231, 225, 0.2)',
                        borderColor: 'rgba(184, 231, 225, 1)',
                        borderWidth: 1,
                    },
                    {
                        label: 'Egresos',
                        data: [this.state.flujos[0].total_egresos, this.state.flujos[1].total_egresos, this.state.flujos[2].total_egresos],
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