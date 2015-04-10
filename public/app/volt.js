angular.module('app', []);

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
