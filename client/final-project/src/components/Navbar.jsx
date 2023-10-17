import {useEffect} from 'react'
import {NavLink} from "react-router-dom"
import {useSelector,useDispatch} from "react-redux";
import {storeLogOut} from "../redux/auth/authSlice";
import { carStoreLogOut } from '../redux/cars/carSlice';


function Navbar() {
  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => {return state.auth.isLoggedIn})



  const notLoggedIn = <div className="nav-right">
  <NavLink to="/login"> Login </NavLink>
  <NavLink to="/register"> Register </NavLink>
</div>

  const loggedIn = <div className="nav-right">
  <NavLink to="/profile"> Profile </NavLink>
  <NavLink to="/" onClick={() => {
  
    dispatch(storeLogOut());
    dispatch(carStoreLogOut());

    }}> Logout </NavLink>
</div>
  return (
    <nav className="nav">
        <div className="nav-left">
            <NavLink to="/"> Home </NavLink>
        </div>
        {
          isLoggedIn === true ? loggedIn : notLoggedIn
        }
    </nav>
  )
}

export default Navbar