package com.wipro.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.constant.EndPointURLConstant;
import com.wipro.service.EmployeeService;
import com.wipro.transferobject.AppResponse;
import com.wipro.transferobject.EmployeeTO;
import com.wipro.transferobject.NameTO;

@RestController()
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceImpl;

    @RequestMapping(value = EndPointURLConstant.EMP_CREATE, method = RequestMethod.POST)
    public ResponseEntity<AppResponse> create(@RequestBody EmployeeTO employee) {
        AppResponse appResponse = new AppResponse();
        EmployeeTO res = employeeServiceImpl.create(employee);
        appResponse.setSuccess(res);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }

    @RequestMapping(value = EndPointURLConstant.EMP_GET_ALL, method = RequestMethod.GET)
    public ResponseEntity<AppResponse> getAll() {
        AppResponse appResponse = new AppResponse();
        List<EmployeeTO> res = employeeServiceImpl.getAll();
        appResponse.setSuccess(res);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }

    @RequestMapping(value = EndPointURLConstant.EMP_UPDATE, method = RequestMethod.POST)
    public ResponseEntity<AppResponse> update(@RequestBody EmployeeTO employee) {
        AppResponse appResponse = new AppResponse();
        EmployeeTO res = employeeServiceImpl.update(employee);
        appResponse.setSuccess(res);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }

    @RequestMapping(value = EndPointURLConstant.EMP_DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<AppResponse> delete(@PathVariable String empId) {
        AppResponse appResponse = new AppResponse();
        boolean res = employeeServiceImpl.delete(empId);
        appResponse.setSuccess(res);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }

    @RequestMapping(value = EndPointURLConstant.EXPORT, method = RequestMethod.GET)
    public void getLicenseDocument(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = createSpreadSheet();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=EMPLOYEES.xlsx");
        workbook.write(response.getOutputStream());
    }

    private XSSFWorkbook createSpreadSheet() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("EMPLOYEES");
        List<EmployeeTO> list = employeeServiceImpl.getAll();
        int rowIndex = 0;
        for (EmployeeTO emp : list) {
            XSSFRow row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(emp.getName().getFirstName());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(emp.getName().getLastName());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(emp.getDepartment());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(emp.getDesignation());
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(emp.getSalary().toPlainString());
        }
        return workbook;
    }

    @RequestMapping(value = EndPointURLConstant.UPLOAD)
    public ResponseEntity<AppResponse> upload(@RequestParam @Valid MultipartFile file) throws IOException {
        List<EmployeeTO> list = new ArrayList<>();
        AppResponse appResponse = new AppResponse();
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (row.getRowNum() != 0) {
                EmployeeTO data = populateData(row);
                if (!StringUtils.isEmpty(data.getName().getFirstName())) {
                    list.add(employeeServiceImpl.create(data));
                }
            }
        }
        appResponse.setSuccess(list);
        return new ResponseEntity<AppResponse>(appResponse, HttpStatus.OK);
    }

    private EmployeeTO populateData(Row row) {
        EmployeeTO employee = new EmployeeTO();
        NameTO name = new NameTO();
        employee.setName(name);
        if (row.getCell(0) != null) {
            name.setFirstName(row.getCell(0).getStringCellValue());
            name.setLastName(row.getCell(1).getStringCellValue());
            employee.setDepartment(row.getCell(2).getStringCellValue());
            employee.setDesignation(row.getCell(3).getStringCellValue());
            employee.setSalary(new BigDecimal(row.getCell(4).getNumericCellValue()));

        }
        return employee;
    }
}
