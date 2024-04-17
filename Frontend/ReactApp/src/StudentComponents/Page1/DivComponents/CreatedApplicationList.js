const deleteURL = "http://localhost:8080/students/application/";

export default function CreatedApplicationList({
  sentApplications,
  setActiveApplication,
}) {
  function handleClick(e, val) {
    console.log();
    setActiveApplication(val);
  }

  function handleDelete(selectedId) {
    fetch(deleteURL + selectedId, {
      method: "DELETE",
      credentials: "include",
      headers: { "Content-Type": "text/plain" },
    })
      .then((res) => {
        return res.text();
      })
      .then((res) => {
        alert(res);
        window.location.reload();
      })
      .catch((e) => console.log(e));
  }

  return (
    <div className="pt-5 pb-5">
      <p className="ms-2 fs-1 mb-5">My Applications List</p>
      <ul className="list-group p-0">
        {sentApplications.map((cur) => (
          <li
            className="list-group-item sent-app-list"
            key={cur.sentApplicationId}
          >
            <SentApplicationList
              props={{
                cur: cur,
                profName: cur.professorName,
                department: cur.department,
                status: cur.status,
                decision: cur.decision,
                sentApplicationId: cur.sentApplicationId,
                projectName: cur.projectName,
                handleClick: handleClick,
                handleDelete: handleDelete,
              }}
            />
          </li>
        ))}
      </ul>
    </div>
  );
}

function SentApplicationList({ props }) {
  return (
    <div className="p-2  d-flex flex-row justify-content-between align-items-center">
      <p className="m-0 fs-6">{props.profName}</p>
      <div className="d-flex justify-content-between">
        <p className="mb-0 me-4">
          {props.decision === "ACCEPTED"
            ? "Accepted ✅"
            : props.decision === "DECLINED"
            ? "Declined ❌"
            : "In - Progress ⏳"}
        </p>
        <button
          className="mb-0 mt-0 me-4"
          onClick={(e) => props.handleDelete(props.sentApplicationId)}
        >
          Delete
        </button>
        <button
          onClick={(e) => props.handleClick(e, props.cur)}
          className="mb-0 mt-0"
        >
          View
        </button>
      </div>
    </div>
  );
}
