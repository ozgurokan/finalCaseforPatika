import React from 'react'
import {NavLink} from "react-router-dom"

function Navbar() {
  return (
    <nav className="nav">
        <div className="nav-left">
            <NavLink to="/"> Home </NavLink>
        </div>
        <div className="nav-right">
            <NavLink to="/login"> Login </NavLink>
            <NavLink to="/register"> Register </NavLink>
        </div>
    </nav>
  )
}

export default Navbar