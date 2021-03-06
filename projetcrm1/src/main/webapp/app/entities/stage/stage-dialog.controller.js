(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('StageDialogController', StageDialogController);

    StageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Stage', 'Etudiant', 'PeriodeStage', 'Enseignant', 'Entreprise', 'Contact'];

    function StageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Stage, Etudiant, PeriodeStage, Enseignant, Entreprise, Contact) {
        var vm = this;

        vm.stage = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.etudiants = Etudiant.query();
        vm.periodestages = PeriodeStage.query();
        vm.enseignants = Enseignant.query();
        vm.entreprises = Entreprise.query();
        vm.contacts = Contact.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.stage.id !== null) {
                Stage.update(vm.stage, onSaveSuccess, onSaveError);
            } else {
                Stage.save(vm.stage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('projetcrm1App:stageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.stageAnnee = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
