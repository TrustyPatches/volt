(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfileController', ProfileController);

    ProfileController.$inject = ['$http'];

    function ProfileController($http) {
        var vm = this;

        // Fields
        vm.username = "";
        vm.loggedin = false;

        // Functions
        vm.isLoggedIn = isLoggedIn;

        activate();

        function activate() {
            isLoggedIn();
            getUsername();
        }

        function getUsername() {
            $http.get('api/profile/username').success(function(data) { vm.username = data; });
        }

        function isLoggedIn() {
            $http.get('api/profile/loggedin').success(function(data) { vm.loggedIn = data; });
        }

    }
})();
