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

let onloadFunc2 = function() {
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			console.log(xhr.responseText);
			
			let reqStrArr = xhr.responseText.split('|');
			let reqArr = [];
			for (let i=0; i<reqStrArr.length; i++) {
				console.log(reqStrArr[i]);
				reqArr[i] = JSON.parse(reqStrArr[i]);
			}
			
			addArrayToTable(reqArr);
		}
	}
	
	xhr.open("GET", "EmployeeRequestsServlet", true);
	xhr.send();
};

let addArrayToTable = function(dataArr) {
	
	var table = document.getElementById("requests_table");
	
	for (let i=0; i<dataArr.length; i++) {
		
		let row = document.createElement('tr');
		
		let columns = ['rid', 'amount', 'type', 'description', 'status'];
		
		for (let j=0; j<columns.length; j++) {
			 let cell = document.createElement('td');
			 cell.innerHTML = dataArr[i][columns[j]];
			 console.log(dataArr[i][columns[j]]);
			 row.appendChild(cell);
		}
		
		table.appendChild(row);
	}
	
};

window.onload = (function() {
	onloadFunc();
	onloadFunc2();
	
})();