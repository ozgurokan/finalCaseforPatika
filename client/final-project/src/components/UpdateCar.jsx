import { useEffect,useState } from "react";
import {Flex,Box,Heading,Input,FormControl,FormLabel,Button,Alert} from "@chakra-ui/react"
import {useFormik} from "formik";
import {useSelector} from "react-redux";
import {newCarValidations} from "../validation/validation";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";  
import {updateCar} from "../apiMethods"

function UpdateCar() {

    const BASE_URL = import.meta.env.VITE_BASE_URL
    const userIdStore = useSelector((state) => state.auth.userData.userId);
    const usernameStore = useSelector((state) => state.auth.userData.username);
    const navigate = useNavigate();


    const {id,userIdParam} = useParams();
    const [values,setValues] = useState({
        id: id,
        brand: "",
        model : "",
        plate : "",
        user_id : "",
        year: ""
        
    })

    useEffect(() => {

            axios.get(BASE_URL+`cars/${id}`,{headers: {Authorization : `Bearer ${localStorage.getItem("accessToken")}`}})
            .then(res => {
                setValues({...values,brand : res.data.brand,model : res.data.model, year : res.data.year,plate: res.data.car_plate, user_id : res.data.user_id})
                console.log(res.data)
                if((userIdParam != userIdStore) || (userIdParam != res.data.user_id)){
                  navigate("/profile/cars")
                }

            }).catch(err => console.log(err))



      }, []);
      
    const formik = useFormik({
      initialValues : {
        brand : values.brand,
        model : values.model,
        plate : values.plate,
        year : values.year,
      },
      validationSchema :newCarValidations ,
      enableReinitialize : true,
      onSubmit : async(values,bag) => {
        const request = {
            brand : values.brand,
            model : values.model,
            car_plate : values.plate,
            year : values.year,
        }
          try{
            const rspns = await updateCar(id,request)
            console.log(rspns);
            navigate("/profile/cars")
          }catch(e){
             bag.setErrors({general:"Bir Hata oluştu"})
          }
  
        }
    })
    return (
      <Flex alignItems={"center"} width={"full"} justifyContent="center" >
      <Box mt={10} borderWidth={"1px"} p={10} width="25rem" >
        <Box textAlign={"center"}>
          <Heading>Edit Car</Heading>
          <Box textAlign={"left"} mt={5}>
          <form onSubmit={formik.handleSubmit}>
            <FormControl>
  
            <FormLabel mt={4}>Brand</FormLabel>
              <Input id='brandField' name='brand' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.brand}  isInvalid={formik.touched.brand && formik.errors.brand} />
  
              <FormLabel mt={4}>Model</FormLabel>
              <Input id='modelField' name='model' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.model}  isInvalid={formik.touched.model && formik.errors.model} />
  
              <FormLabel mt={4}>Plate</FormLabel>
              <Input id='plateField' name='plate' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.plate} isInvalid={formik.touched.plate && formik.errors.plate}  />
  
              <FormLabel mt={4}>Year</FormLabel>
              <Input id='yearField' name='year' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.year}  isInvalid={formik.touched.year && formik.errors.year} />
            </FormControl>
            <Box my={4}>{formik.errors.general && (<Alert status='error'>{formik.errors.general}</Alert>)}</Box>
            <hr />  
            <Button width={"full"} my={4} colorScheme={"blue"} type={"submit"}>Update</Button>
          </form>
          </Box>
        </Box>
      </Box>
    </Flex>
    )
}

export default UpdateCar