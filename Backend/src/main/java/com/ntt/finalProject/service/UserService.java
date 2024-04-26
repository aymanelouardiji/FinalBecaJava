package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.User;
import com.ntt.finalProject.model.UserRegistrationRequest;
import com.ntt.finalProject.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, Long>{
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	protected JpaRepository<User, Long> getRepository() {
		// TODO Auto-generated method stub
		return userRepository;
	}
	
	// Enregistrer un nouveau encadrant 
    public User registerUser(UserRegistrationRequest request) {
    	
    	User user = new User();
    	
    	user.setUsername(request.getUsername());
    	
    	
    	user.setEmail(request.getEmail());
    	
    	String encodedPassword = getEncodedPassword(request.getPassword());
    	user.setPassword(encodedPassword);
    	
    	
		//Set<Role> roles = new HashSet<>();
    	//Role encadrantRole = roleDao.findByRoleName("encadrant");
    	//roles.add(encadrantRole);
    	//user.setRoles(roles);
    	
    	User savedUser = userRepository.save(user);
    	
    	return savedUser;
    }
	
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	public long getNumberOfUsers() {
		return userRepository.count();
	}
	
	public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	
	

}
