// EmployeeController.java
package com.example.employeerest.controller;

import com.example.employeerest.dto.EmployeeDTO;
import com.example.employeerest.response.Response;
import com.example.employeerest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final String SUCCESS_STATUS = "Success";
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Response> create(@Validated @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.create(employeeDTO);
        Response response = new Response();
        if (createdEmployee != null) {
            response.setCode(HttpStatus.OK);
            response.setStatus(SUCCESS_STATUS);
            response.setMessage("Employee Created Successfully");
            response.setData(List.of(createdEmployee));
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND);
            response.setStatus("failed");
            response.setMessage("Employee not created");
            return ResponseEntity.status(response.getCode()).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAll() {
        List<EmployeeDTO> employees = employeeService.getAll();
        Response response = new Response();
        response.setCode(HttpStatus.OK);
        response.setStatus(SUCCESS_STATUS);
        response.setMessage("Retrieved all employees successfully");
        response.setData(List.of(employees));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.update(updatedEmployeeDTO, id);

        Response response = new Response();
        if (updatedEmployee != null) {
            response.setCode(HttpStatus.OK);
            response.setStatus(SUCCESS_STATUS);
            response.setMessage("Employee updated successfully");
            response.setData(List.of(updatedEmployee));
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND);
            response.setStatus("failed");
            response.setMessage("Employee not found or not updated");
            return ResponseEntity.status(response.getCode()).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        employeeService.delete(id);
        Response response = new Response();
        response.setCode(HttpStatus.NO_CONTENT);
        response.setStatus(SUCCESS_STATUS);
        response.setMessage("Employee deleted successfully");
        return ResponseEntity.status(response.getCode()).body(response);
    }
}