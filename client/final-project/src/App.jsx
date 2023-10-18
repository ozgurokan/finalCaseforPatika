import './App.css'
import Home from "./components/Home";
import Register from "./components/Register";
import Navbar from './components/Navbar';
import Login from "./components/Login";
import {Routes, Route} from "react-router-dom";
import Profile from './components/Profile';
import Settings from "./components/Settings";
import Cars from './components/Cars';
import NewCar from './components/NewCar';
import UpdateCar from './components/UpdateCar';


function App() {


  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/profile" element={<Profile/>}>
          <Route path="/profile/settings" element={<Settings/>} ></Route>
          <Route path='/profile/cars' element={<Cars/>}></Route>
          <Route path='/profile/new-car' element={<NewCar/>}></Route>
          <Route path='/profile/update-car/:id/:userIdParam' element={<UpdateCar/>}></Route>

        </Route>


      </Routes>
    </div>
  )
}

export default App
