import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import urlweb from "../../config/config";

const CategoriaEgresos = () => {
    const config = {
        headers: { Authorization: `Bearer ${localStorage.token}` }
    }; 
    const [data, setData] = useState([])

    const get = useCallback(async () => {
        try{
            let url = 'http://' + urlweb + '/motivosE';
            const response = await axios.get(url,config)
            if(response.status === 200){
                setData(response.data)
            }
        }catch(err){
            console.log(err.message)
        }
    }, [])

    useEffect(() => {
        get();
    }, [get]);
    
    return data;
}

export default CategoriaEgresos;