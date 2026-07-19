package se.iths.erikthorell.nexusnewdemo.mapper;

import org.mapstruct.Mapper;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeDto;
import se.iths.erikthorell.nexusnewdemo.dto.EmployeeSummaryDto;
import se.iths.erikthorell.nexusnewdemo.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    EmployeeSummaryDto toSummaryDto(Employee employee);

}