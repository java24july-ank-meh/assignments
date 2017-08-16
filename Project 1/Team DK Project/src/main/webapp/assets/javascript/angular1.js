//Check for the various File API support.
if (window.File && window.FileReader && window.Blob) {
	// Great success! All the File APIs are supported.
	alert('Supported');
} else {
	alert('The File APIs are not fully supported in this browser.');
}


var myWebApp = angular.module('rWebSite', [/*'angularFileUpload'*/]); // Empty brackets for
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

myWebApp.controller('photoCtrl',	function photoCtrl($scope, $http/*, FileUploader*/) {
	$scope.inputtedNumber;
	$scope.imageUpload;

	
//	 $scope.uploader = new FileUploader();
	
	//doesnt work
	$scope.uploadPhotoF = function() {
		var fd = new FormData();
//		var imageBlob = dataURItoBlob($scope.imageUpload);
		let image = $scope.imageUpload;
//		fd.append('image',imageBlob);
		//fd.append('id', "100002");
//		fd.append('name', "image");
		fd.append('file',image);


		let numberRID = $scope.inputtedNumber; 
	
		let rData = {"reimb_id":numberRID}
		alert(rData);
		alert(JSON.stringify(rData));
		//fd.append('rid', numberRID);
		//fd.append('rData',rData);
		fd.append('rDataStringify',JSON.stringify(rData));

//		$http({
//		method : 'Post',
//		url : uploadUrl,
//		data : fd
//		}).success(function(status) {
//		alert(status);
//		}).error(function(status, error) {
//		alert(status + "/ " + error);
//		});
		alert("fd "+fd);

		$http.post('uploadPhoto', fd, {
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
	
	$scope.downloadedImage;
	
	$scope.getAReimbFromServerGet = function() {
//		$http({
//			method : 'GET',
//			url : 'reimb'
//		}).success(function(data, status, headers, config) {
//			$scope.reimb = data;
//		}).error(function(data, status, headers, config) {
//
//		});
		
		$http({
			method : 'GET',
			url : 'photo'
		}).success(function(data, headers) {
			alert("sucess");
//			alert("headers "+headers);
//			alert("data "+data);
		
			$scope.re = data;
//			alert("re "+$scope.re.receipt);
			
			$scope.downloadedImage = "data:image/png;base64,"+ $scope.re.receipt;
//			var blob = new Blob([data]);
//			var blobURL = URL.createObjectURL(blob);
//			$scope.receipt = $sce.trustAsResourceUrl(blobURL);
			//$scope.receipt = data;
		}).error(function(data, status, headers, config) {
			alert("error");
		});
		
	};

	
});

myWebApp.controller('ReimbursementCtrl', function ReimbursementCtrl($scope,$http) {

	$scope.getReimbursementFromServerGet = function() {
		$http({
			method : 'Post',
			url : '/reimbursementList'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};

	$scope.updateRiembursementServerGet = function() {
		$http({
			method : 'Get',
			url : '/UpdateServlet'
		}).success(function(data, status, headers, config) {
			$scope.person = data;
		}).error(function(data, status, headers, config) {

		});
	};





});


