import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import CategoriasEgreso from '../../components/data/CategoriasEgreso';

const PieChartEgreso = ({ anio, mes }) => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 

    const [montosOrigen, setMontosOrigen] = useState([]);
    const motivos = CategoriasEgreso();

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://' + urlweb + '/montos/egreso/' + anio + '/' + mes;
                const response = await axios.get(url,config);
                if (response.status === 200) {
                    setMontosOrigen(response.data);
                }
            } catch (err) {
                console.log(err.message);
            }
        };

        getMontoOrigen();
    }, [anio, mes]);

    console.log("motivos:", motivos);
    console.log("montosOrigen:", montosOrigen);

    // Generate dynamic background and border colors
    const backgroundColors = motivos.map((item, index) => `rgba(${index+2 * 100}, ${index+1 * 150}, ${index * 75}, 0.2)`);
    const borderColors = motivos.map((item, index) => `rgba(${index+2 * 30}, ${index * 50}, ${index * 70}, 1)`);

    const data = {
        labels: motivos.map((item) => item.nombre),
        datasets: [
            {
                label: 'Monto total: ',
                data: montosOrigen.map((item) => item.monto_total),
                backgroundColor: backgroundColors,
                borderColor: borderColors,
                borderWidth: 1,
            },
        ],
    };

    const options = {
        tooltips: {},
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
