package se.iths.erikthorell.nexusnewdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService
        implements UserDetailsService {


    private final EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {


        Employee employee =
                employeeRepository.findByUsername(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "User not found"
                                )
                        );


        return User.builder()

                .username(employee.getUsername())

                .password(employee.getPassword())

                .authorities(
                        new SimpleGrantedAuthority(
                                "ROLE_" + employee.getRole()
                        )
                )

                .build();
    }
}