(function() {
    'use strict';
    angular
        .module('projetcrm1App')
        .factory('Etudiant', Etudiant);

    Etudiant.$inject = ['$resource', 'DateUtils'];

    function Etudiant ($resource, DateUtils) {
        var resourceUrl =  'api/etudiants/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.etuPremiereInscription = DateUtils.convertLocalDateFromServer(data.etuPremiereInscription);
                        data.etuAnneeCourante = DateUtils.convertLocalDateFromServer(data.etuAnneeCourante);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.etuPremiereInscription = DateUtils.convertLocalDateToServer(copy.etuPremiereInscription);
                    copy.etuAnneeCourante = DateUtils.convertLocalDateToServer(copy.etuAnneeCourante);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.etuPremiereInscription = DateUtils.convertLocalDateToServer(copy.etuPremiereInscription);
                    copy.etuAnneeCourante = DateUtils.convertLocalDateToServer(copy.etuAnneeCourante);
                    return angular.toJson(copy);
                }
            },
            'getPeriode':{
                method:'GET',
                transformRequest

            }
        });
    }
})();
