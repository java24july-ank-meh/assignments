
/*
var myApp = angular.module('employee', ['ui.router']);

myApp.config(function($stateProvider) {
  

  var pendingState = {
    name: 'pending',
    url: '/pending',
    template: '<h3>View Pending</h3>'
  }

  var resolvedState = {
    name: 'resolved',
    url: '/resolved',
    template: '<h3>View Resolved</h3>'
  }

  var newRState = {
    name: 'reimbursment',
    url: '/newReimbursment',
    template: '<h3>Create New Reimbursment</h3>'
  }

  var accountState = {
    name: 'account',
    url: '/viewAccount',
    templateUrl: 'accountStateTemplate.html'
  }


  $stateProvider.state(pendingState);
  $stateProvider.state(resolvedState);
  $stateProvider.state(newRState);
  $stateProvider.state(accountState);
});

*/
'use strict';
var App = angular.module('employeeRouter',['ui.router']);

App.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        // For any unmatched url, send to /home
        $urlRouterProvider.otherwise("/home")
        
        $stateProvider
            .state('home', {
              url: "/home",
              template: "<h2>Welcome to the Employee Homepage</h2>"
            })

             .state('account', {
              url: "/account",
              templateUrl: "ER_employeeAccount.jsp"
            })

            .state('pending', {
              url: "/pending",
              templateUrl: "ER_pending.jsp"
            })



            .state('resolved', {
              url: "/resolved",
              templateUrl: "ER_resolved.jsp"
            })


            .state('reimbursment', {
              url: "/newReimbursment",
              templateUrl: "ER_newReimbursment.jsp"
            })
      }]);
