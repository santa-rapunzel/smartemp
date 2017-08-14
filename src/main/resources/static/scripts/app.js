'use strict';

/**
 * @ngdoc overview
 * @name santaApp
 * @description
 * # santaApp
 *
 * Main module of the application.
 */
angular
  .module('smartEmp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngFileUpload'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html'
      })
      .when('/list', {
        templateUrl: 'views/create-employee.html',
        controller: 'employeeController',
        controllerAs: 'empCtrl'
      })
      .when('/upload', {
        templateUrl: 'views/file-upload.html',
        controller: 'fileUploadController',
        controllerAs: 'vmUpload'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
