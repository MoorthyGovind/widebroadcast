package com.widebroadcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.service.SlotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/slots")
@CrossOrigin
@Slf4j
public class SlotController {

	@Autowired
	SlotService slotService;

	@PostMapping
	public ResponseEntity<ResponseDto> createSlot(@RequestBody CreateSlotRequestDto createSlotRequestDto)
			throws SlotNotAvailableException {
		log.info("SlotController createSlot ---> creatinf slots ");
		ResponseDto responseDto = slotService.createSlot(createSlotRequestDto);
		responseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		responseDto.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok().body(responseDto);
	}

}
