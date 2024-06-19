import React from "react";
import { useSelector } from "react-redux";
import { Navigate, Route } from "react-router-dom";

function AuthRoute(route) {
  const loggedIn = useSelector((store) => store.authReducer.loggedIn);

  return (
    <Route
      key={route.key}
      path={route.path}
      element={loggedIn ? route.element : <Navigate to="/" replace />}
    />
  );
}

export default AuthRoute;