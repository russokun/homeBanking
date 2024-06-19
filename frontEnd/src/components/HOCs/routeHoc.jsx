import React from "react";
import { Route } from "react-router-dom";

const RouteHoc = (Component, route, key) => {
    return (
      <Route element={Component} path={route} key={key} />
    );
}
export default RouteHoc;