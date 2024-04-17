import React, { useState, useEffect } from "react";
import "./homepage.css";
import CreateProject from "../DivComponents/NewProject/CreateProjectProfessor";
import EmptyProject from "../DivComponents/NewProject/EmptyProjectProfessor";
import HaveProject from "../DivComponents/NewProject/HaveProjectProfessor";
import StudentInfo from "../DivComponents/NewProject/StudentInfoProfessor";
import MyAccount from "../DivComponents/NewProject/MyAccountProfessor";
import StudentComponent from "../DivComponents/NewProject/StudentComponent";
import axios, { HttpStatusCode } from "axios";

function HomePage() {
  const [studentData, setStudentData] = useState(null);
  const [project, setProject] = useState({
    project_name: "",
    description: "",
    application_id: "",
    statusMessage: "",
  });
  const query = "g";
  const [component, setComponent] = useState("ProjectDisplay");

  useEffect(() => {
    const loadFaculty = async () => {
      axios
        .get("http://localhost:8080/faculty/project", {
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
    const loadStudentData = async () => {
      axios
        .get(
          "http://localhost:8080/faculty/received-applications?search-query=" +
            query,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          console.log(response);
        });
    };
    loadStudentData();
  }, [project]);

  const handleEditClick = () => {
    setComponent("CreateProject"); // Update component state to display CreateProject
    console.log("edited");
  };
  const isCreateClicked = () => {
    setComponent("CreateProject");
  };

  return (
    // HomePage for the professor
    <div className="homepage" id="homepage">
      <div className="row" id="homepage-row">
        <div className="col-md-4 flex-column" id="col-1">
          {/* Search Box for the applicants */}
          <input
            type="text"
            className="form-control mt-5 text-center"
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

          <div className="container-fluid" id="applicants_block">
            <div className="container-fluid">
              <div className="col">
                <StudentComponent />
              </div>
            </div>
          </div>

          <div className="buttons mt-5 text-center">
            <button
              type="button"
              className="btn btn-secondary p-3"
              id="my_project_button"
              onClick={() => setComponent("ProjectDisplay")}
            >
              My Project
            </button>
            <br />
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
        </div>
      </div>
    </div>
  );
}
export default HomePage;
