
var coursedetailModule = angular.module('courseDetailModule', []);
    // Get course details
coursedetailModule.controller("courseDetail", function ($scope, $http) {
    $http.get('http://localhost:8080/StudentCourseRegistrationSystem/rest/courses/1')
         .success(function (data,status,headers,config) 
        {
          $scope.responses = data;
        })
         .error(function(data,status,headers,config)
         {
        	 alert("Error while getting the course details" +status);
        	 alert(JSON.stringify(data));
        	 $scope.responses = data;
         });
    // Get course schedules
    $http({
		method : 'GET',
		url : 'http://localhost:8080/StudentCourseRegistrationSystem/rest/courses/1/schedules'
	}).success(function(data, status, headers,config) {
												 $scope.schedules = data;
												 $scope.days = data.daysOfWeek;
												 console.log("data : "+data);
												 console.log("days : "+ data.daysOfWeek);
											}).error(
												function(data, status, headers,
												config) {
												alert("Error while getting the schedules for a particular course!");
												$scope.schedules = data;
											});
    
    $scope.selectedSchedule = [];
	$scope.toggleSelectionSchedule = function(scheduleId){
		var idx  = $scope.selectedSchedule.indexOf(scheduleId);
		if(idx>-1){
			$scope.selectedSchedule.splice(idx,1);
		}else{
			$scope.selectedSchedule.push({
				"scheduleId" : scheduleId
			});
		}
	}    
    // Enrolling for a course after selecting a schedule
	$scope.enroll= function(){
		if($scope.selectedSchedule.length < 1 ){
			alert("Note : Select a schedule!");
			return;
		}
		$http({
			method : 'POST',
			url : 'http://localhost:8080/StudentCourseRegistrationSystem/students/enroll',
			data : { 
  				"student_id" : $scope.student_id,
  				"course_id"  : $scope.course_id,
  				"schedule_id" : $scope.schedule_id,
  			},
			headers : {
				'Content-Type' : 'application/json'
			}
			
		}).success(function(data, status, headers,config) {
			 alert("Enrolled Successfully");
		}).error(
			function(data, status, headers,config) {
				alert("Error while enrolling for the course!!");
			
		});
     }
         
});