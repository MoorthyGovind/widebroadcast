package com.widebroadcast.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.repository.SlotRepository;
import com.widebroadcast.repository.TimeSlotRepository;

public class SlotServiceImpl implements SlotService {
	
	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	@Autowired
	SlotRepository slotRepository;

	@Override
	public ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) {
		return null;
	}

}
