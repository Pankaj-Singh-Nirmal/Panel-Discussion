package pl.panel.discussion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.panel.discussion.model.UserDTO;
import pl.panel.discussion.service.UserService;
import pl.panel.discussion.validators.RegistrationConfirmPasswordValidator;
import pl.panel.discussion.validators.RegistrationFirstNameValidator;
import pl.panel.discussion.validators.RegistrationLastNameValidator;
import pl.panel.discussion.validators.RegistrationPasswordValidator;
import pl.panel.discussion.validators.RegistrationUserIdValidator;

@Controller
public class LoginRegisterController 
{
	@Autowired
	UserService userService;
	
	@Autowired
	RegistrationUserIdValidator registrationUserIdValidator;
	
	@Autowired
	RegistrationFirstNameValidator registrationFirstNameValidator;
	
	@Autowired
	RegistrationLastNameValidator registrationLastNameValidator;
	
	@Autowired
	RegistrationPasswordValidator registrationPasswordValidator;
	
	@Autowired
	RegistrationConfirmPasswordValidator registrationConfirmPasswordValidator;
	
	@GetMapping("/")
	public String loadRegisterUserPage(@ModelAttribute("userInfo") UserDTO userDTO)
	{
		return "registerUser-page";
	}
	
	@PostMapping("/processUserRegistration")
	public String processUserRegistrationPage(@Valid @ModelAttribute("userInfo") UserDTO userDTO,
											  BindingResult result, RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError err : allErrors) 
			{
				System.out.println(err);
			}
			return "registerUser-page";
		}
		
		String plainTextPassword = userDTO.getPassword();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String bcryptPassword = encoder.encode(plainTextPassword);
		userDTO.setPassword(bcryptPassword);
		
		userService.insertUser(userDTO);
		userService.insertUserIntoUsersAndAuthorities(userDTO.getUserId(), userDTO.getPassword());
		
		redirectAttributes.addFlashAttribute("registrationSuccess", "Registration Success !");
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loadLoginPage()
	{
		return "login-page";
	}
	
	@InitBinder("userInfo")
	public void initBinderRegisterUserValidator(WebDataBinder binder) 
	{
		binder.addValidators(registrationUserIdValidator);
		binder.addValidators(registrationFirstNameValidator);
		binder.addValidators(registrationLastNameValidator);
		binder.addValidators(registrationPasswordValidator);
		binder.addValidators(registrationConfirmPasswordValidator);
	}
}
