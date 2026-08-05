package se.iths.erikthorell.nexusnewdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.service.ShiftService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShiftController.class)
public class ShiftControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ShiftService shiftService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getShiftsShouldReturnShifts() throws Exception{
        LocalDateTime shiftTIme = LocalDateTime.of(2026, 8, 12, 7, 10);

        ShiftDto shiftDto = new ShiftDto(
                1L,
                "Bromma",
                shiftTIme,
                List.of()
        );
        List<ShiftDto> allShifts = List.of(shiftDto);
        when(shiftService.getAllShifts())
                .thenReturn(allShifts);
        mockMvc.perform(get("/shifts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]location").value("Bromma"));
    }
}
