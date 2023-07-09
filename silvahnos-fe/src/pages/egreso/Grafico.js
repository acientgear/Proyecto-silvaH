import React, { Component } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';
import axios from 'axios';
import urlweb from '../../config/config';

function obtenerNombresUltimos5Dias() {

    const dias = ['dom', 'lun', 'mar', 'mie', 'jue', 'vie', 'sab'];
    const hoy = new Date();
    const nombresDias = [];

    for (let i = 0; i <= 4; i++) {
        const fecha = new Date();
        fecha.setDate(hoy.getDate() - i);
        const nombreDia = dias[fecha.getDay()];
        nombresDias.push(nombreDia);
    }

    return nombresDias;
}

const config = {
    headers: { Authorization: `Bearer ${localStorage.token}` }
}; 

const getMontoPorDia = async (anio, mes, dia) => {
    try {
        let url = 'http://'+urlweb+'/egresos/monto/' + anio + '/' + mes + '/' + dia;
        const response = await axios.get(url,config);
        if (response.status === 200) {
            return response.data;
        }
    } catch (err) {
        console.log(err.message);
    }
};

async function obtenerMontos5Dias() {
    const hoy = new Date();
    const montos = [];

    for (let i = 0; i <= 4; i++) {
        const fecha = new Date();
        fecha.setDate(hoy.getDate() - i);

        const monto = getMontoPorDia(fecha.getFullYear(), fecha.getMonth() + 1, fecha.getDate());
        montos.push(monto);
    }

    const datos = await Promise.all(montos);

    return datos;
}

class LineChart extends Component {

    chartRef = React.createRef();
    myChart = null;

    async componentDidMount() {
        const nombresDias = obtenerNombresUltimos5Dias();

        const montos = await obtenerMontos5Dias();

        const myChartRef = this.chartRef.current.getContext('2d');

        if (this.myChart) {
            this.myChart.destroy();
        }

        this.myChart = new Chart(myChartRef, {
            type: 'line',
            data: {
                labels: [nombresDias[4], nombresDias[3], nombresDias[2], nombresDias[1], nombresDias[0]],
                datasets: [
                    {
                        label: 'Egresos',
                        data: [montos[4], montos[3], montos[2], montos[1], montos[0]],
                        backgroundColor: 'rgba(242, 182, 160, 0.2)',
                        borderColor: 'rgba(242, 182, 160, 1)',
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
            <>
                <div key="grafico-egresos">
                    <canvas
                        id="myChart"
                        ref={this.chartRef}
                    />
                </div>
            </>
        );
    }
}

export default LineChart;