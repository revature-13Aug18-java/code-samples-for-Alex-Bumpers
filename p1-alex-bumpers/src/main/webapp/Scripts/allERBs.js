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
	console.log(reimbursements);
	reimbursementsArr = reimbursements.reimbursements;
	console.log(reimbursementsArr);

	let table = document.getElementById("table");

	for (i in reimbursementsArr) {
		let newRow = document.createElement("tr");

		if (reimbursementsArr[i].id) {
			id = `${reimbursementsArr[i].reimbursementId}`;
		} else {
			id = "NULL";
		}

		if (reimbursementsArr[i].employeeId) {
			employeeId = `${reimbursementsArr[i].employeeId}`;
		} else {
			employeeId = "NULL";
		}


		if (reimbursementsArr[i].name) {
			name = `${reimbursementsArr[i].employees.name}`;
		} else {
			name = "NULL";
		}

		if (reimbursementsArr[i].reimbursementAmount) {
			id = `${reimbursementsArr[i].reimbursementAmount}`;
		} else {
			id = "NULL";
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

sendAjaxGet("http://localhost:8080/p1-alex-bumpers/reimbursements", display);

sendAjaxGet("http://localhost:8080/p1-alex-bumpers/session", populateUser)