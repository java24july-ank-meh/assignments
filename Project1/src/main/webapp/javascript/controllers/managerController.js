app.controller('managerController', 
	function($scope, $http, $rootScope, $location) {
	    	
		$scope.employees = [];
	    	
	    	$scope.reimbursements = [];
	    	
	   
	    	$http.get('/ReimbursementApplication/api/v1/employees')
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.employees = response.data;
	    		});	
	    	
	    	$http.get('/ReimbursementApplication/api/v1/reimbursements')
    			.then(function(response) {
    				console.log(response.data);
    				$scope.reimbursements = response.data;
    		});	
	    	
	    	$scope.employeeInfo = function(e){ 
	    		$rootScope.employee = JSON.parse(e.target.name);
	    		console.log(e.target.name);
	    		$location.path("/employee_page");
	    	};
	    	
	    	$scope.managerViewState = 'allEmployees';
	    		
	    	$scope.showEmployees = function() {

            $scope.managerViewState = 'allEmployees';
        };

        $scope.showReimbursements = function() {
            $scope.managerViewState = 'allReimbursements';
        };
        
	    	$scope.downloadImage = function(e) {
	    		let targetName = e.target.name;
	    
	    		$http({
	    	        method: 'GET',
	    	        url: '/ReimbursementApplication/api/v1/reimbursements/' + targetName,
	    	        responseType: 'blob'
	    	    }).then(function(response){
	    	    	
	    	    		// Open up the modal
	    	    		$('#reimbursementImage').modal('show');  		
	    	    	
	    	    		let fileReader = new FileReader();    	    		
	
	    	    		fileReader.onload = function(event) {
	    	    			$scope.outImage = fileReader.result;
	    	    			$scope.$apply();
	    	    		}
	    	    		
	    	    		fileReader.readAsDataURL(response.data)
	        	    		        	    	
	    	    }, function(data){
	    	        console.log("Some Error!!")
	    	    });
	    		
	    };
	        
        $scope.resolveReimbursement = function(e, isAccepted) {
        	        		
        		let targetName = e.target.name;
        		
        		$scope.reimbursements = $scope.reimbursements.filter(function(values) {
        			return values.rId !== parseInt(targetName); 
        		});
        		
        		$http.put('/ReimbursementApplication/api/v1/reimbursements?rId=' + targetName + '&accepted='+ isAccepted)
        			.then(function(response) {
        				console.log("Successful image resolve");
        			}, function(response) {
        				console.log("Unsuccessful image resolve");
        			});      	        		
        }; 
        
     
        
	    	
});