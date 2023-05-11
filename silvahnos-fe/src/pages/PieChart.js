import React from 'react';
import { Pie } from 'react-chartjs-2';

const data = {
    labels: ['Astara', 'Sueldos', 'Otros'],
    datasets: [
        {
            label: '# of Votes',
            data: [15, 5, 10],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
            ],
            borderWidth: 1,
        },
    ],
};

const options = {
    tooltips: {
        callbacks: {
            label: function (tooltipItem, data) {
                var dataset = data.datasets[tooltipItem.datasetIndex];
                var total = dataset.data.reduce(function (previousValue, currentValue, currentIndex, array) {
                    return previousValue + currentValue;
                });
                var currentValue = dataset.data[tooltipItem.index];
                var percentage = Math.floor((currentValue / total) * 100 + 0.5);
                return percentage + '%';
            },
        },
    },
};

const PieChart = () => (
    <>
        <Pie data={data} options={options} />
    </>
);

export default PieChart;