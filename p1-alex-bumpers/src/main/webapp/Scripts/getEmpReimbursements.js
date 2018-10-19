let sessionId;
const isUserUrl = "http://localhost:8080/p1-alex-bumpers/session";

function validateUser(xhr) {
  let response = JSON.parse(xhr.responseText);
  if (response == null || response.username == null) {
    window.location = "http://localhost:8080/p1-alex-bumpers/login";
  }

  let username = document.getElementById("username");
  username.innerHTML = response.username;
  console.log(response);

  sessionId = response.id;
  console.log('id', sessionId);

  let initUrl = `${reimbursementUrl}?employeeId=${sessionId}`;
  sendAjaxGet(initUrl, populateTable);
}

function populateTable(xhr) {
  let tbody = document.getElementById("tbody");
  //
  console.log(xhr);
  let response = JSON.parse(xhr.responseText);
  let reimbursements = response.reimbursements;

  for (let reimbursement of reimbursements) {
    let trEl = document.createElement("tr");

    trEl.innerHTML = `
      <td>${reimbursement.reimbursementId}</td>
	 	<td>${reimbursement.employeeId}</td>
	 	<td>${reimbursement.employees.name}</td>
	 	<td>${reimbursements.reimbursementAmount}</td>
	 	<td>${reimbursements.reimbursementStatus}</td>
//      <td>${reimbursement.manager ? reimbursement.manager.name : "N/A"}</td>
    `;

    tbody.appendChild(trEl);
  }


}


// Sets up the intial page for employee home page
function sendAjaxGet(url, callback) {
  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      callback(this);
    }
  }

  xhr.open("get", url);
  xhr.send();
}

// validating user and initializing sessionId var 
// for use in later AJAX calls
sendAjaxGet(isUserUrl, validateUser);

function sendAjaxPost(url, callback, data) {
  console.log("url: ", url);
  let xhr = new XMLHttpRequest();

  xhr.onload = function () {
    if (this.readyState == 4 && this.status == 200) {
      callback(this);
    }
  }

  xhr.open("POST", url);
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
  xhr.send(data);
}
const reimbursementUrl = "http://localhost:8080/p1-alex-bumpers/reimbursements";

document.addEventListener("DOMContentLoaded", function () {
  //prevent default submission on submit
  let submit = document.getElementById("submit");
  let reqAlert = document.getElementById("reqAlert");
  submit.addEventListener("click", function (e) {
    e.preventDefault();
    let amount = document.getElementById("amount");
    let reason = document.getElementById("reason");
    if ((amount.value < 0 || amount.value == "") || reason.value.length < 6) {
      addInvalidAlert(reqAlert);
      return;
    } else {
      removeInvalidAlert(reqAlert);
      let formData = `amount=${amount.value}&reason=${reason.value}`;
      console.log(formData);
      amount.value = "";
      reason.value = "";
      sendAjaxPost(reimbursementUrl, showAlert, formData);
      return;
    }
    // tie form data to form for POSTing
  });

  // Populate pending tab already


  function showAlert(xhr) {
    let submitAlert = document.getElementById("submitAlert");
    submitAlert.classList.remove("noDisplay");
    submitAlert.classList.add("doDisplay");
    // hide after 2secs
    setTimeout(function () {
      submitAlert.classList.remove("doDisplay");
      submitAlert.classList.add("noDisplay");
    }, 3000);
  }
  // for user input validation alerts

  function addInvalidAlert(el) {
    el.classList.remove("noDisplay");
    el.classList.add("doDisplay");
  }

  function removeInvalidAlert(el) {
    el.classList.remove("doDisplay");
    el.classList.add("noDisplay");
  }

  // Event listeners for pending/resolved reimbursements
  let navTabs = document.getElementsByClassName("rNav");
  // pending populate table
  navTabs[0].addEventListener("click", function () {
    tbody.innerHTML = "";
    navTabs[0].classList.add("activeTab");
    navTabs[1].classList.remove("activeTab");
    tbody.innerHTML = "";
    let url = `${reimbursementUrl}?employeeId=${sessionId}&currentState=pending`;
    sendAjaxGet(url, populateTable);
  });

  navTabs[1].addEventListener("click", function () {
    tbody.innerHTML = "";
    navTabs[1].classList.add("activeTab");
    navTabs[0].classList.remove("activeTab");
    let url = `${reimbursementUrl}?employeeId=${sessionId}&currentState=resolved
    `;
    sendAjaxGet(url, populateTable);
  });




});