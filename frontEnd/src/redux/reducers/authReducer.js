import { createReducer } from "@reduxjs/toolkit";
import { login } from "../actions/authActions";
import { logout } from "../actions/authActions";
//nombre reducer en singular pq es 1 solo
//por buena practica se almacena estado inicial
const initialState = {
  loggedIn: false,
  token:'',
  expiresIn:'',
  error:'',
  user:{
    name:'',
    email:'',
    password:''
  }
} 
//por buena practica parametro es la ref del estado y la accion/callback(este implementa la logica/construcion de casos)
const authReducer = createReducer(initialState, (builder) => {
  builder
  .addCase(login,(state,action)=>{
    //guarda el token en el estado de la aplicacion
    //false a true
    //modeo del draft, solo con redux toolkitstate.expiresIn = action.payload.expiresIn
    alert ('loged Sucessfully!')
    return{
      ...state,
      user:{
        email: action.payload.email,
        name : action.payload.name
      },
      token: action.payload.token,
      loggedIn: action.payload.loggedIn,
      expiresIn: action.payload.expiresIn
    }
  })
  .addCase(logout,(state,action)=>{
    //true a false
    alert ('See u later!')
    return initialState
  })
})

export default authReducer;