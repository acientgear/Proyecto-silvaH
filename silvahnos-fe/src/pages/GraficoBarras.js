import React from 'react';
import { Bar } from 'react-chartjs-2';
import Sem1 from '../components/data/Sem1';
import Sem2 from '../components/data/Sem2';

const data = {
    labels: Sem1.concat(Sem2),
    datasets: [
        {
            label: 'Ingresos',
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1,
            hoverBackgroundColor: 'rgba(255, 99, 132, 0.4)',
            hoverBorderColor: 'rgba(255, 99, 132, 1)',
            data: [65, 59, 80, 81, 56]
        },
        {
            label: 'Egresos',
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            hoverBackgroundColor: 'rgba(54, 162, 235, 0.4)',
            hoverBorderColor: 'rgba(54, 162, 235, 1)',
            data: [28, 48, 40, 19, 86]
        }
    ]
};

const options = {

};

const DoubleBarChart = () => {
    return (
        <div>
            <Bar
                data={data}
                options={options}
            />
        </div>
    );
};

export default DoubleBarChart;

