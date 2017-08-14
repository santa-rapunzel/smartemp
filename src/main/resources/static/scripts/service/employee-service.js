(function () {
  'use strict';

  angular.module('smartEmp').service('employeeService', employeeService);

  employeeService.$inject = ['$http', 'constants', 'Upload'];

  function employeeService($http, constants, Upload){
    return {
      createEmp: createEmp,
      deleteEmp: deleteEmp,
      updateEmp: updateEmp,
      getAll: getAll,
      uploadData: uploadData,
      exportData: exportData
    }

    function getAll(){
      return $http.get(constants.REST_URL + "emp/all").then(function(response){
        return response.data;
      });
    }

    function createEmp(employee) {
        return $http.post(constants.REST_URL + "emp/create", employee)
          .then(function(response){
              return response.data;
        });
    }

    function deleteEmp(empId) {
      return $http.delete(constants.REST_URL + "emp/delete/"+empId)
        .then(function(response){
          return response.data;
        });
    }

    function updateEmp(employee) {
      return $http.post(constants.REST_URL + "emp/update", employee)
        .then(function(response){
          return response.data;
        });
    }

    function uploadData(files) {
        var promise = Upload.upload({
          url: constants.REST_URL + "emp/upload",
          data: {file: files[0]},
          arrayKey: ''
        });
      return promise;
    }

    function exportData() {
      $http.get(constants.REST_URL + "emp/export");
    }
  }
})();
