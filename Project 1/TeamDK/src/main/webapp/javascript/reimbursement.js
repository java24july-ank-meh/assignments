var riembursementApp = angular.module('rbWeb', []);

riembursementApp.controller('reimburseControll', function(){
	
	$sope.getAllReimbursements = function(){
		$http({
		method: 'Get',
		url: 'ReimbursementsView'
		}).success(function(data,status,headers,config){
			$scope.reimbursement = data;
		}).error(function(data,status,headers,config){
			
		});
		
	};
});