window.onload = auth;

function auth() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === "fail") {

                window.location = "loginpage.html";
            } else {
            	loadResReimbs();
            }
        }
    }
    xhttp.open("POST", "AuthenticationServlet", true);
    xhttp.send("1");
}

function loadResReimbs() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
        	displayResReimbTable(JSON.parse(xhttp.responseText));
        }
    }
    xhttp.open("GET", "ReimbServlet", true);
    xhttp.send();

}


function displayResReimbTable(rJSON) {
	let res = rJSON.resolvedReimbs;
	for (let i = 0; i < res.length; i++) {
        table = document.getElementById("reimbtable");
        row = table.insertRow();
        cell = row.insertCell();
        cell.innerHTML = res[i].id;
        cell = row.insertCell();
        cell.innerHTML = typeType(res[i].status);
        cell = row.insertCell();
        cell.innerHTML = "$"+res[i].amount;
        cell = row.insertCell();
        cell.innerHTML = res[i].description;
        cell = row.insertCell();
        cell.innerHTML = statusType(res[i].status);
	}
}

function typeType(num) {
    switch (num) {
        case 1:
            return "Medical";
        case 2:
            return "Business";
        case 3:
            return "Travel";
        case 4:
            return "Family";
        default:
            return "Unknown";
    }
}

function statusType(num) {
    switch (num) {
        case 1:
            return "Pending";
        case 2:
            return "Approved";
        case 3:
            return "Denied";
            return "Unknown";
    }
}