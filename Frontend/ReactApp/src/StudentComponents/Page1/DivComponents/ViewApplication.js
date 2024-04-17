import React, { useState, useEffect } from "react";
import axios from "axios";

const deleteURL = "http://localhost:8080/students/application/";

const headers = {
  method: "GET",
  credentials: "include",
  headers: {
    "Content-Type": "application/json",
  },
};

function ViewApplication({ activeId, appInfo }) {
  const [studentApplicationDetails, setStudentApplicationDetails] = useState({
    professorName: "",
    firstName: "",
    lastName: "",
    department: "",
    cwid: "",
    gpa: "",
    classification: "",
    resumeLink: "",
    message: "",
    decision: "",
    statusMessage: "",
  });

  useEffect(() => {
    fetch(
      "http://localhost:8080/students/application-details/" + activeId,
      headers
    )
      .then((res) => {
        return res.json();
      })
      .then((res) => {
        setStudentApplicationDetails({ ...res });
      })
      .catch((e) => console.log(e));
  }, [activeId]);

  function handleDelete() {
    fetch(deleteURL + activeId, {
      method: "DELETE",
      credentials: "include",
      headers: { "Content-Type": "text/plain" },
    })
      .then((res) => {
        return res.text();
      })
      .then((res) => {
        alert(res);
        window.location.reload();
      })
      .catch((e) => console.log(e));
  }

  return (
    <div className="new_project m-5">
      <span className="fs-2">View Application for {appInfo.professorName}</span>
      <div className="project-container bg-secondary-subtle p-0 rounded mt-2">
        <p className="mt-22">
          <span className="fw-bold">Project: </span>
          {appInfo.projectName}
        </p>
        <p className="mt-2 fw-bold">
          Description:{" "}
          <span className="fw-normal">{appInfo.projectDescription}</span>
        </p>
      </div>
      <div className="mb-2"> </div>
      <form>
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
              value={studentApplicationDetails.firstName || "N/A"}
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
              value={studentApplicationDetails.lastName || "N/A"}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="gpa"> GPA </label>
            <br />
            <input
              readOnly
              name="gpa"
              id="gpa"
              type="text"
              className="form-control mt-3"
              value={studentApplicationDetails.gpa || "N/A"}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-md-4 mb-3">
            <label htmlFor="classification"> Classification </label>
            <br />
            <input
              readOnly
              name="classification"
              id="classification"
              type="text"
              className="form-control mt-3"
              value={studentApplicationDetails.classification || "N/A"}
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="major"> Major </label>
            <br />
            <input
              readOnly
              name="major"
              id="major"
              type="text"
              value={studentApplicationDetails.department || "N/A"}
              className="form-control mt-3"
            />
          </div>
          <div className="col-md-4 mb-3">
            <label htmlFor="cwid"> CWID </label>
            <br />
            <input
              readOnly
              name="cwid"
              id="cwid"
              type="text"
              value={studentApplicationDetails.cwid || "N/A"}
              className="form-control mt-3"
            />
          </div>
        </div>
        <div>
          <label htmlFor="resume_link"> Link to your resume</label>
          <br />
          <input
            readOnly
            type="url"
            id="resume_link"
            name="resumeLink"
            className="form-control mt-3"
            value={studentApplicationDetails.resumeLink || "N/A"}
          />
        </div>

        <div className="mt-3">
          <label htmlFor="message"> Message to Professor </label>
          <br />
          <textarea
            readOnly
            type="text"
            id="message"
            name="message"
            className="form-control mt-3"
            value={studentApplicationDetails.message || "N/A"}
            rows={3}
          />
        </div>
      </form>
      <div className="d-flex justify-content-end mt-4">
        <button onClick={handleDelete}>Delete</button>
      </div>
    </div>
  );
}
export default ViewApplication;
