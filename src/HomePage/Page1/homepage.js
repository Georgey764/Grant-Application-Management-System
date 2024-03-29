import React from "react";
import "./homepage.css";

function HomePage() {
  return (
    <div className="homepage" id="homepage">
      <div className="row">

        <div className="col-md-3 bg-danger flex-column">
          <input type = "text" className = "form-control mt-5 text-center" placeholder="Search for Applicants"/>
          
          <div>
            
            <span> Applicants </span>
          </div>
          
        </div>



        <div className="col-md-9 bg-warning"></div>
      </div>
    </div>
  );
}

export default HomePage;
