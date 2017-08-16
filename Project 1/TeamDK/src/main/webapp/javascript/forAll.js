/**
 * 
 */

angular.module('reimbWebSite', []);

angular.module('reimbWebSite').controller('navCtrl', function($scope,$http) {

	$scope.maninfo = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"info"}		
		});
	};



	});