package com.example.demo.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserInformation;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> response = userRepo.findAll();

		List<UserDTO> allUsers = new LinkedList<>();

		response.stream().forEach(o -> {
			UserDTO user = new UserDTO();

			user.setHttpStatus(Integer.toString(o.getId()));
			user.setUserName(o.getFirstName());

			allUsers.add(user);

		});

		return allUsers;
	}

	@Override
	public UserDTO getAllUsersByName(String name) {

		Optional<User> userOptional = userRepo.findByFirstName(name);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found with name = " + name);
		}

		return mapEntityToDTO(userOptional.get());
	}

	@Override
	public UserDTO saveUser(UserInformation user) {

		User userEntity = new User();

		userEntity.setFirstName(user.getUserName());
		userEntity.setLastName(user.getAddress());

		User savedUser = userRepo.save(userEntity);

		return mapEntityToDTO(savedUser);
	}

	private UserDTO mapEntityToDTO(User savedUser) {

		UserDTO dto = new UserDTO();

		dto.setUserName(savedUser.getFirstName());
		dto.setHttpStatus(Integer.toString(savedUser.getId()));

		return dto;
	}

}