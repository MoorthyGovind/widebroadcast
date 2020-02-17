package com.widebroadcast.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.entity.Slot;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.repository.SlotRepository;
import com.widebroadcast.repository.TimeSlotRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	SlotRepository slotRepository;

	@Override
	public ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) throws SlotNotAvailableException {
		log.info("SlotServiceImpl createSlot ---> creating slots");
		if (timeSlotRepository.findBySlotDateTime(createSlotRequestDto.getSlotFromDateTime()).isPresent()) {
			log.info("SlotServiceImpl createSlot ---> SlotNotAvailableException occured");
			throw new SlotNotAvailableException(AppConstant.SLOT_ALREADY_CREATED);
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
		log.info("SlotServiceImpl createSlot ---> slots created");
		return new ResponseDto();
	}

}
