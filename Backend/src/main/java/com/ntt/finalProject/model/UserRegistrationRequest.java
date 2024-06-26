package com.ntt.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserRegistrationRequest {
	private String username;
	private String email;
	private String password;
}
