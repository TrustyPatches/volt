angular.module('app', []);

(function() {
  'use strict';

  angular
    .module('app')
    .controller('LoginController', LoginController);

  LoginController.$inject = [];
  
  function LoginController() {
    var vm = this;

  }
})();

(function () {
    angular
        .module('app')
        .controller('SignUpController', SignUpController);

    SignUpController.$inject = ['$http'];

    function SignUpController($http) {
        var vm = this;

        vm.firstName = "Boogaloo";

        vm.formEmail = "";
        vm.formPassword = "";
        vm.formPasswordRepeat = "";

        vm.submitForm = submitForm;

        function submitForm() {
            $http.post('api/signup', { 'email': vm.formEmail, 'password': vm.formPassword });
        }
    }
})();
