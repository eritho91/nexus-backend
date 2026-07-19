package se.iths.erikthorell.nexusnewdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.erikthorell.nexusnewdemo.dto.CreateShiftRequest;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;
import se.iths.erikthorell.nexusnewdemo.service.ShiftService;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;


    @GetMapping
    public ResponseEntity<List<ShiftDto>> getShifts() {

        return ResponseEntity.ok(
                shiftService.getAllShifts()
        );
    }


    @PostMapping
    public ResponseEntity<ShiftDto> createShift(
            @RequestBody CreateShiftRequest request
    ) {

        return ResponseEntity.ok(
                shiftService.createShift(request)
        );
    }


    @PostMapping("/{shiftId}/employees/{employeeId}")
    public ResponseEntity<ShiftDto> addEmployee(
            @PathVariable Long shiftId,
            @PathVariable Long employeeId
    ) {

        return ResponseEntity.ok(
                shiftService.addEmployeeToShift(
                        shiftId,
                        employeeId
                )
        );
    }


    @DeleteMapping("/{shiftId}/employees/{employeeId}")
    public ResponseEntity<ShiftDto> removeEmployee(
            @PathVariable Long shiftId,
            @PathVariable Long employeeId
    ) {

        return ResponseEntity.ok(
                shiftService.removeEmployeeFromShift(
                        shiftId,
                        employeeId
                )
        );
    }
}