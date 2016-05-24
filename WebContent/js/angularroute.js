/**
 * @Created By - Dharmendra Kumar Kaushal
 * @Date - 23-May2016
 */

// angularroute.js

// create the module and name it scotchApp
var QSMRApp = angular.module('QSMRApp', ['ngRoute']);

//configure our routes
QSMRApp.config(function($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : 'views/pages/home.html',
            controller  : 'maincontroller'
        })
        
        // route for the about page
        .when('/about', {
            templateUrl : 'views/pages/about.html',
            controller  : 'aboutController'
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'views/pages/contact.html',
            controller  : 'contactController'
        })
        
        //route for QSMR Form Page
        .when('/qsmrform',{
        	templateUrl : 'views/pages/qsmrform.html',
        	controller : ''
        })
        
        //route for QSMR Report
        .when('/qsmrreport',{
        	templateUrl : 'views/pages/qsmrreport.html',
        	controller : 'qsmrreportcontroller'
        })
        
        .otherwise({
            redirectTo: '/'
      });
});

// create the controller and inject Angular's $scope
/*QSMRApp.controller('mainController', function($scope) {

    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});*/

QSMRApp.controller('aboutController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});

QSMRApp.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});