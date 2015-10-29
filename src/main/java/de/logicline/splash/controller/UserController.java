package de.logicline.splash.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.logicline.splash.dto.ContactDto;
import de.logicline.splash.model.ContactEntity;
import de.logicline.splash.model.UserEntity;
import de.logicline.splash.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReCaptchaImpl reCaptcha;

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userLogin(
			@RequestBody final UserEntity userEntity,
			HttpServletResponse response) {
		UserEntity userEntityTmp = userService.getUserByNameAndPassword(
				userEntity.getUsername(), userEntity.getPassword());
		if (userEntityTmp == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		Map<String, String> responseMap = new HashMap<String, String>();
		String token = userEntityTmp.getToken();
		responseMap.put("token", token);
		responseMap.put("userId", String.valueOf(userEntityTmp.getUserId()));
		responseMap.put("role", userEntityTmp.getRole());

		ContactEntity contactEntity = userService.getContact(token);
		if (contactEntity != null) {
			responseMap.put("firstName", contactEntity.getFirstName());
			responseMap.put("lastName", contactEntity.getLastName());
		}

		return responseMap;
	}

	@RequestMapping(value = "/user/clogin", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userLoginWithCaptcha(
			@RequestBody Map<String, String> userInput,
			HttpServletRequest request, HttpServletResponse response) {

		String username = userInput.get("username");
		String password = userInput.get("password");
		String challenge = userInput.get("recaptcha_challenge_field");
		String uresponse = userInput.get("recaptcha_response_field");

		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
				request.getRemoteAddr(), challenge, uresponse);

		if (reCaptchaResponse.isValid()) {
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return null;
		}

		UserEntity userEntityTmp = userService.getUserByNameAndPassword(
				username, password);
		if (userEntityTmp == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		// TODO merge with normal login
		Map<String, String> responseMap = new HashMap<String, String>();
		String token = userEntityTmp.getToken();
		responseMap.put("token", token);
		responseMap.put("userId", String.valueOf(userEntityTmp.getUserId()));
		responseMap.put("role", userEntityTmp.getRole());

		ContactEntity contactEntity = userService.getContact(token);
		if (contactEntity != null) {
			responseMap.put("firstName", contactEntity.getFirstName());
			responseMap.put("lastName", contactEntity.getLastName());
		}

		return responseMap;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public @ResponseBody String createUserInfoById(
			@RequestBody final ContactDto contactDto,
			HttpServletRequest request, HttpServletResponse response) {

		String password = userService.createUser(contactDto);

		return password;
	}

	@RequestMapping(value = "/user/edit/password/{userId}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> updateUserPassword(
			@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> responseMap = new HashMap<String, String>();
		UserEntity userEntity = userService.updatePassword(userId);

		responseMap.put("username", userEntity.getUsername());
		responseMap.put("password", userEntity.getPassword());
		return responseMap;
	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	public @ResponseBody ContactDto getUserInfoByUserId(
			@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {

		ContactEntity contactEntity = userService.getContactByUserId(userId);
		if (contactEntity == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}

		return contactEntity.toDto();
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	public @ResponseBody Map<Integer, String> getAllCustomer(
			HttpServletRequest request, HttpServletResponse response) {

		Map<Integer, String> customerIdMap = userService.getCustomerIdMap();

		return customerIdMap;
	}

	@RequestMapping(value = "/user/search/{name}", method = RequestMethod.GET)
	public @ResponseBody Map<Integer, String> searchUserByName(
			@PathVariable("name") String name, HttpServletRequest request,
			HttpServletResponse response) {

		Map<Integer, String> nameMap = userService.searchUserByName(name);

		return nameMap;
	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.PUT)
	public void updateUserInfoById(@PathVariable("userId") Integer userId,
			@RequestBody final ContactDto contactDto,
			HttpServletRequest request, HttpServletResponse response) {

		userService.updateUserInfoByUserId(userId, contactDto);
		return;
	}

}
