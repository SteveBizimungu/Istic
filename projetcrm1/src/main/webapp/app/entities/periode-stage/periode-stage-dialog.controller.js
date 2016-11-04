(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('PeriodeStageDialogController', PeriodeStageDialogController);

    PeriodeStageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PeriodeStage', 'Stage'];

    function PeriodeStageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PeriodeStage, Stage) {
        var vm = this;

        vm.periodeStage = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.stages = Stage.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.periodeStage.id !== null) {
                PeriodeStage.update(vm.periodeStage, onSaveSuccess, onSaveError);
            } else {
                PeriodeStage.save(vm.periodeStage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('projetcrm1App:periodeStageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.perDebut = false;
        vm.datePickerOpenStatus.perFin = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
