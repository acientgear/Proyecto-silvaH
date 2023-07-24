import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import Colores from '../../components/data/Colores';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const PieChartEgreso = ({ anio, mes }) => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    }; 

    const coloresrgb = Colores();
    const getColor = (index) => coloresrgb[index % coloresrgb.length].rgb;

    const [montosOrigen, setMontosOrigen] = useState([]);

    useEffect(() => {
        const getMontoOrigen = async () => {
            try {
                let url = 'http://' + urlweb + '/motivoMonto/egreso/' + anio + '/' + mes;
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
        tooltips: {},
    };

    return (
        <>
            <div style={{width:"100%"}}>
                <Pie data={data} options={options} />
            </div>
        </>
    );
};

export default PieChartEgreso;
