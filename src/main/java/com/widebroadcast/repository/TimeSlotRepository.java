package com.widebroadcast.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widebroadcast.entity.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
	
	Optional<TimeSlot> findBySlotDateTime(LocalDateTime dateTime);
	

}
