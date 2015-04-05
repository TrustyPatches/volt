(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfileController', ProfileController);

    ProfileController.$inject = ['$http'];

    function ProfileController($http) {
        var vm = this;

    }
})();
