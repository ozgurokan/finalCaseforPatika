import {useEffect} from 'react'
import {Flex,Box,Heading,Input,FormControl,FormLabel,Button,Text,Alert} from "@chakra-ui/react"
import {useFormik} from "formik";
import {useDispatch,useSelector} from "react-redux";
import {validationsRegister} from "../validation/validation";
import {Link,useNavigate} from "react-router-dom";
import { register } from '../apiMethods';
import {storeLogin} from "../redux/auth/authSlice";

function Register() {


  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn)

  const navigate = useNavigate();

  const checkLoggedIn = (a) => {
    if(a){
      navigate("/")
    };
  };

  useEffect(() =>{
    checkLoggedIn(isLoggedIn);
  },[])

  const formik = useFormik({
    initialValues:{
      name: "",
      surname : "",
      username: "",
      password: "",
      passwordConfirm: "",
      email : "",
    },
    validationSchema :validationsRegister ,
    onSubmit : async(values,bag) => {
        try{
          const response = await register(values);
          console.log(response);
          dispatch(storeLogin(response))
          navigate("/");
        }catch(e){
           bag.setErrors({general:e.response.data.message})
        }

      }
  })

  return (
    <Flex alignItems={"center"} width={"full"} justifyContent="center" >
    <Box mt={10} borderWidth={"1px"} p={10} width="25rem" >
      <Box textAlign={"center"}>
        <Heading>Sign Up</Heading>
        <Text mt={4} fontSize={18} textAlign="center" fontWeight={300}>Please sign up to continue</Text>
        <Box textAlign={"left"} mt={5}>
        <form onSubmit={formik.handleSubmit}>
          <FormControl>

          <FormLabel mt={4}>Name</FormLabel>
            <Input id='namefield' name='name' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.name} isInvalid={formik.touched.name && formik.errors.name}/>

            <FormLabel mt={4}>Surname</FormLabel>
            <Input id='surnamefield' name='surname' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.surname} isInvalid={formik.touched.surname && formik.errors.surname}/>

            <FormLabel mt={4}>Username</FormLabel>
            <Input id='usernamefield' name='username' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.username} isInvalid={formik.touched.username && formik.errors.username}/>

            <FormLabel mt={4}>Password</FormLabel>
            <Input id='passwordfield' type="password" name='password' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.password} isInvalid={formik.touched.password && formik.errors.password}/>

            <FormLabel mt={4}>Password Confirm</FormLabel>
            <Input id='passwordConfirmfield' type="password" name='passwordConfirm' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.passwordConfirm} isInvalid={formik.touched.passwordConfirm && formik.errors.passwordConfirm}/>

            <FormLabel mt={4}>Email</FormLabel>
            <Input type="email" name='email' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.email} isInvalid={formik.touched.email && formik.errors.email}/>
          </FormControl>
          <Box my={4}>{formik.errors.general && (<Alert status='error'>{formik.errors.general}</Alert>)}</Box>
          <Button width={"full"} my={4} colorScheme={"blue"} type={"submit"}>Sign Up</Button>
          <hr />  
          <Text align={"center"}  fontSize={14} mt={3}> Already have an account? <Link to="/login" className="form-navigator">Sign In</Link> </Text>  
        </form>
        </Box>
      </Box>
    </Box>
  </Flex>
  )
}

export default Register