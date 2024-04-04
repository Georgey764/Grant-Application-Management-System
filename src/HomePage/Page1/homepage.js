import React, { useState, useEffect } from "react";
import "./homepage.css";
import CreateProject from "../DivComponents/NewProject/CreateProject";
import EmptyProject from "../DivComponents/NewProject/EmptyProject";
import HaveProject from "../DivComponents/NewProject/HaveProject";
import StudentInfo from "../DivComponents/NewProject/StudentInfo";
import MyAccount from "../DivComponents/NewProject/MyAccount";
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
  const [component, setComponent] = useState("ProjectDisplay");

  const jwtCode =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaSIsImlzcyI6Ikdlb3JnZSdzIEJhY2tlbmQiLCJpYXQiOjE3MTE5MDI5NjJ9.JFq44j_rxV-pJ5EHs3IizCRwv7MH7DuGLQmCBBw4j64";

  useEffect(() => {
    const loadResult = async () => {
      axios
        .get("http://localhost:8080/faculty/project", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${jwtCode}`,
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
    loadResult();
  }, []);

  const handleEditClick = () => {
    setComponent("CreateProject"); // Update component state to display CreateProject
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
            <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_account_button"
              onClick={() => setComponent("MyAccount")}
            >
              My Account
            </button>
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
