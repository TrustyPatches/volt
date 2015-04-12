(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$http'];

    function LoginController($http) {
        var vm = this;

        // Fields
        vm.formUsername = "";
        vm.formPassword = "";
        vm.loginReturnValue = false;

        // Functions
        vm.submitForm = submitForm;
        vm.logout = logout;

        function submitForm() {
            $http.post('api/login', { 'username': vm.formUsername, 'password': vm.formPassword })
                .success(function(data) {
                    vm.loginReturnValue = data;
                });
        }

        function logout() {
            $http.post('api/logout');
        }

    }
})();
