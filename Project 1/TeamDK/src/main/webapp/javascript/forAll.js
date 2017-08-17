/**
 * 
 */

angular.module('reimbWebSite', []);

angular.module('reimbWebSite').run(function($rootScope,$http){

	$rootScope.user = {};

	$http({	method : 'GET',
		url : 'user'	
	}).then(function(data){
		if(data !== null){
			alert("not null");
//			let u = angular.fromJson(data);

			$rootScope.user = data;

//			alert("duser "+data.uID);
//			alert("duser "+data.username);
//			alert("duser "+data.firstName);
//			alert("duser "+data.lastName);
//			alert("duser "+data.manager);
//			alert("duser "+data.roleID);
			
//			let u = $rootScope.user;
//			alert("user "+u.uID);
//			alert("user "+u.username);
//			alert("user "+u.firstName);
//			alert("user "+u.lastName);
//			alert("user "+u.manager);
//			alert("user "+u.roleID);
//
//			alert("rs user "+$rootScope.user);
//			alert("rs user "+$rootScope.username);
//			alert("rs user "+$rootScope.fname);
//			alert("rs user "+$rootScope.lname);
//			alert("rs user "+$rootScope.manager);
//			alert("rs user "+$rootScope.roleid);

		}
	});
}

);

angular.module('reimbWebSite').controller('userICtrl', function($scope,$http) {

});

angular.module('reimbWebSite').controller('photoCtrl',	function photoCtrl($scope, $http/*, FileUploader*/) {
	$scope.inputtedNumber;
	$scope.imageUpload;
});

angular.module('reimbWebSite').directive("photoin", [ function() {
	return {
		scope: {
			photoin: "="
		},
		link: function(scope, element, attributes) {
			//file picked in uploader, change from null to something
			element.bind("change", function(changeEvent) {
				var reader = new FileReader();
				reader.onload = function(loadEvent) {
					scope.$apply(function() {
						scope.photoin = loadEvent.target.result;
					});
				}
				reader.readAsDataURL(changeEvent.target.files[0]);
			});
		}
	}
}]);

//document.onload authenticate
