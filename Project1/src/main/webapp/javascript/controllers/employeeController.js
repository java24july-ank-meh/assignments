app.controller('employeeController', 
	function($scope, $http, $rootScope) {
		
	    	$scope.pendingReimbursements = [];
	    	$scope.resolvedReimbursements = [];
	    	$scope.profile = $rootScope.employee;
	    	
	    	console.log($rootScope.employee);
	    	
	    	let pendingURL = '/ReimbursementApplication/api/v1/reimbursements?employeeId=' + $rootScope.employee.userId + '&status=PENDING';
	    	console.log(pendingURL);
	    	$http.get(pendingURL)
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.pendingReimbursements = response.data;
	    			console.log($scope.pendingReimbursements);
	    		});	
	    		
	    	let approvedURL = '/ReimbursementApplication/api/v1/reimbursements?employeeId=' + $rootScope.employee.userId + '&status=DENIED';
	    	console.log(approvedURL);
	    	$http.get(approvedURL)
	    		.then(function(response) {
	    			console.log(response.data);
	    			$scope.resolvedReimbursements = response.data;
	    		}, function(response) {
	    			console.log("Unsuccessful get request");
	    		});
	    	
	    	$scope.updateProfile = function(e) {
	    		$('#employeeProfile').modal('hide');
	    		$('body').removeClass('modal-open');
	    		$('.modal-backdrop').remove();
	    		
    			// use $.param jQuery function to serialize data from JSON
            let data = $.param({
            	   id: $rootScope.employee.userId,
                username: $scope.profile.username,
                password: $scope.profile.password,
                fName: $scope.profile.firstName,
                lName: $scope.profile.lastName,
                email: $scope.profile.eMail
            });
        
            let config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
            console.log(data);
            $http.post('/ReimbursementApplication/api/v1/employees', data, config)
            		.then(function (response) {
            			// user the success here
            			console.log(data);
            			console.log("Successful change")
            			console.log(response.data);
            		
            		}, function (response) {
            			console.log("Unsuccessful change");
            		});
	    	}
	    	
	    	$scope.reimbursementForm = function() {
	    		
	    		$('#newReimbursement').modal('hide');
	    		$('body').removeClass('modal-open');
	    		$('.modal-backdrop').remove();
	    		
	    		let reader = new FileReader();
	    		let imageFile = $scope.reimbursement.image.files[0];
	    		
	    		
	    		reader.onloadend = function(e) {
	    			console.log("Load image file !!!")
	    			console.log(e.target.result)
	    			// use $.param jQuery function to serialize data from JSON 
                let data = {
                	 	receipt: e.target.result, // The uploaded file
                };
            
                let config = {
                    headers : {
                        'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryGEkh1Qx1AuFwurd5'
                    }
                }

                let idStr = 'employeeId=' + $rootScope.employee.userId;
                let amountStr = '&amount=' + $scope.reimbursement.amount;
                let descriptionStr = '&description=' + $scope.reimbursement.description;
                let typeStr =  '&type=' + $scope.reimbursement.type;
                
                let finalURL = '/ReimbursementApplication/api/v1/reimbursements?' + idStr + amountStr + descriptionStr + typeStr;
                $http.post(finalURL, data, config)
                		.then(function (response) {
                			// user the success here
                			console.log("Successful upload")
                			console.log(response.data);
                		
                		}, function (response) {
                			console.log("Unsuccessful upload");
                		});
	    		}
	    		
	    		// Read the file that we uploaded
	    		reader.readAsArrayBuffer(imageFile);
	 
	    	};
	    	
	    $scope.submitReimbursement = function(e) {
	    		
	    		e.preventDefault();
	    	
	    		$('#newReimbursement').modal('hide');
	    		$('body').removeClass('modal-open');
	    		$('.modal-backdrop').remove();
	    			    		
	    		 // Get form
	        var form = $('#reimbursementForm')[0];
	
			// Create an FormData object
	        var data = new FormData(form);
	
	        $.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "/ReimbursementApplication/api/v1/reimbursements",
	            data: data,
	            processData: false,
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            success: function (data) {
	
	            		console.log("SUCCESS : ", data);
	
	            },
	            error: function (e) {
	
	                console.log("ERROR : ", e);
	
	            }
	        });
	    		
	    }
        
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
	    		
	    		$scope.pendingReimbursements = $scope.pendingReimbursements.filter(function(values) {
	    			
	    			if (values.rId === parseInt(targetName)) {
	    				
	    	    			$scope.resolvedReimbursements.push(values);
	    	    			return false;
	    				
	    			} else {
	    				return true;
	    			}; 
	    		});
	    			    		
	    		$http.put('/ReimbursementApplication/api/v1/reimbursements?rId=' + targetName + '&accepted='+ isAccepted)
	    			.then(function(response) {
	    				console.log("Successful image resolve");
	    			}, function(response) {
	    				console.log("Unsuccessful image resolve");
	    			});      	        		
	    }; 
	    
});