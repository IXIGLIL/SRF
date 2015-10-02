package dao.impl;

import java.util.List;

import model.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ValidateDAO;

public class ValidateDAOImpl extends HibernateDaoSupport implements ValidateDAO
{	
	public List hasUserNo (String userNo)
	{
		return super.getHibernateTemplate().find("from User where userNo = '" + userNo + "' ");
	}
	
	public List hasUserPwd (String userNo, String userPwd)
	{
		return super.getHibernateTemplate().find("from User where userNo = '" + userNo + "' and userPwd = '" + userPwd + "' ");
	}
}	