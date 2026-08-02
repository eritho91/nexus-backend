package se.iths.erikthorell.nexusnewdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeDto;
import se.iths.erikthorell.nexusnewdemo.service.EmployeeService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getEmployeesShouldReturnEmployees() throws Exception{
        EmployeeDto employeeDto = new EmployeeDto(
                1L,
                "Erik",
                "Thorell",
                "eritho",
                "USER",
                List.of()
        );
        List<EmployeeDto> employeeList = List.of(employeeDto);

        when(employeeService.getAllEmployees())
                .thenReturn(employeeList);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("eritho"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getEmployeeShouldReturnEmployee() throws Exception{
        EmployeeDto employeeDto = new EmployeeDto(
                1L,
                "Erik",
                "Thorell",
                "eritho",
                "USER",
                List.of()
        );

        when(employeeService.getEmployee(1L))
                .thenReturn(employeeDto);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("eritho"));

    }
}
