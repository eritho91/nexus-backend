package se.iths.erikthorell.nexusnewdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin.password}")
    private String adminPassword;


    @Override
    public void run(String... args) {

        if (employeeRepository.findByUsername("admin").isEmpty()) {

            Employee admin = new Employee();

            admin.setFirstName("Erik");
            admin.setLastName("Thorell");
            admin.setUsername("admin");
            admin.setPassword(
                    passwordEncoder.encode(adminPassword)
            );
            admin.setRole("ADMIN");

            employeeRepository.save(admin);
        }
    }
}