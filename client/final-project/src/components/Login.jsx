import React from 'react'

import {Link} from "react-router-dom"

function Login() {
  return (
    <div className='login'>
      <div className="login-card">
        <div className='card-header'>
          <h2>PatiCars</h2>
          <p>Please log in to continue</p>
        </div>
        <div className='card-form'>
          <p>Username</p>
          <input type="text" />
          <p>Password</p>
          <input type="password" />
          <div className='card-error'>

          </div>
          <div className='card-button'>
            <button > Log In</button>
          </div>
          
          <div className="card-message">
            <hr /> 
            No account yet? <Link to="/register" >Sign up</Link></div>
        </div>
      </div>
    </div>
  )
}

export default Login