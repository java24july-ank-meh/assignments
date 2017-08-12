
var myWebApp = angular.module('rWebSite', []); //Empty brackets for dependencies

myWebApp.controller('loginPart', function loginPart($scope){
	$scope.numberOfLogins = 0;
	$scope.user = [{userName: "u", passWord: "p"}]
/*	$scope.newUser = {};*/
	$scope.person = [];

	$scope.login = function (userName, passWord) {
		$scope.user.push({userName: userName,passWord: passWord});
	}	

});

var userService= myWebApp.factory("UserServ",['$http', function($http){
	var fac = {};
	
	fac.addUser = function(user) {
		$http.post("/User/AddUser", user).succes(function (response) {
			alert(response.status);
		})
	}
	
	
	return fac;	
}]);

var loginController = myWebApp.controller('loginCtrl', function loginCtrl($scope, UserServ, $http) {

	$scope.getAUserFromServerGet = function () {
		$http({
			method: 'GET',
			url: 'test'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};
	
	$scope.getAUserFromServerPost = function () {
		$http({
			method: 'Post',
			url: 'test'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};
	
	$scope.updateUserToServer = function () {
		/*alert("Called");*/
		/*UserServ.addUser($scope.newUser);*/
		
		let dataU = angular.toJson($scope.newUser);
		console.log(dataU);
		/*var name = $scope.newName;
		alert(name);
		$scope.person.firstName = name;
		$http.post("/user/AddUser", dataU).succes(function (response) {
			alert(response.status);
		});
		*/
		
		
		$http({
			method: 'Post',
			url: 'user' /*?data='+dataU*/,
			data: dataU
		}).success(function(status) {
			alert(status);
		}).error(function(status, error) {
			alert(status + "/ "+error);
		});
		
	};

	

});







