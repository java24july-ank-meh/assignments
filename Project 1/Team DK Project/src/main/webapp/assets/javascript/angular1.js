//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}


var myWebApp = angular.module('rWebSite', []); // Empty brackets for
//dependencies

myWebApp.controller('loginPart', function loginPart($scope) {
	$scope.numberOfLogins = 0;
	$scope.user = [ {
		userName : "u",
		passWord : "p"
	} ]
	/* $scope.newUser = {}; */
	$scope.person = [];

	$scope.login = function(userName, passWord) {
		$scope.user.push({
			userName : userName,
			passWord : passWord
		});
	}

});


myWebApp.controller('loginCtrl', function loginCtrl($scope,$http) {

	$scope.getAUserFromServerGet = function() {
		$http({
			method : 'GET',
			url : 'test'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};

	$scope.getAUserFromServerPost = function() {
		$http({
			method : 'Post',
			url : 'test'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};

	$scope.updateUserToServer = function() {
		let dataU = angular.toJson($scope.newUser);
		alert(dataU);

		$http({
			method : 'Post',
			url : 'user' /* ?data='+dataU */,
			data : dataU
		}).success(function(status) {
			alert(status);
		}).error(function(status, error) {
			alert(status + "/ " + error);
		});

	};

});

myWebApp.controller('photoCtrl',	function photoCtrl($scope, $http) {

	$scope.imageUpload;

	$scope.uploadPhoto = function(f) {
		var fd = new FormData();
		var imageBlob = dataURItoBlob($scope.imageUpload);

//		fd.append('image',imageBlob);
//		fd.append('id', "100000");
//		fd.append('name', "image");
		fd.append('file',imageBlob);


//		$http({
//		method : 'Post',
//		url : uploadUrl,
//		data : fd
//		}).success(function(status) {
//		alert(status);
//		}).error(function(status, error) {
//		alert(status + "/ " + error);
//		});

		$http.post('photo', fd, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
		}).success(function(status,response) {
//			alert(status);
			console.log('success', response);
		}).error(function(status, error) {
//			alert(status + "/ " + error);
			 console.log('error', response);
		});

	};
	
	function dataURItoBlob(dataURI) {
		let binary = atob(dataURI.split(',')[1]);
		let mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
		let array = [];
		for (var i = 0; i < binary.length; i++) {
			array.push(binary.charCodeAt(i));
		}
		return new Blob([new Uint8Array(array)], {
			type: mimeString
		});


	};
	
});

myWebApp.directive("photoin", [ function() {
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



myWebApp.controller('viewReimbCtrl', function viewReimbCtrl($scope,$http) {

	$scope.getAReimbFromServerGet = function() {
		$http({
			method : 'POST',
			url : 'reimb'
		}).success(function(data, status, headers, config) {
			$scope.reimb = data;
		}).error(function(data, status, headers, config) {

		});
	};


});












