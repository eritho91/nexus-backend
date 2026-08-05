package se.iths.erikthorell.nexusnewdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.erikthorell.nexusnewdemo.dto.CreateEmployeeRequest;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeDto;
import se.iths.erikthorell.nexusnewdemo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                employeeService.getEmployee(id)
        );
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @RequestBody @Valid CreateEmployeeRequest request
    ) {

        return ResponseEntity.ok(
                employeeService.createEmployee(request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}