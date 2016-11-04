(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('EnseignantDetailController', EnseignantDetailController);

    EnseignantDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Enseignant', 'Stage'];

    function EnseignantDetailController($scope, $rootScope, $stateParams, previousState, entity, Enseignant, Stage) {
        var vm = this;

        vm.enseignant = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('projetcrm1App:enseignantUpdate', function(event, result) {
            vm.enseignant = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
