'use strict';

describe('Controller Tests', function() {

    describe('Stage Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockStage, MockEtudiant, MockPeriodeStage, MockEnseignant, MockEntreprise, MockContact;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockStage = jasmine.createSpy('MockStage');
            MockEtudiant = jasmine.createSpy('MockEtudiant');
            MockPeriodeStage = jasmine.createSpy('MockPeriodeStage');
            MockEnseignant = jasmine.createSpy('MockEnseignant');
            MockEntreprise = jasmine.createSpy('MockEntreprise');
            MockContact = jasmine.createSpy('MockContact');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Stage': MockStage,
                'Etudiant': MockEtudiant,
                'PeriodeStage': MockPeriodeStage,
                'Enseignant': MockEnseignant,
                'Entreprise': MockEntreprise,
                'Contact': MockContact
            };
            createController = function() {
                $injector.get('$controller')("StageDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'projetcrm1App:stageUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
