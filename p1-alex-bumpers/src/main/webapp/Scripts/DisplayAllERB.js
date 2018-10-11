document.addEventListener("DOMContentLoaded", function () {
	let reimbursementContainers = document.getElementsByClassName("reimbursementsTable");
	let reimbursementItems = document.getElementsByClassName("ERBNavItem");
	console.log(reimbursementItems.innerHTML);
  let count = 0;
  for (let ri of reimbursementItems) {
    ri.addEventListener('click', function () {
      // toggle style of nav tab
      toggleActive(this, reimbursementItems);
      toggleTablesOn(this, reimbursementContainers);
    });
  }

  function toggleTablesOn(ri, tables) {
    let selectTableActivate;
    let tableIndex;
    
    switch(ri.innerHTML.toLowerCase()) {
      case "All Employee Reimbursement Requests":
        tableIndex = 0;
    	  selectTableActivate = tables[0];

        break;
      case "Pending":
    	  selectTableActivate = tables[1];
    	  tableIndex = 1;
        break;
      case "Resolved":
    	  selectTableActivate = tables[2];
    	  tableIndex = 2;
        break;
    }

    for(let t of tables) {
      t.classList.remove("doDisplay");
      t.classList.add("noDisplay");
      if(t == selectTableActivate) {
        t.classList.remove("noDisplay");
        t.classList.add("doDisplay");
      }
    }
  }
//
  function toggleActive(ri, ritems) {
    for (let item of ritems) {
      item.classList.remove("activeItem");
      if (item == ri) {
        ri.classList.add("activeItem");
      }
    }
  }

  
});