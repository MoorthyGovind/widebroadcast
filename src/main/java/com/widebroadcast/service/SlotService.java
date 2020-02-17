package com.widebroadcast.service;

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;

public interface SlotService {
	
	ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) throws SlotNotAvailableException;

}
