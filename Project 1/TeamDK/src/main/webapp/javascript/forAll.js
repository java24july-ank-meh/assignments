/**
 * 
 */

angular.module('reimbWebSite', []);

angular.module('reimbWebSite').controller('navMCtrl', function($scope,$http) {

	$scope.maninfo = function() {
		$http({
			method : 'GET',
			url : 'manager',
			params: {"link":"info"}		
		});
	};



});

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


//

angular.module('reimbWebSite').controller('photoCtrl',	function photoCtrl($scope, $http/*, FileUploader*/) {
	$scope.inputtedNumber;
	$scope.imageUpload;

//	$scope.uploadPhotoF = function() {
//		var fd = new FormData();
//		let image = $scope.imageUpload;
//		fd.append('file',image);
//
//
//		let numberRID = $scope.inputtedNumber; 
//	
//		let rData = {"reimb_id":numberRID};
//		alert(rData);
//		alert(JSON.stringify(rData));
//		
//		fd.append('rDataStringify',JSON.stringify(rData));
//
//		alert("fd "+fd);
//
//		$http.post('uploadPhoto', fd, {
//            transformRequest: angular.identity,
//            headers: { 'Content-Type': undefined }
//		}).success(function(status,response) {
////			alert(status);
//			console.log('success', response);
//		}).error(function(status, error) {
////			alert(status + "/ " + error);
//			 console.log('error', response);
//		});
//
//	};
	
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
