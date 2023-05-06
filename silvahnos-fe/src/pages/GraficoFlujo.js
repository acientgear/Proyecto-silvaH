import React, { Component, useState } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';

const data = [
    { month: 'Enero', ingresos: 18000000, egresos: 26168454, saldoCuenta: -8168454 },
    { month: 'Febrero', ingresos: 20000000, egresos: 18000000, saldoCuenta: 0 },
    { month: 'Marzo', ingresos: 22268362, egresos: 20690022, saldoCuenta: 0 },
    { month: 'Abril', ingresos: 0, egresos: 0, saldoCuenta: 0 },
    { month: 'Mayo', ingresos: 0, egresos: 0, saldoCuenta: 0 }
];

class LineChart extends Component {
    chartRef = React.createRef();
    myChart = null;

    componentDidMount() {
        const myChartRef = this.chartRef.current.getContext('2d');

        if (this.myChart) {
            this.myChart.destroy();
        }

        this.myChart = new Chart(myChartRef, {
            type: 'line',
            data: {
                labels: ['enero', 'febrero', 'marzo'],
                datasets: [
                    {
                        label: 'Ingresos',
                        data: [data[0].ingresos, data[1].ingresos, data[2].ingresos],
                        backgroundColor: 'rgba(184, 231, 225, 0.2)',
                        borderColor: 'rgba(184, 231, 225, 1)',
                        borderWidth: 1,
                    },
                    {
                        label: 'Egresos',
                        data: [data[0].egresos, data[1].egresos, data[2].egresos],
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