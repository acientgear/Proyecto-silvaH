import React, { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';
import { Bar } from 'react-chartjs-2';
import Sem1 from '../../components/data/Sem1';
import Sem2 from '../../components/data/Sem2';
import axios from 'axios';
import urlweb from '../../config/config';
import Cookies from 'js-cookie';

const DoubleBarChart = ({anio}) => {

    const config = {
        headers: { Authorization: `Bearer ${Cookies.get("token")}` }
    };
    
    const getTotalPorMes = async (tipo, anio, mes) => {
        try {
            let url = 'http://' + urlweb + '/' + tipo + '/total/' + anio + '/' + mes;
            const response = await axios.get(url, config);
            if (response.status === 200) {
                return response.data;
            }
        } catch (err) {
            console.log(err.message);
        }
    }
    
    async function getTotalMontosPorMes(tipo,anio) {
        const montos = [];
        const fecha = new Date();
        //const anio = fecha.getFullYear();
    
        for (let i = 1; i <= 12; i++) {
            const monto = getTotalPorMes(tipo, anio, i);
            montos.push(monto);
        }
    
        const datos = await Promise.all(montos);
    
        return datos;
    }

    const [montosIngresos, setMontosIngresos] = useState([]);
    const [montosEgresos, setMontosEgresos] = useState([]);

    useEffect(() => {
        const fetchMontos = async () => {
            const tipo1 = 'ingresos';
            const tipo2 = 'egresos';

            const montosIngresos = await getTotalMontosPorMes(tipo1,anio);
            const montosEgresos = await getTotalMontosPorMes(tipo2,anio);

            setMontosIngresos(montosIngresos);
            setMontosEgresos(montosEgresos);
        };

        fetchMontos();
    }, [anio]);

    const data = {
        labels: Sem1.concat(Sem2),
        datasets: [
            {
                label: 'Ingresos',
                backgroundColor: 'rgba(75, 192, 192, 0.9)',
                borderColor: 'rgba(75, 192, 192, 0.9)',
                borderWidth: 1,
                hoverBackgroundColor: 'rgba(75, 192, 192, 0.9)',
                hoverBorderColor: 'rgba(75, 192, 192, 0.9)',
                data: montosIngresos
            },
            {
                label: 'Egresos',
                backgroundColor: 'rgba(255, 99, 132, 0.9)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1,
                hoverBackgroundColor: 'rgba(255, 99, 132, 0.9)',
                hoverBorderColor: 'rgba(255, 99, 132, 1)',
                data: montosEgresos
            }
        ]
    };

    const options = {};

    return (
        <>
            <Container>
                <Bar
                    data={data}
                    options={options}
                />
            </Container>
        </>
    );
};

export default DoubleBarChart;

