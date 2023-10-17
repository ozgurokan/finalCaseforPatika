import { createSlice } from "@reduxjs/toolkit";
import {fetchCars} from "../auth/thunks";


const carsSlice = createSlice({
    name: "cars",
    initialState:{
        data : [],
        isLoading : false,
        error : null
    },
    reducers:{
        carStoreLogOut : (state) => {
            state.data = [];
            isLoading = false;
            error = null;
        }
    },
    extraReducers : (builder) => {
        builder.addCase(fetchCars.pending, (state) => {
            state.isLoading = true;
        }),
        builder.addCase(fetchCars.fulfilled, (state,action) => {
            state.data = action.payload;
            state.isLoading = false;
        }),
        builder.addCase(fetchCars.rejected, (state,action) => {
            state.error = action.error.message;
            state.isLoading = false;
        })
    }
})

export const {carStoreLogOut} = carsSlice.actions;
export default carsSlice.reducer;