package se.iths.erikthorell.nexusnewdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.erikthorell.nexusnewdemo.dto.CreateShiftRequest;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;
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
                .orElseThrow();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow();


        shift.getEmployees().add(employee);

        return shiftMapper.toDto(
                shiftRepository.save(shift)
        );
    }


    public ShiftDto removeEmployeeFromShift(
            Long shiftId,
            Long employeeId
    ) {

        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow();


        shift.getEmployees()
                .removeIf(employee ->
                        employee.getId().equals(employeeId)
                );


        return shiftMapper.toDto(
                shiftRepository.save(shift)
        );
    }
}