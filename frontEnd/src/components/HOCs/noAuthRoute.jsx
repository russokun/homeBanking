import React from 'react';
import { useSelector } from "react-redux";
import { Route } from "react-router-dom";

function NoAuthRoute(route){
    const {loggedIn} = useSelector((store) => store.authReducer.loggedIn)

    return !loggedIn ? <Route key={route.key} path={route.path} element={route.element} /> : <Navigate to="/" />
}
export default NoAuthRoute;