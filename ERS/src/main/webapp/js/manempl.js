window.onload = loadData;

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
            table = document.getElementById("emptable");
            row = table.insertRow();
            cell = row.insertCell();
            cell.innerHTML = rJSON[i].firstname + " " + rJSON[i].lastname;
            cell = row.insertCell();
            cell.innerHTML = roleType(rJSON[i].role);
            cell = row.insertCell();
        
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