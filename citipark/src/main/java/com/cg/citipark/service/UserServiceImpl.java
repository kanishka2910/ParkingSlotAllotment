package com.cg.citipark.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.citipark.beans.User;
import com.cg.citipark.exceptions.DuplicateUserException;
import com.cg.citipark.exceptions.NoSuchUserFoundException;
import com.cg.citipark.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User addUser(User user) {
		User registerUser = userRepository.findByEmail(user.getEmail());
		if(registerUser==null)
		{
			return userRepository.save(user);
		}
		else
			throw new DuplicateUserException("User with email : " + user.getEmail()
					+ " already exists");
		
	}
	@Override
    public User getUserByEmail(String email) {
		
		User user= userRepository.findByEmail(email);
		 if (user == null) 
	 		{
	 			throw new NoSuchUserFoundException("User with email: "+email+" does not exist");
	 		}
	 		return user;
	    }
     
	@Override
    public User getUserById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) 
		{
			throw new NoSuchUserFoundException("User with id: "+userId+" does not exist");
		}
		return user.get();
	}
	
	@Override
	public boolean deleteUserById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent())
			throw new NoSuchUserFoundException("User with id: "+userId+" does not exists");
		userRepository.deleteById(userId);
		return true;
	}
	@Override
     public User updateUser(User user) {
 		User updateUser = userRepository.findById(user.getUserId()).get();
 		if(updateUser!= null)
 			return userRepository.save(user);
 		return null;
 	}
	
    
 	

}

