import React, { useState } from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LoginPage from "./Login Page/LoginPage";
import SignupPage from "./Signup Page/SignupPage";

const authorityUrl = "http://10.10.4.17:8080/authority";

const router = createBrowserRouter([
  {
    id: "root",
    path: "/",
    element: <LoginPage />,
    errorElement: <div>Error 404 Nothing Found</div>,
  },
  {
    path: "/signup",
    element: <SignupPage />,
    errorElement: <div>Error 404 Nothing Found</div>,
  },
  {
    path: "/App",
    element: <App />,
    errorElement: <div>Error 404 Nothing Found</div>,
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
