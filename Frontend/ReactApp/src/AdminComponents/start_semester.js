import React from "react";
import "./admin.css";
import logo from "./ulm_logo.png";

function Start_semester() {
  return (
    <div className="start_semester mt-2">
      <div id="login_box my-4">
        <div class="login_box_logo">
          <img src={logo} alt="ULM logo" class="ulm_logo" />
        </div>
      </div>
      <div class="container align-items-center justify-content-center text-center mt-5">
        <span className="fs-2">
          Lets Start the emerging scholar for this semester
        </span>
        <div class="row align-items-center mt-5">
          <div class="col justify-content-center fs-4">Which Semester ?</div>
        </div>
        <div className="d-flex align-items-center justify-content-center">
          <input
            type="text"
            className="form-control mt-5 text-center form-input"
            placeholder="Fall 2022"
          />
        </div>
        <div className="save_button m-5">
          <button
            type="button"
            className="btn btn-success px-4"
            id="save_button"
          >
            Start New Semester
          </button>
        </div>
      </div>
    </div>
  );
}

export default Start_semester;
