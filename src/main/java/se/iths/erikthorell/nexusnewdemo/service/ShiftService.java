package se.iths.erikthorell.nexusnewdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.dto.CreateShiftRequest;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftBookedMessage;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;
import se.iths.erikthorell.nexusnewdemo.exception.ResourceNotFoundException;
import se.iths.erikthorell.nexusnewdemo.mapper.ShiftMapper;
import se.iths.erikthorell.nexusnewdemo.repository.EmployeeRepository;
import se.iths.erikthorell.nexusnewdemo.repository.ShiftRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftMapper shiftMapper;
    private final ShiftBookedProducer shiftBookedProducer;


    public List<ShiftDto> getAllShifts() {

        return shiftRepository.findAll()
                .stream()
                .map(shiftMapper::toDto)
                .toList();
    }


    public ShiftDto createShift(CreateShiftRequest request) {

        Shift shift = new Shift();

        shift.setLocation(
                request.location()
        );

        shift.setShiftTime(
                LocalDateTime.of(
                        request.date(),
                        request.time()
                )
        );


        return shiftMapper.toDto(
                shiftRepository.save(shift)
        );
    }


    public ShiftDto addEmployeeToShift(
            Long shiftId,
            Long employeeId
    ) {

        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));


        shift.getEmployees().add(employee);

        ShiftBookedMessage message = new ShiftBookedMessage(
                employee.getUsername(),
                employee.getFirstName() + " " + employee.getLastName(),
                shift.getLocation(),
                shift.getShiftTime()
        );

        shiftBookedProducer.sendShiftBookedMessage(message);

        return shiftMapper.toDto(
                shiftRepository.save(shift)
        );
    }


    public ShiftDto removeEmployeeFromShift(
            Long shiftId,
            Long employeeId
    ) {

        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found"));


        shift.getEmployees()
                .removeIf(employee ->
                        employee.getId().equals(employeeId)
                );


        return shiftMapper.toDto(
                shiftRepository.save(shift)
        );
    }
}
