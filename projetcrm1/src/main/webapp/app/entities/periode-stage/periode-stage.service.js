(function() {
    'use strict';
    angular
        .module('projetcrm1App')
        .factory('PeriodeStage', PeriodeStage);

    PeriodeStage.$inject = ['$resource', 'DateUtils'];

    function PeriodeStage ($resource, DateUtils) {
        var resourceUrl =  'api/periode-stages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.perDebut = DateUtils.convertDateTimeFromServer(data.perDebut);
                        data.perFin = DateUtils.convertDateTimeFromServer(data.perFin);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
