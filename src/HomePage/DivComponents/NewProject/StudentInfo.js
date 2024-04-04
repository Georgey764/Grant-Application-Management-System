import React from 'react'

function StudentInfo() {
  return (
    <div className='Student_Info m-5'>
      <span className="fs-2"> Student Name </span>
        <div className="container my-4">
            <div className="row">
                <TextComponent title = "First Name" answer = "Abhishek"/>
                <TextComponent title = "Last Name" answer = "Amgain"/>
                <TextComponent title = "GPA " answer = "4.0"/>
              </div>
              <div className="row">
                <TextComponent title = "Major" answer = "Computer Science"/>
                <TextComponent title = "CWID" answer = "30155679"/>
                <TextComponent title = "Classification" answer = "Junior"/>
              </div>
        </div>

        <span className='fs-4'>Message from Student </span>
        <div
          className="project_description fs-5 mt-3"
          style={{
            height: "200px",
            backgroundColor: "#f0f0f0",
            overflowY: "auto",
          }}
        >
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate
          amet dolor, doloremque molestias a earum adipisci, sit nostrum,
          aspernatur fuga quasi! Quidem necessitatibus aspernatur eius
          assumenda! Eaque maxime aliquid soluta! Consectetur iure a debitis
          natus eaque molestiae officiis quae, quisquam obcaecati illum
          temporibus nobis ad numquam praesentium nam voluptates eveniet
          asperiores! Cum a dolorem accusamus fugiat nemo. Reiciendis, quo
          quaerat. Repudiandae architecto incidunt sequi odit, tenetur aperiam!
          Odit id error ducimus corporis itaque saepe sint molestiae aspernatur,
          fugiat natus quasi sed, vero fuga nostrum nulla, accusamus nam labore.
          Eaque, incidunt. Nam quos doloremque eligendi quae nostrum accusamus
          ab facilis itaque explicabo. Nemo eos perspiciatis a tempore illum
          corrupti iusto vitae beatae optio sapiente saepe dignissimos aperiam
          minima, nisi voluptates fugit. Consectetur aliquid, fugiat obcaecati
          doloribus sunt veniam vel optio nihil similique ullam necessitatibus
          eaque, a dolorum repellat tempora quia alias exercitationem! Cumque
          tempora veniam saepe quis repudiandae laborum quos repellat.
        </div>

        {/* Button for the accepting or rejecting the application for the faculty */}
        <div className="resume_decision justify-content-start">
          <div className="resume_link mt-4">
              Link to Resume : 
              <a href = "............"> https://www.google.com/docs/about/resume </a>
            </div>
          <div className="decision_buttons d-flex justify-content-end mt-3">
            <div className="reject_button mx-4">
              <button type="button" className="btn btn-danger px-4" id = "reject_button">
                Reject
              </button>
            </div>
            <div className="accept_button mx-4">
              <button type="button" className="btn btn-success px-4" id = "accept_button">
                Accept
              </button>
            </div>
          </div>
        </div>
    </div>
  )
}

function TextComponent(props){
  return(
          <div className="col-md-4 text-center fs-4 mb-5">
                {props.title}<br/>
                <div className="project_name fs-5 mt-3">{props.answer}</div>
          </div>
  );
}


export default StudentInfo