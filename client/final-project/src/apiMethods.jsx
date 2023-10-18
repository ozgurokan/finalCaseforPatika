import { useDispatch,useSelector} from "react-redux";
import axios from "axios";

import {storeLogin} from "./redux/auth/authSlice"

const BASE_URL = import.meta.env.VITE_BASE_URL



export const login = async(values) => {
    const request = {username:values.username,password:values.password};
    const response  = await axios.post(BASE_URL+'auth/login',request);

    return response.data;
};

export const register = async(values) => {
    const request = {
        name:values.name,
        surname : values.surname,
        username: values.username,
        password: values.password,
        email: values.email
    };

    const response = await axios.post(BASE_URL+'auth/save',request);

    return response.data;
}

export const changePassword = async(values) => {
    const request = {
        currentPassword : values.currentPassword,
        newPassword : values.newPassword
    }
    
    const response = await axios.put(BASE_URL+`users/${values.userId}`, request,{
        headers: {
            Authorization : `Bearer ${localStorage.getItem("accessToken")}`
            }
    })

    return response.data;
}

export const deleteCar = async(carId) => {
    try{
        await axios.delete(BASE_URL+`cars/${carId}`,{
            headers: {
                Authorization : `Bearer ${localStorage.getItem("accessToken")}`
                }
        });
        const response = "Araç başarıyla silindi";
        return response
    }catch{
        const response = "Araç silinirken bir hata oluştu";
        return response
    }
    
}

export const newCar = async(values) => {
    const response = await axios.post(BASE_URL+`cars`,values,{
        headers: {
            Authorization : `Bearer ${localStorage.getItem("accessToken")}`
            }
    })
    return response;
}

export const fetchOneCar = async(carId) => {
    const response = await axios.get(BASE_URL+`cars/${carId}`,{
        headers: {
            Authorization : `Bearer ${localStorage.getItem("accessToken")}`
            }
    })
    return response;
}

export const updateCar = async(cardId ,request) => {
    const response = await axios.put(BASE_URL+`cars/${cardId}`,request,{
        headers: {
            Authorization : `Bearer ${localStorage.getItem("accessToken")}`
            }
    })
    return response.data;
}
