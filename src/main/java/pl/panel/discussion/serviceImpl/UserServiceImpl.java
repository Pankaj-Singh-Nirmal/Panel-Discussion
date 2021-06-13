package pl.panel.discussion.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.panel.discussion.dao.UserDao;
import pl.panel.discussion.model.UserDTO;
import pl.panel.discussion.service.UserService;

@Service
public class UserServiceImpl  implements UserService
{
	@Autowired
	UserDao userDao;
	
	@Override
	public void insertUser(UserDTO userDTO) 
	{
		userDao.insertUser(userDTO);
	}
	
	@Override
	public void insertUserIntoUsersAndAuthorities(String username, String password) 
	{
		userDao.insertUserIntoUsersAndAuthorities(username, password);
	}
	
	@Override
	public List<UserDTO> selectUser(String userId) 
	{
		return userDao.selectUser(userId);
	}
	
	@Override
	public Boolean userIdExists(String userId)  
	{
		return userDao.userIdExists(userId);
	}
	
	@Override
	public Boolean passwordMatch(String userId, String password)  
	{
		return userDao.passwordMatch(userId, password);
	}
}
