import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import {setAccessToken,getAccessToken,deleteToken} from "../auth/helper"


const BASE_URL = import.meta.env.VITE_BASE_URL



export const fetchCars = createAsyncThunk("user/cars", async(userId) => {
    const response = await axios.get(BASE_URL+`cars?userId=${userId}`,{
        headers: {
            Authorization : `Bearer ${localStorage.getItem("accessToken")}`
            }
    });
    return response.data;
})



