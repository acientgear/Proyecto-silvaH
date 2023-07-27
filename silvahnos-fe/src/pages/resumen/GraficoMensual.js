import React, { useEffect, useRef } from 'react';
import { Chart } from 'chart.js';
import 'chart.js/auto';
import axios from 'axios';
import Dias from '../../components/data/Dias';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const LineChart = ({ anio, mes }) => {
    const chartRef = useRef();
    const myChartRef = useRef(null);

    useEffect(() => {
        const fetchData = async () => {
            const config = {
                headers: { Authorization: `Bearer ${Cookies.get("token")}` }
            };

            const getMontoPorDia = async (tipo, anio, mes) => {
                try {
                    let url = 'http://' + urlweb + '/' + tipo + '/monto/' + anio + '/' + mes;
                    const response = await axios.get(url, config);
                    if (response.status === 200) {
                        return response.data;
                    }
                } catch (err) {
                    console.log(err.message);
                }
            };

            const dias = Dias(anio, mes-1);

            let tipo1 = 'ingresos';
            let tipo2 = 'egresos';
            const montosIngresos = await getMontoPorDia(tipo1, anio, mes);
            const montosEgresos = await getMontoPorDia(tipo2, anio, mes);

            if (myChartRef.current) {
                myChartRef.current.destroy();
            }

            const myChart = new Chart(chartRef.current, {
                type: 'line',
                data: {
                    labels: dias,
                    datasets: [
                        {
                            label: 'Ingresos',
                            data: montosIngresos,
                            backgroundColor: 'rgba(184, 231, 225, 0.6)',
                            borderColor: 'rgba(184, 231, 225, 1)',
                            borderWidth: 1,
                        },
                        {
                            label: 'Egresos',
                            data: montosEgresos,
                            backgroundColor: 'rgba(255, 99, 132, 0.6)',
                            borderColor: 'rgba(255, 99, 132, 1)',
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

            myChartRef.current = myChart;
        };

        fetchData();
    }, [anio, mes]);

    return (
        <>
            <div>
                <canvas
                    id="myChart"
                    ref={chartRef}
                />
            </div>
        </>
    );
};

export default LineChart;
