let jumboHeight = $('.jumbotron').outerHeight();

function parallax() {
    var scrolled = $(window).scrollTop();
    $('.bg').css('height', (jumboHeight - scrolled) + 'px');
}

$(window).scroll(function(e) {
    parallax();
});

function login() {
    let xhttp = new XMLHttpRequest();
    xhttp.responseType = "text";
    xhttp.onreadystatechange = function() {
        console.log(this.readyState);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === 'employee') {
                window.location = "employeehome.html";
            } else if (xhttp.responseText === 'manager') {
                window.location = "managerhome.html";
            } else {
                document.getElementById("error").innerHTML = xhttp.responseText;
            }
        }
    }
    var username = document.getElementById("username").value
    var password = document.getElementById("password").value
    xhttp.open("POST", "LoginServlet", true);
    xhttp.send(username + ":" + password);
}