import React from "react";
import "./admin.css";
import logo from "./ulm_logo.png";

function EndSemester() {
  return (
    <div className="start_semester mt-2">
      <div id="login_box my-4">
        <div class="login_box_logo">
          <img src={logo} alt="ULM logo" class="ulm_logo" />
        </div>
      </div>
      <div class="container align-items-center justify-content-center text-center mt-5">
        <span className="fs-2">Stop taking application for this semester</span>
        {/* <div class="row align-items-center mt-5">
          <div class="col justify-content-center fs-4">
            Current Semester : Fall 2024
          </div>
        </div> */}
        <div className="stop_button m-5">
          <button
            type="button"
            className="btn btn-danger px-4"
            id="stop_button"
          >
            Reset Application Semester
          </button>
        </div>
      </div>
    </div>
  );
}

export default EndSemester;
