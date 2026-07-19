package se.iths.erikthorell.nexusnewdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);

}