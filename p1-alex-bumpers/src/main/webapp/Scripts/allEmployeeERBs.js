function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			callback(this);
		}
	}

	xhr.open("GET", url);
	xhr.send();
}


function display(xhr) {
	let reimbursements = JSON.parse(xhr.responseText);
	reimbursementsArr = reimbursements.erbemployee;

	let table = document.getElementById("table");

	for (i in reimbursementsArr) {
		let newRow = document.createElement("tr");
		if (reimbursementsArr[i].id) {
			id = `${reimbursementsArr[i].reimbursementId}`;
			console.log(id);
		} else {
			id = "NULL";
			console.log(id)
		}

		if (reimbursementsArr[i].employeeId) {
			employeeId = `${reimbursementsArr[i].employeeId}`;
			if (reimbursementsArr[i].employeeId == 4) {
				console.log(reimbursementsArr[i].employeeId)
			}
		} else {
			employeeId = "NULL";
		}


		if (reimbursementsArr[i].employees.name) {
			name = `${reimbursementsArr[i].employees.name}`;
		} else {
			name = "NULL";
		}

		if (reimbursementsArr[i].reimbursementAmount) {
			amount = `${reimbursementsArr[i].reimbursementAmount}`;
		} else {
			amount = "NULL";
		}

		if (reimbursementsArr[i].reimbursementStatus) {
			id = `${reimbursementsArr[i].reimbursementStatus}`;
		} else {
			id = "NULL";
		}

		newRow.innerHTML = `<td>${reimbursementsArr[i].reimbursementId}</td>
	 	<td>${reimbursementsArr[i].employeeId}</td>
	 	<td>${reimbursementsArr[i].employees.name}</td>
	 	<td>${reimbursementsArr[i].reimbursementAmount}</td>
	 	<td>${reimbursementsArr[i].reimbursementStatus}</td>
	 	
	 	`;

		table.appendChild(newRow);
	}
}


function populateUser(xhr) {
	let response = JSON.parse(xhr.responseText);
	console.log(response);

	if (response == null) {
		window.location = "http://localhost:8080/p1-alex-bumpers/login";
	} else if (response.username == null) {
		window.location = "http://localhost:8080/p1-alex-bumpers/login";
	} else {
		let username = document.getElementById("username");
		username.innerHTML = response.username;
	}
}

var erbsUrl = "http://localhost:8080/p1-alex-bumpers/erbemployee";
var reimbursementsUrl = "http://localhost:8080/p1-alex-bumpers/reimbursements";
var sessionUrl = "http://localhost:8080/p1-alex-bumpers/session"

sendAjaxGet(erbsUrl, display);
sendAjaxGet(sessionUrl, populateUser)