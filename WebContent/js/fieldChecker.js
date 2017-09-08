/**
 * 
 */
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope) {
    $scope.user = 'Enter username';
    $scope.pass = 'Enter password';
});