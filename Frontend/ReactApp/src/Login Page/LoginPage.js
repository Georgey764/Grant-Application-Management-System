import React, { useState } from "react";
import "./login.css";
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const logInUrl = "http://localhost:8080/authenticate";
  const navigate = useNavigate();

  // if (urlParams.has("error")) {
  //   document
  //     .querySelector("#login_form_box")
  //     .insertAdjacentHTML(
  //       "afterbegin",
  //       `<div id='error_div'>${urlParams.get("error")}</div>`
  //     );
  //   setTimeout(function () {
  //     document.querySelector("#error_div").remove();
  //   }, 3000);
  // }

  function handleSubmit(e) {
    e.preventDefault();
    fetch(logInUrl, {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password,
      }),
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        if (data.message.startsWith("SUCCESS")) {
          localStorage.setItem("authority", data.authority);
          navigate("/app");
        } else {
          alert(data.message);
        }
      })
      .catch((e) => console.log(e));
  }

  return (
    <div className="div_login">
      <div id="login_box">
        <div className="login_box_logo">
          <img
            src={process.env.PUBLIC_URL + "/images/ulm_logo.png"}
            alt="ULM logo"
            className="ulm_logo"
          />
        </div>
        <div className="login_form">
          <form action="" id="login_form_box" onSubmit={(e) => handleSubmit(e)}>
            <label htmlFor="warhawks_email"> Warhawks Email</label>
            <br />
            <input
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              type="email"
              id="warhawks_email"
              name="w_email"
              value={email}
            />

            <label htmlFor="password" className="password_label">
              {" "}
              Password
            </label>
            <br />
            <input
              onChange={(e) => {
                setPassword(e.target.value);
              }}
              type="password"
              id="warhawks_password"
              name="w_password"
            />

            <button type="submit" id="submit_form">
              {" "}
              Continue{" "}
            </button>
          </form>
        </div>
        <div className="form_sign">
          <div className="form_sign_text">
            {" "}
            <span id="forgot_password">Forgot Password </span>
          </div>
          <div id="new_account">
            Need a new account ?
            <a href="/signup">
              <span id="signup_ref"> Sign Up</span>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
