import React from 'react';
import {Flex,Box,Heading,Button,Text} from "@chakra-ui/react";
import {Link} from "react-router-dom";

function PageNotFound() {
  return (
    <Flex width={"full"} height={"90vh"} mt={"10%"} justifyContent={"center"}>
        <Box textAlign={"center"}>
        <Heading fontSize={"5rem"}>404</Heading>
            <Text mb={"1rem"}>The page could not be found</Text>
            <Link to={"/"}><Button colorScheme={"blue"}>Go to Home</Button></Link>
        </Box>
    </Flex>
  )
}

export default PageNotFound