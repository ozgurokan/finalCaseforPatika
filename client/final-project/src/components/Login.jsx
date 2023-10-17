import {useDispatch,useSelector} from "react-redux";
import {Link,useNavigate} from "react-router-dom"
import { Flex,Box,Heading,FormControl,FormLabel,Text,Input,Button,Alert } from '@chakra-ui/react'
import {useFormik} from "formik"
import {validationsLogin} from "../validation/validation";
import {storeLogin} from "../redux/auth/authSlice"
import {login} from "../apiMethods";
import {useEffect} from "react";






function Login() {
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
      username: "",
      password: ""
    },
    validationSchema : validationsLogin,
    onSubmit : async(values,bag) => {   

        try{
          const response = await login(values);
          dispatch(storeLogin(response))
          navigate("/");
        }catch(e){
          bag.setErrors({general : "Username or password is incorrect"})
        }

      }
  })


  return (
    <Flex alignItems={"center"} width={"full"} justifyContent="center" >
      <Box mt={20} borderWidth={"1px"} p={10} width="25rem" >
        <Box textAlign={"center"}>
          <Heading>Sign In</Heading>
          <Text mt={4} fontSize={18} textAlign="center" fontWeight={300}>Please sign in to continue</Text>
          <Box textAlign={"left"} mt={10}>
          <form onSubmit={formik.handleSubmit}>
            <FormControl>
              <FormLabel>Username</FormLabel>
              <Input id='usernamefield' name='username' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.username} isInvalid={formik.touched.username && formik.errors.username}/>
              <FormLabel mt={4}>Password</FormLabel>
              <Input type="password" name='password' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.values.password} isInvalid={formik.touched.password && formik.errors.password}/>
            </FormControl>
            {<Box  my={4}>{formik.errors.general && (<Alert justifyContent={"center"} status='error'>{formik.errors.general}</Alert>)}</Box>}
            <Button width={"full"} my={4} colorScheme={"blue"} type={"submit"}>Login</Button>
            <hr />
            <Text align={"center"}  fontSize={14} mt={3}>  Not have an account yet? <Link to="/register" className="form-navigator">Register</Link> </Text>  
          </form>
          </Box>
        </Box>
      </Box>
    </Flex>
  )
}

export default Login