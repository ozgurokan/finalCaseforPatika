import { useDispatch } from "react-redux";
import { carStoreLogOut } from "../cars/carSlice";

export const getAccessToken = () =>{
    let token = localStorage.getItem("accessToken");

    return token
}

export const setAccessToken = (token) => {
    localStorage.setItem("accessToken", token);
}

export const deleteToken = () => {
    localStorage.removeItem("accessToken");
}


