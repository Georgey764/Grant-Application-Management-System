import React, { useEffect, useState } from "react";

const getSentApplicationDetailsURL =
  "http://localhost:8080/faculty/application?sent-application-id=";
const decisionURL =
  "http://localhost:8080/faculty/decision?sent-application-id=";

function StudentInfoProfessor({ activeApplicationId }) {
  const [appData, setAppData] = useState({});

  useEffect(() => {
    fetch(getSentApplicationDetailsURL + activeApplicationId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => setAppData(data))
      .catch((e) => console.log(e));
  }, []);

  function handleDecision(decision) {
    fetch(decisionURL + activeApplicationId + "&decision=" + decision, {
      method: "PUT",
      credentials: "include",
      headers: {
        "Content-Type": "text/plain",
      },
    })
      .then((res) => res.text())
      .then((res) => {
        alert(res);
        window.location.reload();
      })
      .catch((e) => console.log(e));
  }

  return (
    <div className="Student_Info m-5">
      <span className="fs-2"> Student Name </span>
      <div className="container my-4">
        <div className="row">
          <TextComponent title="First Name" answer={appData.firstName} />
          <TextComponent title="Last Name" answer={appData.lastName} />
          <TextComponent title="GPA " answer={appData.gpa || "N/A"} />
        </div>
        <div className="row">
          <TextComponent title="Major" answer={appData.department} />
          <TextComponent title="CWID" answer={appData.cwid} />
          <TextComponent
            title="Classification"
            answer={appData.classification || "N/A"}
          />
        </div>
      </div>

      <span className="fs-4">Message from Student </span>
      <div
        className="project_description fs-5 mt-3"
        style={{
          height: "100px",
          backgroundColor: "#f0f0f0",
          overflowY: "auto",
        }}
      >
        {appData.message || "N/A"}
      </div>

      {/* Button for the accepting or rejecting the application for the faculty */}
      <div className="resume_decision justify-content-start">
        <div className="resume_link mt-4">
          Link to Resume :
          <a href="............">{appData.resumeLink || "N/A"}</a>
        </div>
        <div className="decision_buttons d-flex justify-content-end mt-3">
          {appData.decision !== "IN - PROGRESS" ? (
            <div className="reject_button mx-4">
              <button
                onClick={(e) => handleDecision("IN - PROGRESS")}
                type="button"
                className="btn btn-danger px-4"
                id="reject_button"
              >
                Withdraw Decision
              </button>
            </div>
          ) : (
            <>
              <div className="reject_button mx-4">
                <button
                  onClick={(e) => handleDecision("DECLINED")}
                  type="button"
                  className="btn btn-danger px-4"
                  id="reject_button"
                >
                  Reject
                </button>
              </div>
              <div className="accept_button mx-4">
                <button
                  onClick={(e) => handleDecision("ACCEPTED")}
                  type="button"
                  className="btn btn-success px-4"
                  id="accept_button"
                >
                  Accept
                </button>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
}

function TextComponent(props) {
  return (
    <div className="col-md-4 text-center fs-4 mb-5">
      {props.title}
      <br />
      <div className="project_name fs-5 mt-3">{props.answer}</div>
    </div>
  );
}

export default StudentInfoProfessor;
