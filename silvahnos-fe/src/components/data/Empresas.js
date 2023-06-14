import { useState, useEffect } from "react";
import axios from "axios";
import urlweb from "../../config/config";

const Empresas = () => {
    const [data, setData] = useState([])

    const get = async () => {
        try{
            let url = 'http://' + urlweb + '/empresas';
            const response = await axios.get(url)
            if(response.status === 200){
                setData(response.data)
            }
        }catch(err){
            console.log(err.message)
        }
    }

    useEffect(() => {
        get();
    }, [get]);
    
    return data;
}

export default Empresas;