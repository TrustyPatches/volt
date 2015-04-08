(function () {
    'use strict';

    angular
        .module('app')
        .controller('AdminUserController', AdminUserController);

    AdminUserController.$inject = ['$http'];

    function AdminUserController($http) {
        var vm = this;

        // Fields
        vm.userList = [];

        // Functions
        vm.getUserList = getUserList;

        activate();

        function activate() {
            getUserList();
        }

        function getUserList() {
            $http.get('api/admin/userlist')
                .success(function (data) {
                    vm.userList = data;
                });
        }
    }
})();
