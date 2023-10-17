import React from 'react'
import { Flex,Box,Button } from '@chakra-ui/react'
import {NavLink, Outlet} from "react-router-dom"

function Profile() {
  return (
    <Flex width={"full"} h="90vh" >
      <Box width={"12%"} borderRight="2px" backgroundColor={"blackAlpha.300"}>
          <Flex direction={"column"} alignItems="center" justifyContent={"center"} mt="5" gap={"5"}>
            
            <Button width={"50%"} colorScheme={"blue"}><NavLink to={"settings"}>Settings</NavLink></Button>
            <Button width={"50%"} colorScheme={"blue"}><NavLink to={"cars"}>Cars</NavLink></Button>
          </Flex>
      </Box>
      <Box width={"full"}>
        <Outlet></Outlet>
      </Box>
    </Flex>
  )
}

export default Profile