(function() {
    'use strict';

    angular
        .module('projetcrm1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('periode-stage', {
            parent: 'entity',
            url: '/periode-stage',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PeriodeStages'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/periode-stage/periode-stages.html',
                    controller: 'PeriodeStageController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('periode-stage-detail', {
            parent: 'entity',
            url: '/periode-stage/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PeriodeStage'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/periode-stage/periode-stage-detail.html',
                    controller: 'PeriodeStageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PeriodeStage', function($stateParams, PeriodeStage) {
                    return PeriodeStage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'periode-stage',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('periode-stage-detail.edit', {
            parent: 'periode-stage-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/periode-stage/periode-stage-dialog.html',
                    controller: 'PeriodeStageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PeriodeStage', function(PeriodeStage) {
                            return PeriodeStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('periode-stage.new', {
            parent: 'periode-stage',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/periode-stage/periode-stage-dialog.html',
                    controller: 'PeriodeStageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                perDebut: null,
                                perFin: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('periode-stage', null, { reload: 'periode-stage' });
                }, function() {
                    $state.go('periode-stage');
                });
            }]
        })
        .state('periode-stage.edit', {
            parent: 'periode-stage',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/periode-stage/periode-stage-dialog.html',
                    controller: 'PeriodeStageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PeriodeStage', function(PeriodeStage) {
                            return PeriodeStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('periode-stage', null, { reload: 'periode-stage' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('periode-stage.delete', {
            parent: 'periode-stage',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/periode-stage/periode-stage-delete-dialog.html',
                    controller: 'PeriodeStageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PeriodeStage', function(PeriodeStage) {
                            return PeriodeStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('periode-stage', null, { reload: 'periode-stage' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
