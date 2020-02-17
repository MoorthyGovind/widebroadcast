package com.widebroadcast.service;

import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.exception.UserNotFoundException;

public interface UserService {

	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException;
}
