import React from 'react';
import { Card } from 'react-bootstrap';
import GraficoBarras from './GraficoBarras';

const PruebaGrafico = () => {
    return (
        <Card >
            <Card.Body>
                <Card.Title>Ingresos y egresos</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">Anual</Card.Subtitle>
                <div style={{ width: "100%", height: "600px", margin: "auto", justifyContent: "center" }}>
                    <GraficoBarras style={{ height: "100px" }} />
                </div>
            </Card.Body>
        </Card>
    )
};

export default PruebaGrafico;