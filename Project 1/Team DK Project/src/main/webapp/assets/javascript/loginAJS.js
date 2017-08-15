/**
 * 
 */

//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}


var loginMod =  angular.module('loginMod', []); // Empty brackets for dependencies


loginMod.controller('loginCtrl', function loginCtrl($scope, $http){
	
	$scope.username;
	$scope.password;
	
	$scope.tryLogin = function(){
		let usern = $scope.username;
		let passw = $scope.password;

		let un = angular.toJson(usern);
		let pw = angular.toJson(passw);
		
//		alert(un);
	//	alert(pw);
		
		$http({
			method : 'GET',
			url : 'login',
			params : {"username" : un, "password" : pw }
		});
		
		/*.success(function(status) {
			alert("success");
		}).error(function(error) {
			alert("error");
		});*/		
		
	};
	
	
});





