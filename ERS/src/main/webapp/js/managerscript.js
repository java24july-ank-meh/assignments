
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
        for (let j = 0; j < rJSON[i].pendingReimbs.length; j++) {
            table = document.getElementById("emptable");
            row = table.insertRow();
            row.setAttribute("data-toggle", "collapse");
            row.setAttribute("data-target", "#entry" + i + j);//input for loop number here
            row.setAttribute("class", "accordian-toggle");
            cell = row.insertCell();
            cell.innerHTML = rJSON[i].firstname + " " + rJSON[i].lastname;
            cell = row.insertCell();
            cell.innerHTML = typeType(rJSON[i].pendingReimbs[j].status);
            cell = row.insertCell();
            cell.innerHTML = "<select id=\"sel"+i+j+"\" class=\"form-control\"><option value=\"2\">Approve</option><option value=\"3\">Disapprove</option></select>";
            cell = row.insertCell();
            cell.innerHTML = "<button id =\"but"+i+j+"\" class=\"btn btn-default\" type=\"submit\">Submit</button>";
            document.getElementById("but"+i+j).onclick= function() {updateStatus(i.toString()+j.toString(), rJSON[i].pendingReimbs[j].id)};
            console.log(document.getElementById("but"+i+j).onclick);
            row = table.insertRow();
            cell = row.insertCell();
            cell.innerHTML = "<div class=\"accordian-body collapse\" id=\"entry" + i + j + "\"><div class=\"col-md-8\"><p class=bg-info>" + rJSON[i].pendingReimbs[j].description + "</p></div></div>";
        }
    }
}

function test(message) {
	console.log(message);
}
function updateStatus(ij, id) {
    let val = document.getElementById("sel" + ij).value;


    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            window.location = "managerhome.html";

        }
    }
    xhttp.open("POST", "ManagerServlet", true);
    xhttp.send(val+","+id);
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