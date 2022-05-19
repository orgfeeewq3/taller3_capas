package com.douglashdezt.library.models.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
	@NotBlank
	private String identifier;
	
	@NotBlank
	private String password;

	public String getIdentifier() {
		return identifier;
	}

	public String getPassword() {
		return password;
	}

}


