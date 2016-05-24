/**
 * @Created By- Dharmendra Kumar Kaushal
 * @Date - 23-May-2016
 */

QSMRApp.service('qsmrreportservice', ['$http', function($http) {
    var qsmrreport = this;
    qsmrreport.getResponseListService = function(url) {
        return $http.get('api/rest' + url);
    };
}]);

QSMRApp.controller('qsmrreportcontroller', ['$scope', 'qsmrreportservice', function($scope, qsmrreportservice){

    // create a message to display in our view
    console.log('Controller is invoked..........');
    
    $scope.message = 'This is report controller';
    
    $scope.ProjectName = null;
    $scope.date = null;
    
    //scope function to retrieve Project Name List
    $scope.getProjectNameList = function(){
    	console.log('Entered in GET PROJECT NAME LIST');
    	
    	$scope.projectNameList = null;
    	$scope.dateList = null;
    	
    	qsmrreportservice.getResponseListService('/projectnamelist')
    	.success(function(response){
    		console.log('It is Success '+JSON.stringify(response));
    		$scope.projectNameList = response;
    	})
    	.error(function(error, status){
    		console.log('Error :- '+error);
    		console.log('Status :- '+status);
    	});
    };
    
    //Scope watch Function to Retrieve Date List
    $scope.$watch('pNameList', function(newVal){
    	console.log('Entered in GET Date LIST '+newVal);
    	$scope.dateList = null;
    	
    	if(newVal){
        	qsmrreportservice.getResponseListService('/projectname/'+$scope.pNameList+'/datelist')
        	.success(function(response){
        		console.log('It is Success '+JSON.stringify(response));
        		$scope.dateList = response;
        	})
        	.error(function(error, status){
        		console.log('Error :- '+error);
        		console.log('Status :- '+status);
        	});
    	}
    });
    
}]);