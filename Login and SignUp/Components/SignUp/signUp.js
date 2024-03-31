const logInPageUrl = "../Login/index.html";
const signUpUrl = "http://localhost:8080/sign-up";
const onSuccessUrl =
  "../Login/index.html?success=" +
  encodeURIComponent("account created successfully");
const input_firstName = document.querySelector("#w_first_name");
const input_lastName = document.querySelector("#w_last_name");
const input_department = document.querySelector("#w_major");
const input_email = document.querySelector("#w_email");
const input_cwid = document.querySelector("#w_cwid");
const input_agree = document.querySelector("#agree");
const input_password = document.querySelector("#w_password");
const input_c_password = document.querySelector("#w_c_password");
const form_submitForm = document.querySelector("#signup_form_box");

const login = document.getElementById("login_ref");
function GoToLogIn() {
  window.location.href = logInPageUrl;
}

const errorMessage = function (message) {
  document.querySelector("#error_div")?.remove();

  document
    .querySelector(".signup_form")
    .insertAdjacentHTML("afterbegin", `<div id='error_div'>${message}</div>`);
  setTimeout((e) => {
    document.querySelector("#error_div")?.remove();
  }, 3000);
};

input_c_password.addEventListener("input", (e) => {
  let passwordLength = input_password.value.length;
  if (
    e.target.value != input_password.value &&
    e.target.value.length >= passwordLength
  ) {
    errorMessage("Confirm password doesn't match");
  }
});

form_submitForm.addEventListener("submit", (e) => {
  e.preventDefault();
  let loginStatus = true;
  if (input_password.value.length <= 7) {
    errorMessage("Password must be atleast 8 characters long");
    loginStatus = false;
  }
  const regex = /\d{3,}/;
  if (!regex.test(input_password.value)) {
    errorMessage("Password must contain atleast 3 numbers");
    loginStatus = false;
  }
  if (input_c_password.value != input_password.value) {
    errorMessage("Confirm password doesn't match");
    loginStatus = false;
  }
  const emailAtAddress = input_email.value.substring(
    input_email.value.indexOf("@") + 1
  );
  if (
    emailAtAddress.toUpperCase() != "WARHAWKS.ULM.EDU" &&
    emailAtAddress.toUpperCase() != "ULM.EDU"
  ) {
    errorMessage("Must be a ULM or Warhawks domain email");
    loginStatus = false;
  }
  if (
    input_agree.checked &&
    emailAtAddress.toUpperCase() == "WARHAWKS.ULM.EDU"
  ) {
    errorMessage("Uncheck the faculty option");
    loginStatus = false;
  }
  if (!input_agree.checked && emailAtAddress.toUpperCase() == "ULM.EDU") {
    errorMessage("Check the faculty option");
    loginStatus = false;
  }
  if (loginStatus) {
    let authorityId = input_agree.checked ? 2 : 1;
    fetch(signUpUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        authorityId: authorityId,
        email: input_email.value,
        department: input_department.value,
        cwid: input_cwid.value,
        firstName: input_firstName.value,
        lastName: input_lastName.value,
        password: input_password.value,
      }),
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed creating user");
        }
        return res.json();
      })
      .then((data) => {
        window.location.href = onSuccessUrl;
      })
      .catch((e) => errorMessage(e));
  }
});
