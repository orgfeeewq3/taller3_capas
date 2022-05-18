package com.douglashdezt.library.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglashdezt.library.models.dtos.MessageDTO;
import com.douglashdezt.library.models.dtos.UserInfo;
import com.douglashdezt.library.models.entities.User;
import com.douglashdezt.library.services.UserService;

@RestController
@RequestMapping("/auth")
//TODO: la ruta es en request mapping y no en rest controller
public class AuthController {
	
	@Autowired
	UserService userService;
 
	@PostMapping("/signup")
	public ResponseEntity<MessageDTO> registerUser(@Valid UserInfo userInfo, BindingResult result) {
		try {
			if(result.hasErrors()) {
				String errors = result.getAllErrors().toString();
						
				
				return new ResponseEntity<>(
						new MessageDTO("Hay errores: " + errors),
						HttpStatus.BAD_REQUEST
					);
			}
			
			User foundUser = userService
					.findOneByUsernameAndEmail(userInfo.getUsername(), userInfo.getEmail());
			
			if(foundUser != null) {
				return new ResponseEntity<>(
						new MessageDTO("Este usuario ya existe"),
						HttpStatus.BAD_REQUEST
					);
			}
			
			userService.register(userInfo);
			
			return new ResponseEntity<>(
					new MessageDTO("Usuario Registrado"),
					HttpStatus.CREATED
				);
		} catch (Exception e) {
			return new ResponseEntity<>(
						new MessageDTO("Error interno"),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
}
