const signUp = document.getElementById("sign_up");
const logInUrl = "http://localhost:8080/authenticate";
const onSuccessFacultyUrl = "https://www.youtube.com";
const onSuccessStudentUrl = "https://www.youtube.com";
const onSuccessAdminUrl = "https://www.youtube.com";

const urlParams = new URLSearchParams(window.location.search);
if (urlParams.has("error")) {
  document
    .querySelector("#login_form_box")
    .insertAdjacentHTML(
      "afterbegin",
      `<div id='error_div'>${urlParams.get("error")}</div>`
    );
  setTimeout(function () {
    document.querySelector("#error_div").remove();
  }, 3000);
}

if (urlParams.has("success")) {
  document
    .querySelector("#login_form_box")
    .insertAdjacentHTML(
      "afterbegin",
      `<div id='success_div'>${urlParams.get("success")}</div>`
    );
  setTimeout(function () {
    document.querySelector("#success_div").remove();
  }, 3000);
}

function GoToSignUp() {
  window.location.href = "../SignUp/signUp.html";
}

document.querySelector("#login_form_box").addEventListener("submit", (e) => {
  e.preventDefault();
  let email = document.querySelector("#warhawks_email").value;
  let password = document.querySelector("#warhawks_password").value;
  fetch(logInUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
      password: password,
    }),
  })
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      if (data.jwt != null) {
        sessionStorage.setItem("jwt", data.jwt);
        if (data.authority == "ROLE_STUDENT") {
          window.location.href = onSuccessStudentUrl;
        } else if (data.authority == "ROLE_FACULTY") {
          window.location.href = onSuccessFacultyUrl;
        } else if (data.authority == "ROLE_ADMIN") {
          window.location.href = onSuccessAdminUrl;
        }
      } else {
        window.location.href =
          window.location.href.split("?")[0] +
          "?error=" +
          encodeURIComponent(data.message);
      }
    })
    .catch((e) => console.log(e));
});
