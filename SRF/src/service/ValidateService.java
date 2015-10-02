package service;

import model.User;

public interface ValidateService
{
	public boolean hasUserNo (String userNo);
	public boolean hasUserPwd (String userNo, String userPwd);
}
