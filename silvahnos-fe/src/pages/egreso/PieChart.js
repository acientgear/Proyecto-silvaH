import React from 'react';
import { Pie } from 'react-chartjs-2';
import { useState, useEffect } from 'react';
import axios from 'axios';
import urlweb from '../../config/config';
import coloresrgb from '../../components/data/Colores';
import Cookies from 'js-cookie';

const PieChartEgreso = ({ anio, mes }) => {
    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    }; 

    const [montosOrigen, setMontosOrigen] = useState([]);

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

    const data = {
        labels: montosOrigen.map((item) => item.motivo),
        datasets: [
            {
                label: 'Monto total: ',
                data: montosOrigen.map((item) => item.monto_total),
                backgroundColor: coloresrgb.rgb,
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
            <div style={{width:"75%"}}>
                <Pie data={data} options={options} />
            </div>
        </>
    );
};

export default PieChartEgreso;
