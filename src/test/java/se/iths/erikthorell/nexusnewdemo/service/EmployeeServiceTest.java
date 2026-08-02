package se.iths.erikthorell.nexusnewdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.erikthorell.nexusnewdemo.dto.CreateEmployeeRequest;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.mapper.EmployeeMapper;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployeeShouldReturnEmployee(){
        CreateEmployeeRequest request =
                new CreateEmployeeRequest("eri", "tho", "password123");
        when(passwordEncoder.encode("password123"))
                .thenReturn("encoded-secret");

        Employee savedEmployee = new Employee();
        savedEmployee.setId(1L);
        savedEmployee.setFirstName("Erik");
        savedEmployee.setLastName("Thorell");
        savedEmployee.setUsername("eritho");
        savedEmployee.setPassword("encoded-secret");
        savedEmployee.setRole("USER");

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(savedEmployee);

        EmployeeDto expectedDto = new EmployeeDto(
                1L,
                "Erik",
                "Thorell",
                "eritho",
                "USER",
                List.of()
        );

        when(employeeMapper.toDto(savedEmployee))
                .thenReturn(expectedDto);

        EmployeeDto result = employeeService.createEmployee(request);
        assertEquals(expectedDto, result);
    }

    @Test
    void getEmployeeShouldReturnEmployeeWhenIdExists(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Erik");
        employee.setLastName("Thorell");
        employee.setUsername("eritho");
        employee.setRole("USER");

        EmployeeDto expectedDto = new EmployeeDto(
                1L,
                "Erik",
                "Thorell",
                "eritho",
                "USER",
                List.of()
        );

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(employeeMapper.toDto(employee))
                .thenReturn(expectedDto);

        EmployeeDto result = employeeService.getEmployee(1L);
        assertEquals(expectedDto, result);
    }

    @Test
    void getEmployeeShouldThrowExceptionWhenIdDoesNotExist(){
        when(employeeRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> employeeService.getEmployee(99L));
    }

    @Test
    void getAllEmployeesShouldReturnAllEmployees(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Erik");
        employee.setLastName("Thorell");
        employee.setUsername("eritho");
        employee.setRole("USER");

        EmployeeDto expectedDto = new EmployeeDto(
                1L,
                "Erik",
                "Thorell",
                "eritho",
                "USER",
                List.of()
        );

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee));

        when(employeeMapper.toDto(employee))
                .thenReturn(expectedDto);

        List<EmployeeDto> expectedResult = List.of(expectedDto);
        List<EmployeeDto> result = employeeService.getAllEmployees();
        assertEquals(expectedResult, result);
    }
}
