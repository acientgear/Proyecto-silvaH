import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import CategoriasIngreso from '../../components/data/CategoriasIngreso';

const PieChartIngreso = ({anio,mes}) => {

    const [montosOrigen, setMontosOrigen] = useState([]);
    const motivos = CategoriasIngreso();

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://'+urlweb+'/montos/ingreso/' + anio + '/' + mes;
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
        labels: montosOrigen.map((item) => motivos.nombre),
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
        tooltips: {
        },
    };

    return (
        <>
        <div key="pie-chart-ingreso">

            <Pie data={data} options={options} />
        </div>
        </>
    );
};

export default PieChartIngreso;