package service.impl;

import model.User;
import service.ValidateService;
import dao.ValidateDAO;

public class ValidateServiceImpl implements ValidateService
{
	ValidateDAO validateDAO;

	public void setValidateDAO (ValidateDAO validateDAO)
	{
		this.validateDAO = validateDAO;
	}
		
	public boolean hasUserNo (String userNo)
	{
		if (validateDAO.hasUserNo(userNo).size() > 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean hasUserPwd (String userNo, String userPwd)
	{
		if (validateDAO.hasUserPwd(userNo, userPwd).size() > 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
