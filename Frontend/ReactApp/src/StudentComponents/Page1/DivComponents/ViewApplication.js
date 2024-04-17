import React, { useState, useEffect } from "react";
import axios from "axios";

const headers = {
  method: "GET",
  credentials: "include",
  headers: {
    "Content-Type": "application/json",
  },
};

function ViewApplication({ activeId }) {
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

  return (
    <div className="new_project m-5">
      <span className="fs-2">View Sent Emerging Scholar Application</span>
      <div className="mb-5"> </div>
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
            rows={6}
          />
        </div>
      </form>
    </div>
  );
}
export default ViewApplication;
