package dao;

import java.util.List;

import model.User;

public interface ValidateDAO
{
	public List hasUserNo (String userNo);
	public List hasUserPwd (String userNo, String userPwd);
}
