



function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if (this.readyState==4 && this.status==200) {
			callback(this);
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}


function display(xhr) {
	let employees = JSON.parse(xhr.responseText);
	console.log(employees);
	employeeArr = employees.employees;
	console.log(employeeArr);
	
	 let table = document.getElementById("table");
	
	 for(i in employeeArr) {
	 	let newRow = document.createElement("tr");
		
	 	if (employeeArr[i].id) {
	 		id = `${employeeArr[i].id}`;
	 	} else {
	 		id = "NULL";
	 	}
	 	
	 	if (employeeArr[i].name) {
	 		name = `${employeeArr[i].name}`;
	 	} else {
	 		name = "NULL";
	 	}
	 	
	 	
	 	if (employeeArr[i].username) {
	 		id = `${employeeArr[i].username}`;
	 	} else {
	 		id = "NULL";
	 	}
	 	
	 	if (employeeArr[i].position) {
	 		id = `${employeeArr[i].position}`;
	 	} else {
	 		id = "NULL";
	 	}
	 	
	 	if (employeeArr[i].reimbursements) {
	 		id = `${employeeArr[i].reimbursements}`;
	 	} else {
	 		id = "NULL";
	 	}
		
	 	newRow.innerHTML = `<td>${employeeArr[i].id}</td>
	 	<td>${employeeArr[i].name}</td>
	 	<td>${employeeArr[i].username}</td>
	 	<td>${employeeArr[i].position}</td>
	 	
	 	`
	 	;
		
	 	table.appendChild(newRow);
	 }
}


sendAjaxGet("http://localhost:8080/p1-alex-bumpers/employees", display);





