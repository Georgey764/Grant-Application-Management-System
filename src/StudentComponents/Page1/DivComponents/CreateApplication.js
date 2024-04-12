import React, { useState, useEffect } from "react";
import axios from "axios";

function CreateApplication() {
  const [studentApplication, setStudentApplication] = useState({
    firstName: "",
    lastName: "",
    gpa: "",
    cwid: "",
    classification: "",
    major: "",
    link: "",
    message: "",
    applicationId:""
  });
  const jwtCode =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXNhZCIsImlzcyI6Ikdlb3JnZSdzIEJhY2tlbmQiLCJpYXQiOjE3MTIyNTE2NTd9.TylyvptzvuTC8y-cLLpABIjlFtcTd0KDOA0gxShRXE8";

  function submitData(e) {
    e.preventDefault();
    const professorInfo = async () => {
      axios
        .post("http://localhost:8080/students/send-application", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${jwtCode}`,
          },
          body: studentApplication,
        })
        .then((response) => {
          console.log(response);
        });
    };
    professorInfo();
  }

  function handleChange(e) {
    const { name, value } = e.target;
    setStudentApplication({
      ...studentApplication,
      [name]: value,
    });
  }
  return (
    <div className="new_project m-5">
      <span className="fs-2">Emerging Scholar Application</span>
      <div className="mb-5"> </div>
      <form>
        <div className="row">
          <div className="col md-4 mb-3">
            <label htmlFor="firstName"> First Name </label>
            <br />
            <input
              name="firstName"
              id="firstName"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="lastName"> Last Name </label>
            <br />
            <input
              name="lastName"
              id="lastName"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="gpa"> GPA </label>
            <br />
            <input
              name="gpa"
              id="gpa"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-md-4 mb-3">
            <label htmlFor="classification"> Classification </label>
            <br />
            <input
              name="classification"
              id="classification"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="major"> Major </label>
            <br />
            <input
              name="major"
              id="major"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="cwid"> CWID </label>
            <br />
            <input
              name="cwid"
              id="cwid"
              type="text"
              className="form-control mt-3"
              onChange={handleChange}
            />
          </div>
        </div>
        <div>
          <label htmlFor="resume_link"> Link to your resume</label>
          <br />
          <input
            type="url"
            id="resume_link"
            name="link"
            className="form-control mt-3"
            onChange={handleChange}
          />
        </div>

        <div className="mt-3">
          <label htmlFor="message"> Message to Professor </label>
          <br />
          <textarea
            type="text"
            id="message"
            name="message"
            className="form-control mt-3"
            onChange={handleChange}
            rows={8}
          />
        </div>
        <div className="text-end">
          <button
            type="submit"
            onSubmit={(e) => submitData(e)}
            className="btn btn-success px-4 mt-3"
            id="submit_button"
          >
            Send Application
          </button>
        </div>
      </form>
    </div>
  );
}
export default CreateApplication;
