import "./App.css";
import React from "react";
import StartSemester from "./Components/start_semester.js";
import EndSemester from "./Components/end_semester.js";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="App">
      <EndSemester/>
    </div>
  );
}

export default App;
