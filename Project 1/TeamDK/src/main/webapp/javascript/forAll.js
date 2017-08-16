/**
 * 
 */

angular.module('reimbWebSite', []);



angular.module('reimbWebSite').controller('userICtrl', function($scope,$http) {

	$scope.user = 
		 { uID: 100,
			firstName: 'John',
			middleInitial: 'M',
			lastName: 'Doe',
			email: 'myemail@mail.com',
			roleID: 1,
			numOfReimb: 10
		 };
	
});

angular.module('reimbWebSite').controller('notAuth', function($scope,$http){
	
	$scope.login = function() {
		$http({
			method : 'GET',
			url : 'login',
			params: {"link":"needauth"}		
		});
	};
	
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
