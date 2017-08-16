window.onload = auth;

function auth() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === "fail") {

                window.location = "loginpage.html";
            } else {
            	loadData();
            }
        }
    }
    xhttp.open("POST", "AuthenticationServlet", true);
    xhttp.send("2");
}

function loadData() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            displayTable(JSON.parse(xhttp.responseText));

        }
    }
    xhttp.open("GET", "ManagerServlet", true);
    xhttp.send();
}

function displayTable(rJSON) {
    for (let i = 0; i < rJSON.length; i++) {
            list = document.getElementById("employeelist");
            button = document.createElement("button");
            button.setAttribute("type", "button");
            button.setAttribute("class", "list-group-item");
            button.onclick = function(){opening(rJSON.id)};
            button.innerHTML = rJSON[i].firstname +" "+rJSON[i].lastname;
            list.appendChild(button);
        
    }
    
}

function roleType(num) {
    switch (num) {
        case 1:
            return "Employee";
        case 2:
            return "Manager";
        default:
            return "Unknown";
    }
}

function opening(userid) {
	let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
           window.location = "linktoemplreimb.html"
        }
    }
    xhttp.open("POST", "ManagerEmplReimbServlet", true);
    xhttp.send(userid);
}