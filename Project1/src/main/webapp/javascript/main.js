var app = angular.module("ReimbursementApp", ["ngRoute"]);

app.controller("mainController", function($scope, $rootScope, $http, $location) {
	
	$rootScope.manager = undefined;
	$rootScope.employee = undefined;
	
	
	$scope.userLogin = function() {
			
		$('#loginForm').modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
		
		
		// use $.param jQuery function to serialize data from JSON 
        let data = $.param({
            username: $scope.username,
            password: $scope.password
        });
    
        let config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        $http.post('/ReimbursementApplication/api/v1/login', data, config)
        		.then(function (response) {
        			console.log("returned from form login");
        			console.log(response.data);
        			
        			if (response.data !== undefined && response.data !== null) {
        				        				
        				if (response.data.userRole === 'MANAGER') {
        					$rootScope.manager = response.data;
        					
        					$location.path("/manager_page");

        					
        				} else if (response.data.userRole === 'EMPLOYEE') {
        					$rootScope.employee = response.data;
        					              		
        					console.log($rootScope.employee);
        					
        					
        					$location.path("/employee_page");
        					
        				} else {
        					console.log ("Unsuccessful login");
        				}
        			}
        		
        		}, function (response) {
        			console.log("Unsuccessful");
        		});
	};
});

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "main.html",
        	controller: "mainController"
    })
    .when("/employee_page", {
        templateUrl : "employee.html",
        controller: "employeeController"
    })
    .when("/manager_page", {
        templateUrl : "manager.html",
        controller: "managerController"
    })
    .otherwise({
    		redirectTo: "/"
    });
});
