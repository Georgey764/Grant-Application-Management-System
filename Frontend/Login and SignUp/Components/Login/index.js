const signUp = document.getElementById("sign_up");
const logInUrl = "http://localhost:8080/authenticate";
const onSuccessUrl = "http://localhost:3000/";

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
        window.location.href = onSuccessUrl;
      } else {
        window.location.href =
          window.location.href.split("?")[0] +
          "?error=" +
          encodeURIComponent(data.message);
      }
    })
    .catch((e) => console.log(e));
});
