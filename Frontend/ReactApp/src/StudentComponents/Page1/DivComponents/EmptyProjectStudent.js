import React from "react";

function EmptyProject({ clickCreate }) {
  return (
    <div className="new_project m-5">
      <span className="fs-2"> Your Applications</span>

      {/* Container for the empty portion */}
      <div className="container" style={{ display: "flex", height: "70vh" }}>
        <div
          className="fs-3 text-secondary justify-content-center"
          style={{ margin: "auto" }}
        >
          WOW Such Empty 😵‍💫 🫙
        </div>
      </div>
    </div>
  );
}

export default EmptyProject;
