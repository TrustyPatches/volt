angular.module('app', []);

(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$http'];

    function LoginController($http) {
        var vm = this;

        // Fields
        vm.formEmail = "";
        vm.formPassword = "";

        // Functions
        vm.submitForm = submitForm;
        vm.logout = logout;

        function submitForm() {
            $http.post('api/login', { 'email': vm.formEmail, 'password': vm.formPassword });
        }

        function logout() {
            $http.post('api/logout');
        }

    }
})();

(function () {
    'use strict';

    angular
        .module('app')
        .controller('SignUpController', SignUpController);

    SignUpController.$inject = ['$http'];

    function SignUpController($http) {
        var vm = this;

        // Fields
        vm.formEmail = "";
        vm.formPassword = "";
        vm.formPasswordRepeat = "";

        // Functions
        vm.submitForm = submitForm;

        function submitForm() {
            $http.post('api/signup', { 'email': vm.formEmail, 'password': vm.formPassword });
        };
    }
})();
