let onloadFunc = function() {
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			console.log("xhr ready");
			document.getElementById("user-greeting").innerHTML = xhr.responseText;
		}
	}
	
	xhr.open("GET", "GreetingServlet", true);
	xhr.send();
};

let logoutFunc = function() {
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			console.log("logged out");
			window.location.href = xhr.responseURL;
		}
	}
	
	xhr.open("GET", "LogoutServlet", true);
	xhr.send();
	
};


window.onload = (function() {
	
	console.log("EmpHome loaded");
	
	onloadFunc();
	
})();