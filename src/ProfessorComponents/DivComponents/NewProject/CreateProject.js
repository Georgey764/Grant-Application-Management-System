import React, { useState } from "react";
import axios from "axios";

function CreateProject(props) {
  const initialFormData = {
    project_name: props.data ? props.data.project_name || "" : "",
    project_description: props.data ? props.data.description || "" : "",
  };

  const [formData, setFormData] = useState(initialFormData);

  const jwtToken =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaSIsImlzcyI6Ikdlb3JnZSdzIEJhY2tlbmQiLCJpYXQiOjE3MTE5MDI5NjJ9.JFq44j_rxV-pJ5EHs3IizCRwv7MH7DuGLQmCBBw4j64";

  const url = "http://localhost:8080/faculty/project";
  const config = {
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${jwtToken}`,
    },
  };

  const requestBody = {
    projectName: formData.project_name,
    projectDescription: formData.project_description,
  };
  function handleSubmit(event) {
    event.preventDefault();
    const method = props.data.project_name ? "put" : "post";

    axios[method](url, requestBody, config)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
      .catch((error) => console.log(error));
  }

  function handleDelete() {
    // Reset form data to initial values
    setFormData(initialFormData);
    axios
      .delete(url, config)
      .then((response) => {
        console.log(response);
        window.location.reload();
      })
      .catch((error) => console.log(error));
  }

  return (
    <div className="new_project m-5">
      <span className="fs-2"> Create Your Project </span>

      {/* Form for creating the project */}
      <form onSubmit={handleSubmit} className="mt-5 fs-4">
        {/* Project Title */}
        <label>
          Project Name
          <input
            type="text"
            name="project_name"
            className="form-control mt-3 fs-5"
            value={formData.project_name}
            onChange={(e) =>
              setFormData({ ...formData, project_name: e.target.value })
            }
          />
        </label>
        <br />

        {/* Project Description */}
        <label className=" mt-5 fs-4">
          Project Description <br />
          <textarea
            name="project_description"
            className="form-control mt-3 fs-5"
            rows={10}
            cols={70}
            value={formData.project_description}
            onChange={(e) =>
              setFormData({ ...formData, project_description: e.target.value })
            }
          />
        </label>
        <br />

        {/* Action Button to save and delete the project */}
        <div className="action_buttons mt-5 d-flex justify-content-end">
          <div className="delete_button m-4">
            <button
              type="button"
              className="btn btn-danger px-4"
              id="delete_button"
              onClick={handleDelete}
            >
              Delete
            </button>
          </div>
          <div className="save_button m-4">
            <button
              type="submit"
              className="btn btn-success px-4"
              id="save_button"
            >
              Save
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default CreateProject;
