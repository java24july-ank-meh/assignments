
function loadData() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            displayTable(JSON.parse(xhttp.responseText));

        }
    }
    xhttp.open("POST", "ManagerServlet", true);
    xhttp.send();
}

function displayTable(rJSON) {
    for (let i = 0; i < rJSON.length; i++) {
        console.log(rJSON[i]);
        for (let j = 0; j < rJSON[i].pendingReimbs.length; j++) {
            table = document.getElementById("emptable");
            row = table.insertRow();
            row.setAttribute("data-toggle", "collapse");
            row.setAttribute("data-target", "#entry" + i + j);//input for loop number here
            row.setAttribute("class", "accordian-toggle");
            cell = row.insertCell();
            cell.innerHTML = rJSON[i].firstname + " " + rJSON[i].lastname;
            cell = row.insertCell();
            cell.innerHTML = roleType(rJSON[i].role);
            cell = row.insertCell();
            cell.innerHTML = "<select class=\"form-control\"><option>Approve</option><option>Disapprove</option></select>";
            cell = row.insertCell();
            cell.innerHTML = "<button class=\"btn btn-default\" type=\"submit\">Submit</button>";

            row = table.insertRow();
            cell = row.insertCell();
            cell.innerHTML = "<div class=\"accordian-body collapse\" id=\"entry" + i + j + "\"><div class=\"col-md-8\"><p class=bg-info>" + rJSON[i].pendingReimbs[j].description + "</p></div></div>";
        }
    }
}

function roleType(num) {
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