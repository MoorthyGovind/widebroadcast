package com.widebroadcast.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.entity.User;
import com.widebroadcast.exception.UserNotFoundException;
import com.widebroadcast.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * UserServiceImpl Class - We are using this class for user activities of user
 * login purpose. As a user can login with phone number and password. A login
 * can access for admin and sales manager roles.
 * 
 * @author Govindasamy.C
 * @since 17-02-2020
 * @version V1.1
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * As a user can login with phone number and password for login purpose. A login
	 * can access for admin and sales manager roles.
	 * 
	 * @param loginRequestDto - details of the username and password.
	 * @return - return the values of the userId, name and role for user.
	 * @author Govindasamy.C
	 * @throws UserNotFoundException
	 * @since 17-02-2020
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		log.info("user login based on the user name and password...");
		Optional<User> user = userRepository.findByPhoneNumberAndPassword(loginRequestDto.getPhoneNumber(),
				loginRequestDto.getPassword());
		if (!user.isPresent()) {
			throw new UserNotFoundException(AppConstant.USER_NOT_FOUND);
		}
		log.info("setting the responsse details of the user login...");
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		BeanUtils.copyProperties(user.get(), loginResponseDto);
		log.info("return the response details in user login...");
		return loginResponseDto;
	}

}
