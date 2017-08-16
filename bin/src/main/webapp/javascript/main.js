var app = angular.module("ReimbursementApp", ["ngRoute"]);


app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "main.html" 	
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
