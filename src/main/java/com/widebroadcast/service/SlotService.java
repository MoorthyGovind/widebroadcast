package com.widebroadcast.service;

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;

public interface SlotService {
	
	ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto);

}
