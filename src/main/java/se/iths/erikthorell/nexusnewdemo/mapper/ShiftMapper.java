package se.iths.erikthorell.nexusnewdemo.mapper;

import org.mapstruct.Mapper;
import se.iths.erikthorell.nexusnewdemo.dto.ShiftDto;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;

@Mapper(
        componentModel = "spring",
        uses = EmployeeMapper.class
)
public interface ShiftMapper {

    ShiftDto toDto(Shift shift);

}