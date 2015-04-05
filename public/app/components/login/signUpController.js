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
