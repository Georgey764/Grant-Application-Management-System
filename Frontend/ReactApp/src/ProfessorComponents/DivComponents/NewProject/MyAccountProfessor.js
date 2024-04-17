import React from 'react'

function MyAccount() {
  return (
    <div className="MyAccount m-5">
      <span className="fs-2"> My Account </span>
        <div className="fs-4 mt-5">
            Take Student who have submitted more than one application ?
        </div>
        <div className="mt-5 d-flex justify-content-end">
            <button type="button" className="btn btn-success px-4" id = "frequency_accept">
              Yes
            </button>
        </div>
    </div>
  )
}

export default MyAccount