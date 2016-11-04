(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('PeriodeStageController', PeriodeStageController);

    PeriodeStageController.$inject = ['$scope', '$state', 'PeriodeStage'];

    function PeriodeStageController ($scope, $state, PeriodeStage) {
        var vm = this;
        
        vm.periodeStages = [];

        loadAll();

        function loadAll() {
            PeriodeStage.query(function(result) {
                vm.periodeStages = result;
            });
        }
    }
})();
