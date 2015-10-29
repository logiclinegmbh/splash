package de.logicline.splash.service;

import java.util.List;
import java.util.Map;

import de.logicline.splash.dto.ContactDto;
import de.logicline.splash.model.ContactEntity;
import de.logicline.splash.model.UserEntity;

public interface UserService {

	public UserEntity getUserByNameAndPassword(String username, String password);

	public String createUser(ContactDto contactDto);

	public List<ContactEntity> getContactList();

	public ContactEntity getContact(String token);

	public ContactEntity getContactByUserId(Integer userId);

	public void updateUserInfoByUserId(Integer userId, ContactDto contactDto);

	public UserEntity updatePassword(Integer userId);

	public Map<Integer, String> getCustomerIdMap();

	public Map<Integer, String> searchUserByName(String name);

}
