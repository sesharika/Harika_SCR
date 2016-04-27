var studentRegister = angular.module('studentRegister', []);

//Consumes create Students web service
studentRegister.controller("registerController", function($scope, $http){
		   $scope.register = function() {
			   if($scope.password != $scope.confirmPassword){
				   alert("Passwords do not match. Please enter the correct password!!!");
				   $scope.password ='';
				   $scope.confirmPassword ='';
				   return;
			   }else{
		   	$http({
		   		method : 'POST',
		   		url : 'https://localhost:8443/StudentCourseRegistrationSystem/rest/students',
		   		data : {
		   			"firstName": $scope.firstName,
		   			"lastName": $scope.lastName,
		   			"emailId": $scope.email,
		   			"password": $scope.password,
		   			"userFlag" :'Student'
		   		},
		   		headers : {
					'Content-Type' : 'application/json'
				}
		   	}).success(function(data, status, headers, config){
		   		$scope.message = "Congratulations!!!! Registration Successful!!!";
		   		alert("Congratulations!!!! Registration Successful!!!");
		   		window.location.href = "https://localhost:8443/StudentCourseRegistrationSystem/#/";
		   	})
            .error(function(data, status, headers, config){
            	$scope.error=true;
            	$scope.errorMessage = data.errorMessage;
            });
			   }
		   }
		   })
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	
		   	