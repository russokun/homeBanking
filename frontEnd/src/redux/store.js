import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./reducers/authReducer";
export const store = configureStore({
  reducer: {
    //Aqui van los reductores
    authReducer
  },
});
export default store;