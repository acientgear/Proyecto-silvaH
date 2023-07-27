import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import Colores from '../../components/data/Colores';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';
import CategoriasEgreso from '../../components/data/CategoriasEgreso';

const PieChartEgreso = ({ anio, mes }) => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    }; 

    const coloresrgb = Colores();
    const getColor = (index) => coloresrgb[index % coloresrgb.length].rgb;

    const [montosOrigen, setMontosOrigen] = useState([]);
    const [total, setTotal] = useState(0);

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://' + urlweb + '/motivoMonto/egreso/' + anio + '/' + mes;
                const response = await axios.get(url,config);
                if (response.status === 200) {
                    setMontosOrigen(response.data);
                    setTotal(response.data.reduce((a, b) => a + b.monto_total, 0));
                }
            } catch (err) {
                console.log(err.message);
            }
        };

        getMontoOrigen();
    }, [anio, mes]);

    const data = {
        labels: montosOrigen.map((item) => item.motivo),
        datasets: [
            {
                label: 'Monto total: ',
                data: montosOrigen.map((item) => item.monto_total),
                backgroundColor: montosOrigen.map((item, index) => getColor(index)),
                borderColor: coloresrgb.rgb,
                borderWidth: 1,
            },
        ],
    };

    const options = {
        maintainAspectRatio: false,
        responsive: true,
        plugins: {
            legend: {
                position: 'right',
                rtl: true,
                labels: {
                    usePointStyle: true,
                    pointStyle: 'circle',
                }
            },
            tooltip: {
                callbacks: {
                    label: function (context) {
                        //Show value using custom number formatting.
                        const label = 'Total: $' + context.formattedValue + ' (' + (context.parsed * 100 / total).toFixed(2) + '%)';
                        return label;
                    }
                }
            }
        },
    }

    return (
        <>
            <div key="pie-chart-ingreso" style={{ width: "100%" }}>
                {montosOrigen.length === 0 ?
                    <div
                        width={392}
                        height={392}
                    >
                        <h3>No hay datos para mostrar</h3>
                    </div>
                    :
                    <Pie
                        width={392}
                        height={392}
                        data={data}
                        options={options}
                    />}

            </div>
        </>
    );
};

export default PieChartEgreso;
