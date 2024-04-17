import React from "react";
import "./signup.css";
import { useEffect, useState } from "react";

function SignupPage() {
  const [formObj, setFormObj] = useState({
    firstName: "",
    lastName: "",
    password: "",
    department: "",
    email: "",
    cwid: "",
    authorityId: 1,
  });

  function handleChange(e) {
    console.log(formObj);
    setFormObj({ ...formObj, [e.target.name]: e.target.value });
  }

  function handleClick(e) {
    console.log(formObj);
    setFormObj({ ...formObj, authorityId: formObj.authorityId === 1 ? 2 : 1 });
  }

  function handleSubmit(e) {
    e.preventDefault();
    fetch("http://localhost:8080/sign-up", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formObj),
    })
      .then((res) => {
        console.log(res);
        return res.json();
      })
      .then((res) => {
        if (alert.message != "Successfully made an account") {
          alert(res.message);
        } else {
          window.location.href = "http:/localhost:3000/";
        }
      })
      .catch((e) => console.log(e));
  }

  useEffect(function () {}, []);
  return (
    <div className="div_signup">
      <div id="signup_box">
        <div className="signup_box_logo">
          <img
            src={process.env.PUBLIC_URL + "/images/ulm_logo.png"}
            alt="ULM logo"
            className="ulm_logo"
          />
        </div>

        <div className="signup_form">
          <form
            onSubmit={(e) => handleSubmit(e)}
            action=""
            id="signup_form_box"
          >
            <div className="form_layout">
              <div className="column_1">
                <label htmlFor="first_name"> First Name</label>
                <input
                  required
                  type="text"
                  id="w_first_name"
                  name="firstName"
                  value={formObj.firstName}
                  onChange={(e) => handleChange(e)}
                />

                <label htmlFor="last_name"> Last Name </label>
                <input
                  required
                  type="text"
                  id="w_last_name"
                  name="lastName"
                  value={formObj.lastName}
                  onChange={(e) => handleChange(e)}
                />

                <label htmlFor="major"> Your Department/ Major </label>
                <input
                  required
                  type="text"
                  id="w_major"
                  name="department"
                  value={formObj.department}
                  onChange={(e) => handleChange(e)}
                />
              </div>

              <div className="column_2">
                <label htmlFor="w_email"> Warhawks Email</label>
                <input
                  required
                  type="email"
                  id="w_email"
                  name="email"
                  value={formObj.email}
                  onChange={(e) => handleChange(e)}
                />

                <label htmlFor="cwid"> CWID </label>
                <input
                  required
                  type="number"
                  id="w_cwid"
                  name="cwid"
                  value={formObj.cwid}
                  onChange={(e) => handleChange(e)}
                />

                <div className="faculty_checkbox">
                  <input
                    onClick={(e) => handleClick(e)}
                    type="checkbox"
                    id="agree"
                    name="agree"
                  />
                  <label htmlFor="agree" onClick={(e) => handleClick(e)}>
                    Check this if you are a faculty
                  </label>
                </div>
              </div>

              <div className="column_3">
                <label htmlFor="w_password"> Password</label>
                <input
                  required
                  type="password"
                  id="w_password"
                  name="password"
                  value={formObj.password}
                  onChange={(e) => handleChange(e)}
                />

                <label htmlFor="w_c_password"> Confirm Password </label>
                <input
                  required
                  type="password"
                  id="w_c_password"
                  name="signup_password"
                />

                <button type="submit" id="submit_form">
                  {" "}
                  Continue{" "}
                </button>

                <div className="form_login">
                  <span className="need_account_text">
                    Already have an account?
                  </span>
                  <a href="/">
                    <div id="login_ref">Log In</div>
                  </a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SignupPage;
