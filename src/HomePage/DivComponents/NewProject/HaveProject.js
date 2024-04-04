import React from "react";

function HaveProject(props) {
  return (
    <div className="HaveProject m-5">
      <span className="fs-2"> Your Project Fall 2024 </span>
      <div className="mt-5 fs-4">
        Project Name:
        <br />
        <div className="project_name fs-5 mt-3">
          {props.project.project_name}
        </div>
        <br />
        Project Description: <br />
        <div
          className="project_description fs-5 mt-3"
          style={{
            height: "400px",
            backgroundColor: "#f0f0f0",
            overflowY: "auto",
            padding: 10,
          }}
        >
          {props.project.description}
        </div>
        <div className="mt-5 d-flex justify-content-end">
          <button
            type="button"
            className="btn btn-success px-4"
            id="edit_button"
            onClick={props.onEditClick}
          >
            Edit
          </button>
        </div>
      </div>
    </div>
  );
}

export default HaveProject;
