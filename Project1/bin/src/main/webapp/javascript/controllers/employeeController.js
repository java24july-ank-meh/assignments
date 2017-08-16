app.controller('employeeController', 
	function($scope, $http) {
	    	$scope.pendingReimbursements = [{id: 1, name: 'A', amount: 'A', status: 'PENDING'},
	    		{id: 2, name: 'A', amount: 'A', status: 'PENDING'},
	    		{id: 3, name: 'A', amount: 'A', status: 'PENDING'}];
	    	$scope.resolvedReimbursements = [{id: 4, name: 'A', amount: 'A', status: 'APPROVED'},
	    		{id: 5, name: 'A', amount: 'A', status: 'APPROVED'},
	    		{id: 6, name: 'A', amount: 'A', status: 'APPROVED'}];
	    	
	    	
	    	$scope.employee = {id: 1, userName: 'A',firstName: 'A', lastName: 'A', password: 'A', email: 'A' };
	    	
	    	/*
	    	$http.get('/ReimbursementApplication/api/v1/reimbursements?employeeId=1&status=PENDING')
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.pendingReimbursements = response.data;
	    		});	
	    	*/
	    		
	    	/*
	    	$http.get('/ReimbursementApplication/api/v1/reimbursements?employeeId=1&status=APPROVED')
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.resolvedReimbursements = response.data;
	    		});
	    	*/
	    	
	    	$scope.downloadImage = function(e) {
        		let targetName = e.target.name;
        		
        		/*
        		$http.get('/ReimbursementApplication/api/v1/reimbursements/' + targetName)
        			.then(function(response) {
        				console.log(response);
        			});
        		*/
        };
	    	
});