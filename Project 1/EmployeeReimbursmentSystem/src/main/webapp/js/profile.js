let onPageLoadFunc = function() {
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			console.log("xhr ready");
			let userObj = JSON.parse(xhr.responseText);
			document.getElementById("username-text").innerHTML = userObj.username;
			
			document.getElementById("firstname-text").innerHTML = userObj.firstname;
			document.getElementById("lastname-text").innerHTML = userObj.lastname;
			document.getElementById("email-text").innerHTML = userObj.email;
			document.getElementById("role-text").innerHTML = userObj.role;
		}
	}
	
	xhr.open("GET", "UserInfoServlet", true);
	xhr.send();
	
};



window.onload = (function() {
	
	console.log("profile page script running")
	
	onPageLoadFunc();
	
})();