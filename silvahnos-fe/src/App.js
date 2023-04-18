import { Container } from 'react-bootstrap';
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from './pages/Home';
import Layout from "./Layout";

function App() {
  return (
    <Layout>
      <Container style={{ marginTop: "100px" }}>
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </Container>
    </Layout>

  );
}

export default App;
