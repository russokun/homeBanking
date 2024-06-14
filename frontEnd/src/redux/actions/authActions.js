import { createAction } from "@reduxjs/toolkit";
//nombre actions en plural pq son varias
export const login = createAction('LOGIN',(user /*aqui llega info del usuario*/)=>{
  
  const clearUser = {//aqui se limpia o transforma la info del usuario (no se hace logica)
    email: user.email,
    name: user.firstName + ' ' + user.lastName,
    token: user.token,
    expiresIn: new Date(Date.now() + 1000 * 60 * 60).toISOString(),
    loggedIn: true
  }
  
  return {
    payload: clearUser
  }
})

export const logout = createAction('LOGOUT')//no se necesita payload pq no se necesita info del usuario