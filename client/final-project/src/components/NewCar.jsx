import {useEffect} from 'react'
import {Flex,Box,Heading,Input,FormControl,FormLabel,Button,Text,Alert} from "@chakra-ui/react"
import {useFormik} from "formik";
import {useDispatch,useSelector} from "react-redux";
import {newCarValidations} from "../validation/validation";
import {Link,useNavigate} from "react-router-dom";
import { newCar } from '../apiMethods';
import {storeLogin} from "../redux/auth/authSlice";

function NewCar() {
  
    const dispatch = useDispatch();

    const userId = useSelector((state) => state.auth.userData.userId);
    
    console.log(userId);
  

  
    const formik = useFormik({
      initialValues:{
        brand: "",
        model : "",
        plate: "",
        year: "",
      },
      validationSchema :newCarValidations ,
      onSubmit : async(values,bag) => {
        const request = {
            user_id : userId,
            brand : values.brand,
            model : values.model,
            car_plate : values.plate,
            year : values.year,
        }
          try{
            const response = await newCar(request)
            console.log(response);
          }catch(e){
             bag.setErrors({general:e.response.data.message})
          }
  
        }
    })
  
    return (
      <Flex alignItems={"center"} width={"full"} justifyContent="center" >
      <Box mt={10} borderWidth={"1px"} p={10} width="25rem" >
        <Box textAlign={"center"}>
          <Heading>New Car</Heading>
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
            <Button width={"full"} my={4} colorScheme={"blue"} type={"submit"}>Add</Button>
          </form>
          </Box>
        </Box>
      </Box>
    </Flex>
    )
}

export default NewCar