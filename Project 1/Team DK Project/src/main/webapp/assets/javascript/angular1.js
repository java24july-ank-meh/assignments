
var myWebApp = angular.module('rWebSite', []); //Empty brackets for dependencies

myWebApp.controller('loginPart', function loginPart($scope){
	$scope.numberOfLogins = 0;
	$scope.user = [{userName: "u", passWord: "p"}]
	$scope.loginInfo = "";

	$scope.login = function (userName, passWord) {
		$scope.user.push({userName: userName,passWord: passWord});
	}	

});

myWebApp.controller('loginCtrl', function loginCtrl($scope, $http) {

	$scope.getAUserFromServer = function () {
		$http({
			method: 'GET',
			url: 'test'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};

});







