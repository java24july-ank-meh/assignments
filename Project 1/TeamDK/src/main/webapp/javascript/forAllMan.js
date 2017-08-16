/**
 * 
 */

function openNav() {
	document.getElementById("mySidenav").style.width = "300px";
	document.getElementById("main").style.marginLeft = "300px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
	document.getElementById("main").style.marginLeft = "0";
}

angular.module('reimbWebSite').controller('navMCtrl', function($scope,$rootScope,$http) {

//	$scope.user = $rootScope.user;
//	if($scope.user){
//		var un = $scope.user.username;
//	}

	$scope.maninfo = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"info"}		
		});
	};

	$scope.manhome = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"home"}		
		});
	};

	$scope.allReimbs = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"reimbursments"}	
		});
	};

	$scope.myreimbs = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"myreimbursements"}		
		});
	};

	$scope.allemps = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"employees"}		
		});
	};

	$scope.logout = function() {
		$http({
			method : 'POST',
			url : 'login'	
		});
	};

});




/*
//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}
 */
