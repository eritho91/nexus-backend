package se.iths.erikthorell.nexusnewdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.erikthorell.nexusnewdemo.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

}