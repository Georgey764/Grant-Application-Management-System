import React from "react";
import "./HomePageStudent.css";

function HomePage() {
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
          
        </div>
      </div>
    </div>
  );
}
export default HomePage;
