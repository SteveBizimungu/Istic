(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('ContactDetailController', ContactDetailController);

    ContactDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Contact', 'Stage', 'Entreprise'];

    function ContactDetailController($scope, $rootScope, $stateParams, previousState, entity, Contact, Stage, Entreprise) {
        var vm = this;

        vm.contact = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('projetcrm1App:contactUpdate', function(event, result) {
            vm.contact = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
