'use strict';
var App = angular.module('managerRouter',['ui.router']);

App.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        // For any unmatched url, send to /home
        $urlRouterProvider.otherwise("/home")
        
        $stateProvider
            .state('home', {
              url: "/home",
              template: "<h2>Welcome to the Manager Homepage</h2>"
            })

             .state('account', {
              url: "/account",
              templateUrl: "MR_managerAccount.jsp"
            })

            .state('pending', {
              url: "/pending",
              templateUrl: "MR_pending.jsp"
            })



            .state('resolved', {
              url: "/resolved",
              templateUrl: "MR_resolved.jsp"
            })


            .state('employees', {
              url: "/employees",
              templateUrl: "MR_employees.jsp"
            })
      }]);
