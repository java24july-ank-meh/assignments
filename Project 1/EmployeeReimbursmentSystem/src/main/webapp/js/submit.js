let submitFunc = function() {
	
	console.log("submitFunc called");
	
	let formAmount = document.getElementById("inputAmount").value;
	let formDesc = document.getElementById("inputDesc").value;
	let formImg = document.getElementById("inputImg").files[0];
	let formType = document.getElementById("inputType").value;
	
	let formData = new FormData();
	formData.append("amount", formAmount);
	formData.append("description", formDesc);
	formData.append("imagefile", formImg);
	formData.append("type", formType);
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			alert(xhr.responseText);
		}
	}
	
	xhr.open("POST", "SubmitServlet", true);
	xhr.send(formData);
	
};



window.onload = (function() {
	
	console.log("Submit.js loaded");
	
	
})();