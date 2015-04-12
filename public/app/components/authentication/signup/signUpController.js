(function () {
    'use strict';

    angular
        .module('app')
        .controller('SignUpController', SignUpController);

    SignUpController.$inject = ['$http'];

    function SignUpController($http) {
        var vm = this;

        // Fields
        vm.formUsername = "";
        vm.formPassword = "";
        vm.formPasswordRepeat = "";

        // Functions
        vm.submitForm = submitForm;

        function submitForm() {
            $http.post('api/signup', { 'username': vm.formUsername, 'password': vm.formPassword });
        };
    }
})();
