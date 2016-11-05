/**
 * Created by steve on 01/11/16.
 */
(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .controller('RepertoireEtudiantController', RepertoireEtudiantController);

    RepertoireEtudiantController.$inject = ['$scope', '$state', 'RepertoireEtudiant','previousState'];

    function RepertoireEtudiantController ($scope, $state, RepertoireEtudiant) {
        var vm = this;
        vm.previousState = previousState.name;
        vm.etudiants = [];

        loadAll();

        function loadAll() {
            RepEtudiant.query({periode:$scope.repertoireetudiant},function(result) {
                vm.etudiants = result;
            });
        }


    }
})();
