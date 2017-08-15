/**
 * For employee pages
 */

function openNav() {
	document.getElementById("mySidenav").style.width = "250px";
	document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
	document.getElementById("main").style.marginLeft = "0";
}

//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}

//let loggedInUSer = id;

var empMod =  angular.module('empMod', []); // Empty brackets for dependencies


empMod.controller('infoCtrl', function infoCtrl($scope){
	
	$scope.user;
	
	$scope.getUser = function(){
		$http({
			method : 'GET',
			url : 'employee',
			data : loggedInUser
		}).success(function(data, header, status) {
			alert("data "+data);
			alert("status "+status);
			alert("header "+header);
			$scope.user = data;
		}).error(function(error, status,header) {
			alert("error "+error);
			alert("status "+status);
			alert("header "+header);
		});		
		
		$scope.user.update = false;
	};
	
	
});

function goToSubmitRequest(){
	alert("submit request");
}

