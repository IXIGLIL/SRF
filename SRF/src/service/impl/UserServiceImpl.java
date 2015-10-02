package service.impl;

import java.util.List;

import model.User;
import service.UserService;
import dao.UserDAO;

public class UserServiceImpl implements UserService
{
	UserDAO userDAO;

	public void setUserDAO (UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public List<User> findUser (String userNo)
	{
		return userDAO.findUser(userNo);
	}

	public List<String> findSupervisor (int dept)
	{
		return userDAO.findSupervisor(dept);
	}
	
	public void register(User user)
	{
		userDAO.register(user);
	}
	
	public void updateHadApply(String userNo, String type)
	{
		userDAO.updateHadApply(userNo, type);
	}
	
	public void updateWaitApprove(String userNo, String type)
	{
		userDAO.updateWaitApprove(userNo, type);
	}
	
	public void updateWaitDone(String userNo, String type)
	{
		userDAO.updateWaitDone(userNo, type);
	}
	
	public void updateWaitCheck(String userNo, String type)
	{
		userDAO.updateWaitCheck(userNo, type);
	}
	
	public void updateBeenReject(String userNo, String type)
	{
		userDAO.updateBeenReject(userNo, type);
	}
}
