import React, { useState, useEffect } from "react";
import axios from "axios";

const fetchDetails = "http://localhost:8080/students/details";
const headers = {
  method: "GET",
  credentials: "include",
  headers: {
    "Content-Type": "application/json",
  },
};

function CreateApplication({ appInfo, setActiveApp }) {
  const [studentApplication, setStudentApplication] = useState({
    gpa: "",
    classification: "",
    resumeLink: "",
    message: "",
    applicationId: appInfo.createdApplicationId,
  });

  const [receivedDetails, setReceivedDetails] = useState({
    firstName: "",
    lastName: "",
    cwid: "",
    major: "",
  });

  useEffect(() => {
    fetch(fetchDetails, headers)
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        setReceivedDetails({
          firstName: res.firstName,
          lastName: res.lastName,
          cwid: res.cwid,
          major: res.major,
        });
      })
      .catch((e) => console.log(e));
  }, [studentApplication]);

  function submitData(e) {
    e.preventDefault();
    const professorInfo = async () => {
      axios
        .post(
          "http://localhost:8080/students/send-application",
          studentApplication,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          alert(response.data);
          setActiveApp({});
        })
        .catch((e) => {
          console.log(e);
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
      <form onSubmit={(e) => submitData(e)}>
        <div className="row">
          <div className="col md-4 mb-3">
            <label htmlFor="firstName"> First Name </label>
            <br />
            <input
              readOnly
              name="firstName"
              id="firstName"
              type="text"
              className="form-control mt-3"
              value={receivedDetails.firstName}
              onChange={handleChange}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="lastName"> Last Name </label>
            <br />
            <input
              readOnly
              name="lastName"
              id="lastName"
              type="text"
              className="form-control mt-3"
              value={receivedDetails.lastName}
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
              readOnly
              value={receivedDetails.major}
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
              readOnly
              value={receivedDetails.cwid}
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
            name="resumeLink"
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
            rows={6}
          />
        </div>
        <div className="text-end">
          <button
            type="submit"
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
