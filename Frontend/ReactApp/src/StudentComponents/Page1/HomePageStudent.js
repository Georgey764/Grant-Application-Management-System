import { React, useEffect, useState } from "react";
import "./HomePageStudent.css";
import EmptyProjectStudent from "./DivComponents/EmptyProjectStudent";
import CreateApplication from "./DivComponents/CreateApplication";
import axios, { HttpStatusCode } from "axios";
import EmptyProject from "./DivComponents/EmptyProjectStudent";
import CreatedApplicationList from "./DivComponents/CreatedApplicationList";
import ViewApplication from "./DivComponents/ViewApplication";

const query = "";
const getProfessorsListLink =
  "http://localhost:8080/students/created-applications-list?search-query=" +
  query;
const getSentApplicationLink =
  "http://localhost:8080/students/sent-applications-list";
const logOutUrl = "http://localhost:8080/remove-authentication";
const logInPage = "http://localhost:3000/";

function HomePage() {
  const [sentApplications, setSentApplications] = useState([]);
  const [createdApplications, setCreadtedApplications] = useState([]);
  const [activeApplication, setActiveApplication] = useState({});
  const [query, setQuery] = useState("");

  useEffect(() => {
    const getSentApplication = async () =>
      axios
        .get(getSentApplicationLink, {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          setSentApplications([...res.data]);
        })
        .catch((e) => console.log(e));

    getSentApplication();
  }, []);

  useEffect(() => {
    const professorInfo = async () => {
      axios
        .get(getProfessorsListLink + query, {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: true,
        })
        .then((response) => {
          setCreadtedApplications([...response.data]);
        })
        .catch((e) => console.log(e));
    };
    professorInfo();
  }, [query]);

  function onClickToSetActiveApp(e, val) {
    setActiveApplication(val);
  }

  function onClickMyApplication() {
    setActiveApplication({});
  }

  const setActiveApp = (val) => {
    setActiveApplication(val);
  };

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

  function handleQueryChange(e) {
    setQuery(e.target.value);
  }

  return (
    // HomePage for the professor
    <div className="homepage" id="homepage_student">
      <div className="row" id="homepage-row">
        <div className="col-md-4 flex-column" id="col-1">
          {/* Search Box for the professor */}
          <input
            type="text"
            value={query}
            onChange={(e) => handleQueryChange(e)}
            className="form-control mt-5 text-center"
            placeholder="Search for Professors"
          />

          {/* Applicants and Sort Function */}
          <div className=" col d-flex justify-content-between mt-5 mb-3 text-white">
            <span className="text-white">Your Professors</span>

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
            <div className="container-fluid p-0">
              <ul className="list-group m-0 overflow-y-scroll">
                {createdApplications.map((cur) => {
                  return (
                    <li
                      onClick={(e) => onClickToSetActiveApp(e, cur)}
                      style={{ cursor: "pointer" }}
                      className="list-group-item d-flex justify-content-between"
                      key={
                        cur.sentApplicationId === -1
                          ? -1 + cur.professorName + cur.createdApplicationId
                          : cur.sentApplicationId
                      }
                    >
                      <p className="m-0">{cur.professorName}</p>
                      <p
                        className={`m-0 rounded ${
                          cur.status === "NOT SUBMITTED"
                            ? "bg-success ps-2 pe-2 pt-0 pb-0 text-light"
                            : ""
                        }`}
                      >
                        {cur.decision === "ACCEPTED"
                          ? "Accepted ✅"
                          : cur.decision === "DECLINED"
                          ? "Declined ❌"
                          : cur.decision === "IN - PROGRESS"
                          ? "Submitted 😊"
                          : "Submit"}
                      </p>
                    </li>
                  );
                })}
              </ul>
            </div>
          </div>

          <div className="d-flex justify-content-around buttons mt-0 text-center">
            <button
              type="button"
              className="btn btn-secondary mt-5 p-2 mb-3"
              id="my_account_button"
              onClick={onClickMyApplication}
            >
              My Applications
            </button>
            <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_account_button"
              onClick={logOut}
            >
              Log Out
            </button>
          </div>
        </div>

        <div className="col-md-8">
          {Object.keys(activeApplication).length !== 0 ? (
            activeApplication.sentApplicationId === -1 ? (
              <CreateApplication
                appInfo={activeApplication}
                setActiveApp={setActiveApplication}
              />
            ) : (
              <ViewApplication
                appInfo={activeApplication}
                activeId={activeApplication.sentApplicationId}
              />
            )
          ) : sentApplications.length !== 0 ? (
            <CreatedApplicationList
              sentApplications={sentApplications}
              setActiveApplication={setActiveApp}
            />
          ) : (
            <EmptyProjectStudent />
          )}
        </div>
      </div>
    </div>
  );
}
export default HomePage;
