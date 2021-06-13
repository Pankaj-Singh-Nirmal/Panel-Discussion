package pl.panel.discussion.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.panel.discussion.dao.UserDao;
import pl.panel.discussion.model.UserDTO;

@Component
public class RegistrationUserIdValidator implements Validator
{
	@Autowired
	UserDao userDao;
	
	@Override
	public boolean supports(Class<?> clazz) 
	{
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		String userIdObject = ((UserDTO) target).getUserId();
		
		if(userIdObject.isEmpty())
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "errors.userId-empty");
		else if(userIdObject.length() > 45)
			errors.rejectValue("userId", "errors.userId-length");
		else if(userDao.userIdExists(userIdObject))
			errors.rejectValue("userId", "errors.userId-exists");
	}
}
