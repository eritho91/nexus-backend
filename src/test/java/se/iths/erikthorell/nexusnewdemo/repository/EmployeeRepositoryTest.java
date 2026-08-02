package se.iths.erikthorell.nexusnewdemo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByUserNameShouldReturnEmployeeWhenUsernameExists(){
        Employee employee = new Employee();
        employee.setFirstName("Erik");
        employee.setLastName("Thorell");
        employee.setUsername("eritho");
        employee.setPassword("password123");
        employee.setRole("USER");

        employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findByUsername("eritho");

        assertThat(foundEmployee).isPresent();

        assertThat(foundEmployee.get().getUsername()).isEqualTo("eritho");
    }
}
