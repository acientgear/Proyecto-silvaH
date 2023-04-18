import axios from 'axios';
import Table from 'react-bootstrap/Table';

const Ingresos = () => {
    const [ingresos, setIngresos] = useState([]);

    const getIngresos = async () => {
        try{
            let url = 'http://localhost:8090/ingresos';
            const response = await axios.get(url);
            if(response.status === 200){
                setIngresos(response.data);
            }
        }catch(err){
            console.log(err.message);
        } 
    };

    useEffect(() => {
        getIngresos();
    }, []);

    return (
        <div>
            <h1>Listado de ingresos</h1>
        </div>
    );
};

export default Ingresos;