import React from 'react'
import { Flex,Box,Heading,FormControl,FormLabel,Input,Button,Alert, Toast } from '@chakra-ui/react'
import {useFormik} from "formik";
import {changePassValidations} from "../validation/validation";
import {useSelector} from "react-redux";
import {changePassword} from "../apiMethods"

function Settings() {

    const userId = useSelector((state) => state.auth.userData.userId);
    const formik = useFormik({
        initialValues:{
            currentPassword : "",
            newPassword: "",
            newPasswordConfirm: "",
        },
        validationSchema :changePassValidations ,
        onSubmit : async(values,bag) => {
            const postvalues = {
                currentPassword : values.currentPassword,
                newPassword : values.newPassword,
                userId
            }
            try{
                
                const response = await changePassword(postvalues);

                console.log(response);
            }catch(e){
               bag.setErrors({general:"Current password is incorrect"})
               console.log("olmadı")
            }
    
          }
      })


  return (
    <Flex width={"full"} padding={"10"} justifyContent={"center"} py={"10%"} >
        <Box alignItems={"center"} border={"1px"} borderRadius="5%" padding="20">
            <Heading>Change Password</Heading>
            <Box alignItems={"center"} justifyContent={'center'} mt="20%" width="full"  >
            <form onSubmit={formik.handleSubmit}>
            <FormControl>
              <FormLabel>Current Password</FormLabel>
              <Input id='currentpass' name='currentPassword' type="password" onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.currentPassword} isInvalid={formik.touched.currentPassword && formik.errors.currentPassword} />

              <FormLabel mt={4}>Password</FormLabel>
              <Input id="newpass" type="password" name='newPassword' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.newPassword}  isInvalid={formik.touched.newPassword && formik.errors.newPassword}/>

              <FormLabel mt={4}>Password Confirm</FormLabel>
              <Input id='newpassconfirm' type="password" name='newPasswordConfirm' onChange={formik.handleChange} onBlur={formik.handleBlur} value={formik.newPasswordConfirm} isInvalid={formik.touched.newPasswordConfirm && formik.errors.newPasswordConfirm}/>
            </FormControl>
            <Box my={4}>{formik.errors.general && (<Alert status='error'>{formik.errors.general}</Alert>)}</Box>
            <Button width={"full"} my={4} colorScheme={"blue"} type={"submit"}>Change Password</Button>
            <hr />
          </form>
            </Box>
                
        </Box>
        
    </Flex>
  )
}

export default Settings