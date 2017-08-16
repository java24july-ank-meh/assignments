/**
 * 
 */

// Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}

var loginMod = angular.module('loginMod', [ 'ui.router' ]); // Empty brackets
// for dependencies


loginMod.controller('loginCtrl', function loginCtrl($scope, $http) {

	$scope.username;
	$scope.password;

	$scope.tryLogin = function() {
		let usern = $scope.username;
		let passw = $scope.password;

		// alert(un);
		// alert(pw);

		$http({
			method : 'POST',
			url : 'loginrequest',
			params : {
				"username" : usern,
				"password" : passw
			}
		}).then(function callBack(response,request) {
		 alert("success");
		 alert(response.url);
		 alert(response);
		 alert(response.toString());
		 alert(request.header);
		 alert(request.url);
		 alert(request.responseType);
		 alert(request.getBody());
		 
		// // alert(response.data);
		// // alert(response.status);
		// // alert(response.statusText);
		// // alert(response.header);
		// // document.write(response.data);
		 }, function errorCallBack(response){
		// alert("error");
		 });

	};

});
