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

angular.module('reimbWebSite').controller('navECtrl', function($scope,$http) {

	function authorize(){
	
		$rootScope.user = function() {
			$http({
				method : 'GET',
				url : 'user'	
			});
		};
		
	}
	
//	$scope.user = $rootScope.user;
//	let un = $scope.user.username;
	
	$scope.empinfo = function() {
		$http({
			method : 'GET',
			url : 'employee',
			params: {"link":"info"}		
		});
	};
	
	$scope.empinfoup = function() {
		$http({
			method : 'POST',
			url : 'user',
			params: {"un":un}		
		});
	};
	
	$scope.emphome = function() {
		$http({
			method : 'GET',
			url : 'employee',
			params: {"link":"home"}		
		});
	};

	$scope.submitreimb = function() {
		$http({
			method : 'GET',
			url : 'employee',
			params: {"link":"submit"}	
		});
	};

	$scope.myreimbs = function() {
		$http({
			method : 'GET',
			url : 'employee',
			params: {"link":"myreimbursements"}		
		});
	};

	$scope.photoup = function() {
		$http({
			method : 'GET',
			url : 'employee',
			params: {"link":"photo"}		
		});
	};
	

	
});