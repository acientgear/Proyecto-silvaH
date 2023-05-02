import React, { Component } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';

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
                labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'],
                datasets: [
                    {
                        label: 'Ingresos',
                        data: [30000, 8000, 4950, 50000, 40000, 200000],
                        backgroundColor: 'rgba(184, 231, 225, 0.2)',
                        borderColor: 'rgba(184, 231, 225, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,

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