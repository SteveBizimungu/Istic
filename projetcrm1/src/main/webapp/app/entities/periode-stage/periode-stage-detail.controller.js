(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('PeriodeStageDetailController', PeriodeStageDetailController);

    PeriodeStageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PeriodeStage', 'Stage'];

    function PeriodeStageDetailController($scope, $rootScope, $stateParams, previousState, entity, PeriodeStage, Stage) {
        var vm = this;

        vm.periodeStage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('projetcrm1App:periodeStageUpdate', function(event, result) {
            vm.periodeStage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
