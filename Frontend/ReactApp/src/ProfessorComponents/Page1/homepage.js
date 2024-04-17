import React, { useState, useEffect } from "react";
import "./homepage.css";
import CreateProject from "../DivComponents/NewProject/CreateProjectProfessor";
import EmptyProject from "../DivComponents/NewProject/EmptyProjectProfessor";
import HaveProject from "../DivComponents/NewProject/HaveProjectProfessor";
import MyAccount from "../DivComponents/NewProject/MyAccountProfessor";
import StudentInfoProfessor from "../DivComponents/NewProject/StudentInfoProfessor";
import axios, { HttpStatusCode } from "axios";

const getProjectURL = "http://localhost:8080/faculty/project";
const getStudentsURL =
  "http://localhost:8080/faculty/received-applications?search-query=";
const logOutUrl = "http://localhost:8080/remove-authentication";
const logInPage = "http://localhost:3000/";

function HomePage() {
  const [studentData, setStudentData] = useState([]);
  const [project, setProject] = useState({
    project_name: "",
    description: "",
    application_id: "",
    statusMessage: "",
  });
  const [component, setComponent] = useState("ProjectDisplay");
  const [activeApplicationId, setActiveApplicationId] = useState(-1);
  const [query, setQuery] = useState("");

  useEffect(() => {
    const loadFaculty = async () => {
      axios
        .get(getProjectURL, {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          const { data } = response;
          const newData = {
            application_id: data.applicationId,
            description: data.description,
            project_name: data.name,
            statusMessage: data.statusMessage,
          };
          setProject(newData);
          if (data.statusMessage === "No applications were found.") {
            project.description = "";
            project.project_name = "";
          }
        })
        .catch((error) => {
          if (error === HttpStatusCode.NotFound) {
            project.project_name = "";
            project.description = "";
          }
        });
    };
    loadFaculty();
  }, []);

  useEffect(() => {
    const loadStudentData = async () => {
      axios
        .get(getStudentsURL + query, {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          setStudentData([...response.data]);
        })
        .catch((e) => console.log(e));
    };
    loadStudentData();
  }, [query]);

  function logOut() {
    fetch(logOutUrl, {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => {
        alert("Successfully Logged out");
        window.location.href = logInPage;
      })
      .catch((e) => console.log(e));
  }

  const handleEditClick = () => {
    setComponent("CreateProject");
  };
  const isCreateClicked = () => {
    setComponent("CreateProject");
  };
  function handleApplicantsClick(cur) {
    setComponent("ViewApplicant");
    setActiveApplicationId(cur);
  }
  function handleQueryChange(e) {
    setQuery(e.target.value);
  }

  return (
    // HomePage for the professor
    <div className="homepage" id="homepage">
      <div className="row" id="homepage-row">
        <div className="col-md-4 flex-column" id="col-1">
          {/* Search Box for the applicants */}
          <input
            type="text"
            className="form-control mt-5 text-center"
            value={query}
            onChange={(e) => handleQueryChange(e)}
            placeholder="Search for Applicants"
          />

          {/* Applicants and Sort Function */}
          <div className=" col d-flex justify-content-between mt-5 mb-3 text-white">
            <span className="text-white">Your Applicants</span>

            {/* Sorting */}
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="sorting"
              />
              <label className="form-check-label ml-2" htmlFor="sorting">
                Sort
              </label>
            </div>
          </div>

          <div className="container-fluid p-0" id="applicants_block">
            <ul className="list-group m-0 overflow-y-scroll">
              {studentData.map((cur) => {
                return (
                  <li
                    style={{ cursor: "pointer" }}
                    className="list-group-item d-flex justify-content-between"
                    onClick={(e) => {
                      handleApplicantsClick(cur.sentApplicationId);
                    }}
                    key={cur.sentApplicationId}
                  >
                    <p className="m-0">
                      {cur.firstName} {cur.lastName}
                    </p>
                    <p
                      className={`m-0 rounded ${
                        cur.decision === "IN - PROGRESS"
                          ? "bg-success ps-2 pe-2 pt-0 pb-0 text-light"
                          : ""
                      }`}
                    >
                      {cur.decision === "ACCEPTED"
                        ? "Accepted ✅"
                        : cur.decision === "DECLINED"
                        ? "Declined ❌"
                        : "Decide"}
                    </p>
                  </li>
                );
              })}
            </ul>
          </div>

          <div className="d-flex justify-content-around buttons gap-3 text-center">
            <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_project_button"
              onClick={() => {
                setComponent("ProjectDisplay");
              }}
            >
              My Project
            </button>
            <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_account_button"
              onClick={logOut}
            >
              Log Out
            </button>
            {/* <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_account_button"
              onClick={() => setComponent("MyAccount")}
            >
              My Account
            </button> */}
          </div>
        </div>

        <div className="col-md-8">
          {component === "MyAccount" && <MyAccount />}
          {component === "ProjectDisplay" && (
            <>
              {!(project.project_name && project.description) ? (
                <EmptyProject clickCreate={isCreateClicked} />
              ) : (
                <HaveProject project={project} onEditClick={handleEditClick} />
              )}
            </>
          )}
          {component === "CreateProject" && <CreateProject data={project} />}
          {component === "ViewApplicant" && (
            <StudentInfoProfessor activeApplicationId={activeApplicationId} />
          )}
        </div>
      </div>
    </div>
  );
}
export default HomePage;
