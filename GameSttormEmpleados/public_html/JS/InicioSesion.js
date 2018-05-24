var app = angular.module('inicioSesion', []);
app.controller('sesionController', function ($scope,$http) {


$scope.clicIniciarSesion = function (){

$http.get("http://localhost:8080/GameStorm/webresources/modelo.cuenta/" + $scope.nombreUsuario + "/" + $scope.claveUsuario)
        .then(function (response) {

        
        
        });
};
});
