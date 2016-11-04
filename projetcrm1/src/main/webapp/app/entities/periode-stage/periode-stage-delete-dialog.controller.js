(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('PeriodeStageDeleteController',PeriodeStageDeleteController);

    PeriodeStageDeleteController.$inject = ['$uibModalInstance', 'entity', 'PeriodeStage'];

    function PeriodeStageDeleteController($uibModalInstance, entity, PeriodeStage) {
        var vm = this;

        vm.periodeStage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PeriodeStage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
