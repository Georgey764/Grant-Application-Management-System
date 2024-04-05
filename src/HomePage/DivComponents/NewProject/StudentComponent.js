import React from "react";

function StudentComponent() {
  return (
    <div className="studentComponent">
      <div className="individual_component m-3 border p-3 border-black d-flex flex-column rounded">
        <div className="name_status d-flex justify-content-between">
          <div> Student Name</div>
          <div className="bg-success px-3 py-1 rounded"> Status </div>
        </div>
        <div className="application_id">Application Id: 1</div>
      </div>
    </div>
  );
}

export default StudentComponent;
