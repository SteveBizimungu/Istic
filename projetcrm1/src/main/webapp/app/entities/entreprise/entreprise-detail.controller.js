(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('EntrepriseDetailController', EntrepriseDetailController);

    EntrepriseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Entreprise', 'Stage', 'Contact'];

    function EntrepriseDetailController($scope, $rootScope, $stateParams, previousState, entity, Entreprise, Stage, Contact) {
        var vm = this;

        vm.entreprise = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('projetcrm1App:entrepriseUpdate', function(event, result) {
            vm.entreprise = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
