(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('StageDetailController', StageDetailController);

    StageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Stage', 'Etudiant', 'PeriodeStage', 'Enseignant', 'Entreprise', 'Contact'];

    function StageDetailController($scope, $rootScope, $stateParams, previousState, entity, Stage, Etudiant, PeriodeStage, Enseignant, Entreprise, Contact) {
        var vm = this;

        vm.stage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('projetcrm1App:stageUpdate', function(event, result) {
            vm.stage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
