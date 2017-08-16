

function updateEmp() {
    	let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			console.log(this.readyState);
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById("updateresult").innerHTML = xhttp.responseText;
				
			}
		}
		let p = document.getElementById("password").value;
		let f = document.getElementById("firstname").value;
		let l = document.getElementById("lastname").value;
		let e = document.getElementById("email").value;
		xhttp.open("POST", "EmpUpdateServlet", true);
		xhttp.send(p+":"+f+":"+l+":"+e);
    }
    
    function loadEmp() {
    	let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			console.log(this.readyState);
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				if (xhttp.responseText === "fail") {

					window.location = "loginpage.html";
				}
				let u = JSON.parse(xhttp.responseText);
				document.getElementById("password").value = u.password;
				document.getElementById("firstname").value = u.firstname;
				document.getElementById("lastname").value = u.lastname;
				document.getElementById("email").value = u.email;
				document.getElementById("navname").innerHTML = u.firstname+" "+ u.lastname;
				let link = document.getElementById("homelink");
				if (u.role === 1) {
					link.setAttribute("href", "employeehome.html");
				} else if (u.role === 2) {
					link.setAttribute("href", "managerhome.html");
				}
					
			}
		}
		xhttp.open("POST", "AuthenticationServlet", true);
		xhttp.send("-1");
    }