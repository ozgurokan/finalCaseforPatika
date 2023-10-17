import React from 'react';
import {Flex, Box, Heading} from "@chakra-ui/react";

function Home() {
  return (
      <Flex width={"full"} height={"90vh"} justifyContent="center" alignItems={"center"}>
        <Box><Heading>Welcome to Home Page</Heading></Box>
      </Flex>
    )
}

export default Home