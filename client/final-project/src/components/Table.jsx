import {useState} from "react";
import {TableContainer,Table,Thead,Tbody,Th,Tr,Td,Button,Box, Heading} from "@chakra-ui/react";
import { deleteCar } from "../apiMethods";
import { useNavigate } from "react-router-dom";

export default function TableComp({head, body,username}) {
    const navigate = useNavigate();
    const handleDelete = async(carId) => {
        if(window.confirm("Are you sure to delete this Car?")){
           const response = await deleteCar(carId);
           console.log(response);
           window.location.reload(false)
        }
    }

    if(body == null){
        return (
            <Box width={"full"} textAlign={"center"}>  <Heading>There are no vehicles to list.</Heading> </Box>
        )
    }


    return (
        <TableContainer border={"1px"}>
            <Table variant={"simple"}>
                <Thead backgroundColor={"gray.300"} color={"black"} >
                    <Tr >
                    {head.map((h,key) => {
                        return <Th key={key} padding={"1.5%"} fontWeight={"800"} borderInlineEnd={"1px"}>{h.name}</Th>
                    })}
                    </Tr>
                </Thead>
                <Tbody>
                    {body.map((d,key) => {
                        return (
                            <Tr key={key} _hover={{
                                backgroundColor: "gray.100",
                                color: "blue.500",
                              }} fontWeight={"500"}>
                                <Td>{username}</Td>
                                <Td>{d.brand.toUpperCase()}</Td>
                                <Td>{d.model.toUpperCase()}</Td>
                                <Td>{d.car_plate.toUpperCase()}</Td>
                                <Td>{d.year}</Td>
                                <Td><><Button onClick={() => {handleDelete(d.id)}} colorScheme={"red"}>Delete</Button> <Button colorScheme={"blue"}>Edit</Button></></Td>
                            </Tr>
                        )
                    })}
                </Tbody>
            </Table>
        </TableContainer>
    )

}