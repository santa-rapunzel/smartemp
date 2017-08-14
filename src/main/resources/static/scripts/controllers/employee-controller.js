'use strict';

angular.module('smartEmp')
  .controller('employeeController', employeeController);

employeeController.$inject = ['employeeService', 'constants'];

function employeeController(employeeService, constants){
  var vmEmp = this;

  vmEmp.empList = [];
  vmEmp.create = create;
  vmEmp.getAll = getAll;
  vmEmp.deleteEmp = deleteEmp;
  vmEmp.updateEmp = updateEmp;
  vmEmp.populateForEdit = populateForEdit;
  vmEmp.isShowCreate = false;
  vmEmp.showCreate = showCreate;
  vmEmp.cancel = cancel;
  vmEmp.downloadURL = constants.REST_URL + "emp/export/";
  init();

  function init(){
    getAll();
  }

  function showCreate(){
    vmEmp.emp = {};
    vmEmp.isShowCreate = true;
  }


  function getAll() {
    employeeService.getAll().then(function(response){
      vmEmp.empList = response.success;
      });
  }


  function cancel() {
    vmEmp.isShowEdit = false;
    vmEmp.isShowCreate = false;
  }

  function create() {
    employeeService.createEmp(vmEmp.emp).then(function(response){
      vmEmp.empList.push(response.success);
      vmEmp.isShowCreate = false;
    });
  }

  function deleteEmp(emp, index) {
    employeeService.deleteEmp(emp.empId).then(function(response){
      if(response.success){
        vmEmp.empList.splice(index, 1);
      }
    });
  }

  function updateEmp() {
    employeeService.updateEmp(vmEmp.emp).then(function(response){
      if(response.success){
        vmEmp.empList.splice(vmEmp.dataIndex, 1);
        vmEmp.empList.splice(vmEmp.dataIndex, 0, response.success);
        vmEmp.isShowEdit = false;
        vmEmp.isShowCreate = false;
      }
    });
  }

  function populateForEdit(emp, index){
    vmEmp.dataIndex = index;
    vmEmp.emp = angular.copy(emp);
    vmEmp.isShowEdit = true;
  }
}
