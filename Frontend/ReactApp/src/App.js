import { useEffect, useState } from "react";
import "./App.css";
import MergeComponents from "./MergeComponents";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  const [authority, setAuthority] = useState();

  useEffect(function () {
    setAuthority(localStorage.getItem("authority"));
  }, []);

  return (
    <div className="App">
      <MergeComponents authority={authority} />
    </div>
  );
}

export default App;
