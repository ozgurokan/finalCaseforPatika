import './App.css'
import Home from "./components/Home";
import Register from "./components/Register";
import Navbar from './components/Navbar';
import Login from "./components/Login";
import {Routes, Route} from "react-router-dom";



function App() {


  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login/>}/>

      </Routes>
    </div>
  )
}

export default App
