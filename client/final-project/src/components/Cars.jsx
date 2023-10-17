import {useEffect} from 'react'
import { Flex,Box,Button,Heading} from '@chakra-ui/react'
import {useDispatch, useSelector} from "react-redux";
import {fetchCars} from "../redux/auth/thunks"
import TableComp from "./Table";
import { useNavigate,Outlet } from 'react-router-dom';
import NewCar from './NewCar';


function Cars() {

  const navigate = useNavigate();
  const userId = useSelector((state) => state.auth.userData.userId);
  const username = useSelector((state) => state.auth.userData.username);
  const {data,isLoading,error} = useSelector((state) => state.cars)

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchCars(userId));
    console.log(userId)
  },[])



  console.log(data);

  const headers = [
      {name :"Username",sortable : false},
      {name:"Brand",sortable:true},
      {name: "Model",sortable:true},
      {name:"Plate",sortable:false},
      {name: "Year",sortable :true},
      {name :"Options",sortable:false}
];


  return (
    <Flex width={"full"} padding={"5%"} flexDirection="column">
      <Flex justifyContent={"space-between"} alignItems="center">
      <Box> <Button colorScheme={"blue"} onClick={() => navigate("/profile/new-car")}>Add new Car</Button>  </Box>
      <Box fontWeight="1000" px={"20px"} py="5px" backgroundColor={"green.400"} border="1px solid gray" rounded={"lg"} >Vehicles {data && data.length}</Box>
      </Flex>
      <Box width={"full"} mt="1%" >
        {isLoading ? <Box>Loading</Box> : <TableComp username={username} head={headers} body={data.length != 0 ? data : null}/>}
      </Box>
    </Flex>
  )
}

export default Cars