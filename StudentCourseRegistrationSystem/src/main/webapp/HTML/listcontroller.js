		var studentApp = angular.module("studentApp", []);

		studentApp
				.controller(
						'studentController',
						function($scope, $http) {
							
							$http(
									{
										method : 'GET',
										url : 'http://localhost:8080/StudentCourseRegistrationSystem/rest/courses'
									})
									.success(
											function(data, status, headers,
													config) {
												//alert("success : " + status);
												//alert("data : " + data);
												$scope.responseJson = data;
												
											}).error(
											function(data, status, headers,
													config) {
												alert("error : " + status);
												alert(JSON.stringify(data));
												$scope.responseJson = data;
											});
						});

