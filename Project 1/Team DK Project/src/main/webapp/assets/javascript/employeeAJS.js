

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

