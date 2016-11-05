/**
 * Created by steve on 01/11/16.
 */

(function() {
    'use strict';
    angular
        .module('projetcrm1App')
        .factory('RepEtudiant', RepEtudiant);

    RepEtudiant.$inject = ['$resource', 'DateUtils'];

    function RepEtudiant ($resource, DateUtils) {
        var resourceUrl =  'api/repetudiants/:periode';

        return $resource(resourceUrl, {periode:'periode'}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.etuPremiereInscription = DateUtils.convertLocalDateFromServer(data.etuPremiereInscription);
                        data.etuAnneeCourante = DateUtils.convertLocalDateFromServer(data.etuAnneeCourante);
                    }
                    else{
                        data =[

                            { etuName : "Toto",
                                etuPrenom : "Sebatian",
                                etuMail :"toto.sebastian@rennes.fr"

                            },

                            { etuName : "Pierre",
                                etuPrenom : "Bismut",
                                etuMail :"pierre.bismut@rennes.fr"

                            }

                        ]
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
            }
        });
    }
})();
