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
			method : 'POST',
			url : 'loginrequest',
			params : {"username" : un, "password" : pw }
		}).then(function callBack(response) {
			alert("success");
			alert(response.data);
			alert(response.status);
//			alert(response.statusText);
//			alert(response.header);
//			 document.write(response.data);
		}, function errorCallBack(response){
			alert("error");
		});
		
	};
	
	
	
});





