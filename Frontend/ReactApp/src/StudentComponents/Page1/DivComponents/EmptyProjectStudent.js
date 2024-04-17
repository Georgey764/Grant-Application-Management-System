import React from 'react'

function EmptyProject({clickCreate}) {
  
  return (
    <div className="new_project m-5">

        <span className="fs-2"> Your Applications Fall 2024 </span>

        {/* Container for the empty portion */}
        <div className="container" style = {{display: 'flex', height: '70vh'}}>
            <div className='fs-3 text-secondary justify-content-center' style = {{margin: 'auto'}}>
                WOW Such Empty 😵‍💫 🫙
            </div>
        </div>
        <div className="action_buttons mt-5 d-flex justify-content-end">
            <button type="button" className="btn btn-success px-4" id = "create_button" onClick = {()=>clickCreate(true) }>
              Create
            </button>
        </div>


    </div>
  )
}

export default EmptyProject