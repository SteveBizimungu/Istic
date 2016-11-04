(function() {
    'use strict';
    angular
        .module('projetcrm1App')
        .factory('Stage', Stage);

    Stage.$inject = ['$resource', 'DateUtils'];

    function Stage ($resource, DateUtils) {
        var resourceUrl =  'api/stages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.stageAnnee = DateUtils.convertLocalDateFromServer(data.stageAnnee);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.stageAnnee = DateUtils.convertLocalDateToServer(copy.stageAnnee);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.stageAnnee = DateUtils.convertLocalDateToServer(copy.stageAnnee);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
