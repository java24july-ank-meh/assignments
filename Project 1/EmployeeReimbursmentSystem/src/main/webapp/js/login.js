

window.onload = console.log("login.js loaded\n");
	
let statusElement = document.getElementById("login_status_display");


function loginFunc() {
	console.log("loginFunc called\n");
	let xhttp = new XMLHttpRequest();
	
	
	xhttp.onreadystatechange = function() {
		if(xhttp.readyState === 4 && xhttp.status === 200) {
			console.log("xhr ready");
			responseProcessing(this);
			
		}
	}
	
	xhttp.open("POST", "LoginServlet", true);
	xhttp.setRequestHeader("Content-Type", "text/plain");
	xhttp.send(document.getElementById("username").value+","+document.getElementById("password").value);
}
	
//loginElement.addEventListener("submit", function(){loginFunc();}, false);

function responseProcessing(xhttp) {
	let responseText = xhttp.responseText;
	if (responseText.indexOf("Invalid username and password!") !== -1) {
		statusElement.innerHTML = xhttp.responseText;
		
	} else {
		window.location.href = xhttp.responseURL;
	}
}