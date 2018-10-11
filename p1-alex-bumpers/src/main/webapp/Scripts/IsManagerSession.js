/**
 * 
 */

document.addEventListener("DOMContentLoaded", function () {
	const reimbursementsUrl = "http://localhost:8080/p1-alex-bumpers/reimbursements"
	const isUserUrl = "http://localhost:8080/p1-alex-bumpers/session";



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
			// ajax get reqs
			sendAjaxGet(reimbursementsUrl, populateERBs);
			sendAjaxGet(reimbursementsUrl + "?reimbursementStatus=pending", populatePendingERBs);
			sendAjaxGet(reimbursementsUrl + "?reimbursementStatus=resolved", populateResolvedERBs);
		}
	}

	function populateERBs(xhr) {
		let response = JSON.parse(xhr.responseText);
		let reimbursements = response.reimbursements;

		let table = document.getElementById("tbodyGetERBs");
		table.innerHTML = "";

		for (let r of reimbursements) {
			let tr = document.createElement("tr");
			tr.innerHTML = `
				<td>${r.reimbursementId}</td>
				<td>${r.employeeId}</td>
				<td>${r.employees.name}</td>
				<td>${r.reimbursementAmount}</td>
				<td>${r.reimbursementStatus}</td>
//				<td>${r.managers != undefined ? r.managers.name : "Manager name unavailable."}</td>
			`;

			if (r.reimbursementStatus.toLowerCase() == 'pending') {
				tr.innerHTML += `
					<td>
					<i class="material-icons pointer approve">
					DONE
					</i>
					</td>
					<td>
					<i class="material-icons pointer deny">
					DELETE_OUTLINE
					</i>
					</td>
					`;
			}

			table.appendChild(tr);
		}
	}



	// check user url, validate, populate, and send request(s)
	sendAjaxGet(isUserUrl, populateUser);

	// send AJAX calls on click of appropriate site items
	let reimbursementItems = document.getElementsByClassName("ERBNavItem");
	let reimbursementContainers = document.getElementsByClassName("reimbursementsTable");

	reimbursementItems[0].addEventListener('click', function () {
		sendAjaxGet(reimbursementsUrl, populateERBs)
	});

});