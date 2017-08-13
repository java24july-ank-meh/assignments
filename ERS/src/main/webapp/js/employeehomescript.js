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
    xhttp.open("POST", "ReimbServlet", true);
    xhttp.send(a + ":" + d + ":" + t);
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
    xhttp.send();
}