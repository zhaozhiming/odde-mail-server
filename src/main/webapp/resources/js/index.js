var dba_app = angular.module('oms', ['ngRoute']);

dba_app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/send', { templateUrl: 'resources/pages/send.html', controller: SendController})
        .when('/recipients', { templateUrl: 'resources/pages/recipients.html', controller: RecipientsController})
        .otherwise({redirectTo: '/home'});
}]);

var transform = function(data){
    return $.param(data);
};

var config = {
    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
    transformRequest: transform
};

$(document).ready(function () {
    $('#sideBarMenu').click(function () {
        $('.ui.sidebar').sidebar('toggle');
    });

    $('.ui.sidebar').sidebar('toggle');
});


