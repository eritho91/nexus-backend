package se.iths.erikthorell.nexusnewdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.dto.CreateEmployeeRequest;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.mapper.EmployeeMapper;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;


    public List<EmployeeDto> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }


    public EmployeeDto getEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return employeeMapper.toDto(employee);
    }


    public EmployeeDto createEmployee(CreateEmployeeRequest request) {

        Employee employee = new Employee();

        employee.setFirstName(
                request.firstName()
        );

        employee.setLastName(
                request.lastName()
        );

        employee.setUsername(
                generateUsername(
                        request.firstName(),
                        request.lastName()
                )
        );

        employee.setPassword(
                passwordEncoder.encode(
                        request.password()
                )
        );

        employee.setRole("USER");


        Employee saved =
                employeeRepository.save(employee);


        return employeeMapper.toDto(saved);
    }


    private String generateUsername(String firstName, String lastName) {

        return firstName.substring(0,3).toLowerCase()
                +
                lastName.substring(0,3).toLowerCase();
    }
}