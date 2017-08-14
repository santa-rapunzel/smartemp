'use strict';

angular.module('smartEmp')
  .controller('fileUploadController', fileUploadController);

fileUploadController.$inject = ['employeeService', '$location'];

function fileUploadController(employeeService, $location){
  var vmUpload = this;
  vmUpload.upload = upload;


  init();

  function init(){
  }

  function upload(){
    employeeService.uploadData(vmUpload.file)
      .success(function(response){
        $location.path("/list");
      });
  }
}

