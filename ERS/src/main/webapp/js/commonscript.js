function logOut() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText)
            if (xhttp.responseText === "fail") {
                window.location = "loginpage.html";
            }

        }
    }
    xhttp.open("POST", "AuthenticationServlet", true);
    xhttp.send("logout");
}