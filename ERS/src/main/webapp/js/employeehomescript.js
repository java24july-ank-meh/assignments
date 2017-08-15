window.onload = loadPendReimbs;

function submitReimb() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("submitresult").innerHTML = xhttp.responseText;
        }
    }
    var a = document.getElementById("amount").value;
    var d = document.getElementById("description").value;
    var t = document.getElementById("type").value;
    var f = document.getElementById("fileinput").files[0];
    var form = new FormData();
    form.append("amount", a);
    form.append("description", d);
    form.append("type", t);
    form.append("fileinput", f);
    xhttp.open("POST", "ReimbServlet", true);
    console.log(form);
    xhttp.send(form);
}

function loadEmp() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === "fail") {

                window.location = "loginpage.html";
            }
        }
    }
    xhttp.open("POST", "AuthenticationServlet", true);
    xhttp.send("1");
}

function loadPendReimbs() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
        	displayPendReimbTable(JSON.parse(xhttp.responseText));
        }
    }
    xhttp.open("GET", "ReimbServlet", true);
    xhttp.send();

}


function displayPendReimbTable(rJSON) {
	let pend = rJSON.pendingReimbs;
	for (let i = 0; i < pend.length; i++) {
        table = document.getElementById("reimbtable");
        row = table.insertRow();
        cell = row.insertCell();
        cell.innerHTML = pend.description;
        cell = row.insertCell();
        cell.innerHTML = typeType(pend.type);
        cell = row.insertCell();
        cell.innerHTML = "pending";
        cell = row.insertCell();
	}
}