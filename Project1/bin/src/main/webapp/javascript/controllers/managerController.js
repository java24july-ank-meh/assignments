app.controller('managerController', 
	function($scope, $http) {
	    	$scope.employees = [{id: 1, firstName: 'A', lastName: 'A', eMail: 'A'},
	    		{id: 2, firstName: 'A', lastName: 'A', eMail: 'A'}];
	    	
	    	
	    	$scope.reimbursements = [{id: 1, name: 'A', amount: 100, status: 'PENDING'},
	    		{id: 2, name: 'A', amount: 100, status: 'PENDING'}];
	    	
	    	/*
	    	$http.get('/ReimbursementApplication/api/v1/employees')
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.employees = response.data;
	    		});	
	    	*/
	    	
	    	$scope.managerViewState = 'allEmployees';
	    		
	    	$scope.showEmployees = function() {

            $scope.managerViewState = 'allEmployees';
        };

        $scope.showReimbursements = function() {
            $scope.managerViewState = 'allReimbursements';
        };
        
        $scope.downloadImage = function(e) {
        		let targetName = e.target.name;
        		
        		/*
        		$http.get('/ReimbursementApplication/api/v1/reimbursements/' + targetName)
        			.then(function(response) {
        				console.log(response);
        			});
        		*/
        };
        
        $scope.resolveReimbursement = function(e, isAccepted) {
        		let targetName = e.target.name;        		
        		/*
        		$http.post('/ReimbursementApplication/api/v1/reimbursements/' + targetName + '/'+ isAccepted)
        			.then(function(response) {
        				console.log(response);
        			});
        		*/
        		$scope.reimbursements = $scope.reimbursements.filter(function(values) {
        			return values.id !== parseInt(targetName); 
        		});
        	        		
        }; 
        
	    	
});