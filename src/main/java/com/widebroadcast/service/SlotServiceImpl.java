package com.widebroadcast.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.entity.Slot;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.repository.SlotRepository;
import com.widebroadcast.repository.TimeSlotRepository;

@Service
public class SlotServiceImpl implements SlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	SlotRepository slotRepository;

	@Override
	public ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) {
		if (!timeSlotRepository.findBySlotDateTime(createSlotRequestDto.getSlotFromDateTime()).isPresent()) {

		}
		PlanType plantype = new PlanType();
		plantype.setPlanTypeId(createSlotRequestDto.getPlanTypeId());
		Slot slot = new Slot();
		slot.setPlantype(plantype);
		slot.setProgramName(createSlotRequestDto.getProgrammeName());
		slot.setStatus("AVAILABLE");
		slot = slotRepository.save(slot);
		List<TimeSlot> timeSlots = new ArrayList<>();
		while (createSlotRequestDto.getSlotFromDateTime()
				.isBefore(createSlotRequestDto.getSlotToDateTime().plusSeconds(1))) {
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setSlot(slot);
			timeSlot.setSlotDateTime(createSlotRequestDto.getSlotFromDateTime());
			timeSlot.setStatus("AVAILABLE");
			createSlotRequestDto.setSlotFromDateTime(createSlotRequestDto.getSlotFromDateTime().plusSeconds(1));
			timeSlots.add(timeSlot);
		}
		timeSlotRepository.saveAll(timeSlots);
		return new ResponseDto();
	}

}
