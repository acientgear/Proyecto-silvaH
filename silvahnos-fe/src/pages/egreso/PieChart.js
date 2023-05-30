import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';

const PieChartEgreso = ({ anio, mes }) => {

    const [montosOrigen, setMontosOrigen] = useState([]);

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://'+urlweb+'/montos/egreso/' + anio + '/' + mes;
                const response = await axios.get(url);
                if (response.status === 200) {
                    setMontosOrigen(response.data);
                }
            } catch (err) {
                console.log(err.message);
            }
        };

        getMontoOrigen();
    }, [anio, mes]);

    const data = {
        labels: montosOrigen.map((item) => item.origen),
        datasets: [
            {
                label: 'Monto total: ',
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

    const options = {
        tooltips: {
        },
    };

    return (
        <>
            <div key="pie-chart-egreso">
                <Pie data={data} options={options} />
            </div>
        </>
    );
};

export default PieChartEgreso;