package se.iths.erikthorell.nexusnewdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import se.iths.erikthorell.nexusnewdemo.dto.CreateShiftRequest;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeSummaryDto;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;
import se.iths.erikthorell.nexusnewdemo.mapper.ShiftMapper;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;
import se.iths.erikthorell.nexusnewdemo.repository.ShiftRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ShiftRepository shiftRepository;
    @Mock
    private ShiftMapper shiftMapper;
    @Mock
    private ShiftBookedProducer shiftBookedProducer;

    @InjectMocks
    private ShiftService shiftService;

    @Test
    void createShiftShouldSaveAndReturnShift(){
        LocalDateTime shiftTime =
                LocalDateTime.of(2026, 8, 3, 7, 30);
        Shift shift = new Shift();
        shift.setId(1L);
        shift.setLocation("Bromma");
        shift.setShiftTime(shiftTime);

        ShiftDto expectedResponse = new ShiftDto(
                1L,
                "Bromma",
                shiftTime,
                List.of()
        );

        CreateShiftRequest request = new CreateShiftRequest(
                "Bromma",
                shiftTime.toLocalDate(),
                shiftTime.toLocalTime()
        );

        when(shiftRepository.save(any(Shift.class)))
                .thenReturn(shift);

        when(shiftMapper.toDto(shift))
                .thenReturn(expectedResponse);

        ShiftDto result = shiftService.createShift(request);

        assertEquals(expectedResponse, result);
    }

    @Test
    void getAllShiftsShouldReturnAllShifts(){
        LocalDateTime shiftTime =
                LocalDateTime.of(2026, 8, 3, 7, 30);
        Shift shift = new Shift();
        shift.setId(1L);
        shift.setLocation("Bromma");
        shift.setShiftTime(shiftTime);

        ShiftDto expectedResponse = new ShiftDto(
                1L,
                "Bromma",
                shiftTime,
                List.of()
        );

        when(shiftRepository.findAll())
                .thenReturn(List.of(shift));

        when(shiftMapper.toDto(shift))
                .thenReturn(expectedResponse);

        List<ShiftDto> expectedList = List.of(expectedResponse);
        List<ShiftDto> actualList = shiftService.getAllShifts();

        assertEquals(expectedList, actualList);
    }

    @Test
    void addEmployeeToShiftShouldAddEmployeeAndReturnShift(){
        LocalDateTime shiftTime =
                LocalDateTime.of(2026, 8, 3, 7, 30);
        Shift shift = new Shift();
        shift.setId(1L);
        shift.setLocation("Bromma");
        shift.setShiftTime(shiftTime);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Erik");
        employee.setLastName("Thorell");
        employee.setUsername("eritho");
        employee.setRole("USER");

        when(shiftRepository.findById(1L))
                .thenReturn(Optional.of(shift));
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeSummaryDto employeeSummaryDto =
                new EmployeeSummaryDto("Erik", "Thorell");


        ShiftDto expectedResponse = new ShiftDto(
                1L,
                "Bromma",
                shift.getShiftTime(),
                List.of(employeeSummaryDto)
        );

        when(shiftRepository.save(any(Shift.class)))
                .thenReturn(shift);

        when(shiftMapper.toDto(shift))
                .thenReturn(expectedResponse);

        ShiftDto actualResponse = shiftService.addEmployeeToShift(shift.getId(), employee.getId());

        assertEquals(expectedResponse, actualResponse);
        verify(shiftBookedProducer).sendShiftBookedMessage(any());
    }

    @Test
    void removeEmployeeFromShiftShouldRemoveEmployeeAndReturnShift(){
        LocalDateTime shiftTime =
                LocalDateTime.of(2026, 8, 3, 7, 30);
        Shift shift = new Shift();
        shift.setId(1L);
        shift.setLocation("Bromma");
        shift.setShiftTime(shiftTime);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Erik");
        employee.setLastName("Thorell");
        employee.setUsername("eritho");
        employee.setRole("USER");

        shift.getEmployees().add(employee);

        when(shiftRepository.findById(1L))
                .thenReturn(Optional.of(shift));

        ShiftDto expectedResponse = new ShiftDto(
                1L,
                "Bromma",
                shift.getShiftTime(),
                List.of()
        );

        when(shiftRepository.save(any(Shift.class)))
                .thenReturn(shift);
        when(shiftMapper.toDto(shift))
                .thenReturn(expectedResponse);

        ShiftDto actualResponse = shiftService.removeEmployeeFromShift(shift.getId(), employee.getId());
        assertEquals(expectedResponse, actualResponse);

    }

    @MockitoBean
    private JavaMailSender javaMailSender;

    @Test


}
