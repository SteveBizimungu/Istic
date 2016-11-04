/**
 * Created by steve on 01/11/16.
 */


(function() {
    'use strict';

    angular
        .module('projetcrmApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('perioderep',{
                parent:'mailing' ,
                url:'/periode',
                data:{
                    authorities:['ROLE_USER'],
                    pageTitle:'RepEtudiant'
                },
                views:{
                    'content@':{
                        templateurl:'app/mailing/repertoires/repertoire_etudiant/periode-rep.html',
                        controller:'PeriodeRep',
                        controlleAs:'vm'
                    }
                },
                resolve:{

                }
            })
            .state('etudiantrep', {
                parent: 'mailing',
                url: '/repetudiants',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RepEtudiants'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/mailing/repertoires/repertoire_etudiant/repertoire-etudiant.html',
                        controller: 'RepEtudiantController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                }
            })
            .state('repetudiant-mail', {
                parent: 'entity',
                url: '/repetudiant/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'RepEtudiant'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/etudiant/repetudiant-mail.html',
                        controller: 'EtudiantDetailController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Etudiant', function($stateParams, Etudiant) {
                        return Etudiant.get({id : $stateParams.id}).$promise;
                    }],
                    previousState: ["$state", function ($state) {
                        var currentStateData = {
                            name: $state.current.name || 'etudiant',
                            params: $state.params,
                            url: $state.href($state.current.name, $state.params)
                        };
                        return currentStateData;
                    }]
                }
            })




    }

})();

