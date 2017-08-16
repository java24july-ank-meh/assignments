/**
 * 
 */

function openNav() {
	document.getElementById("mySidenav").style.width = "250px";
	document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
	document.getElementById("main").style.marginLeft = "0";
}
/*
//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}
*/

function maninfo() {
	$.get("/manager");
}

function manhomepage(){
	$.get("/home")
}
function manallemployee(){
	$.get("/home")
}
function manviewallreim(){
	$.get("/reimbursements")
}
function manreim(){
	$.get("/myreimbursements")
}

