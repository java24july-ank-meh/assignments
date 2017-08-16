function loadEmp() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		console.log(this.readyState);
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			if (xhttp.responseText === "fail") {

				window.location = "loginpage.html";
			}
			displayTable(JSON.parse(xhttp.responseText))

		}
	}
	xhttp.open("GET", "ManagerEmplReimbServlet", true);
	xhttp.send();
}


function displayTable(rJSON) {
	reimb = rJSON.resReimbs;
    for (let i = 0; i < resReimbs; i++) {
        
        table = document.getElementById("emptable");
        row = table.insertRow();
        cell = row.insertCell();
        cell.innerHTML = typeType(rJSON[i].pendingReimbs[j].type);
        cell = row.insertCell();
        cell.innerHTML = "$"+reimbs[i].amount;
        cell = row.insertCell();
        cell.innerHTML = reimbs[i].description;
        cell = row.insertCell();
        cell.innerHTML = statusType(reimbs[i].status);
        cell = row.insertCell();
        cell.innerHTML = reimbs.resolver;
        cell = row.insertCell();
        cell.innerHTML = reimbs.resolved;

        
    }
}