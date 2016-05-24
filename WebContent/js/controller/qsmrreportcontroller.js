/**
 * @Created By- Dharmendra Kumar Kaushal
 * @Date - 23-May-2016
 */

QSMRApp.service('qsmrreportservice', ['$http', function($http) {
    var qsmrreport = this;
    qsmrreport.getResponseService = function(url) {
        return $http.get('api/rest' + url);
    };
}]);

QSMRApp.controller('qsmrreportcontroller', ['$scope', 'qsmrreportservice', function($scope, qsmrreportservice){

    // create a message to display in our view
    console.log('Controller is invoked..........');
    
    //scope function to retrieve Project Name List
    $scope.getProjectNameList = function(){
    	console.log('Entered in GET PROJECT NAME LIST');
    	
    	$('#showReportsBtn').hide();
    	    	
    	qsmrreportservice.getResponseService('/projectnamelist')
    	.success(function(response){
    		console.log('It is Success '+JSON.stringify(response));
    		$scope.ProjectNames = response;
    	})
    	.error(function(error, status){
    		console.log('Error :- '+error);
    		console.log('Status :- '+status);
    	});
    };
    
    //Scope watch Function to Retrieve Date List
    $scope.$watch('projectname', function(newVal){
    	console.log('Entered in ProjectName to GET Date LIST '+newVal);
    	
    	$('#showReportsBtn').hide();
    	$scope.Dates = null;
    	
    	if(newVal){
        	qsmrreportservice.getResponseService('/datelist/projectname/'+$scope.projectname+'')
        	.success(function(response){
        		console.log('It is Success '+JSON.stringify(response));
        		$scope.Dates = response;
        	})
        	.error(function(error, status){
        		console.log('Error :- '+error);
        		console.log('Status :- '+status);
        	});
    	}
    });
    
    $scope.$watch('date', function(newVal){
    	console.log('Entered in Date LIST '+newVal);
    	
    	if(newVal){
    		$('#showReportsBtn').show();
    	}
    	else{
    		$('#showReportsBtn').hide();
    	}
    });
    
    $scope.showReports = function(){
    	console.log('Entered in showReports');
    	
    	qsmrreportservice.getResponseService('/generatereport/projectname/'+$scope.projectname+'/date/'+$scope.date)
    	.success(function(response){
    		console.log('It is Success '+JSON.stringify(response));
    	})
    	.error(function(error, status){
    		console.log('Error :- '+error);
    		console.log('Status :- '+status);
    	});
    }
}]);