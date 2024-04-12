import { React, useEffect } from "react";
import "./HomePageStudent.css";
import EmptyProjectStudent from "./DivComponents/EmptyProjectStudent";
import CreateApplication from "./DivComponents/CreateApplication";
import axios, { HttpStatusCode } from "axios";

const query = "";
function HomePage() {
  const jwtCode =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnZXNhZCIsImlzcyI6Ikdlb3JnZSdzIEJhY2tlbmQiLCJpYXQiOjE3MTIyNTE2NTd9.TylyvptzvuTC8y-cLLpABIjlFtcTd0KDOA0gxShRXE8";

  useEffect(() => {
    const professorInfo = async () => {
      axios
        .get(
          "http://localhost:8080/students/created-applications-list?search-query=" +
            query,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${jwtCode}`,
            },
          }
        )
        .then((response) => {
          console.log(response);
        });
    };
    professorInfo();
  }, []);
  return (
    // HomePage for the professor
    <div className="homepage" id="homepage_student">
      <div className="row" id="homepage-row">
        <div className="col-md-4 flex-column" id="col-1">
          {/* Search Box for the professor */}
          <input
            type="text"
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

          <div className="container-fluid" id="applicants_block">
            <div className="container-fluid">
              <div className="col"></div>
            </div>
          </div>

          <div className="buttons mt-5 text-center">
            <button
              type="button"
              className="btn btn-secondary mt-5 p-3 mb-3"
              id="my_account_button"
            >
              My Applications
            </button>
          </div>
        </div>

        <div className="col-md-8">
          <CreateApplication />
        </div>
      </div>
    </div>
  );
}
export default HomePage;
