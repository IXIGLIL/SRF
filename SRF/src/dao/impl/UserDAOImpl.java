package dao.impl;

import java.util.List;

import model.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.UserDAO;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO
{
	public List<User> findUser (String userNo)
	{
		return (List<User>) super.getHibernateTemplate().find("from User a where userNo = '" + userNo + "' order by userNo");
	}
	
	public List<String> findSupervisor (int dept)
	{
		return super.getHibernateTemplate().find("select userName from User where userDept = " + dept + " order by userNo ");
	}
	
	public void register(User user)
	{
		super.getHibernateTemplate().save(user);
	}
	
	public void updateHadApply(String userNo, String type)
	{
		User user = getHibernateTemplate().get(User.class, userNo);
		
		if("plus".equals(type))
		{
			user.setHadApply(user.getHadApply()+1);
		}
		
		if("minus".equals(type))
		{
			user.setHadApply(user.getHadApply()-1);
		}
		
		getHibernateTemplate().update(user);
	}
	
	public void updateWaitApprove(String userNo, String type)
	{
		User user = getHibernateTemplate().get(User.class, userNo);
		
		if ("plus".equals(type))
		{
			user.setWaitApprove(user.getWaitApprove()+1);
		}
		
		if("minus".equals(type))
		{
			user.setWaitApprove(user.getWaitApprove()-1);
		}
		
		getHibernateTemplate().update(user);
	}
	
	public void updateWaitDone(String userNo, String type)
	{
		User user = getHibernateTemplate().get(User.class, userNo);
		
		if("plus".equals(type))
		{
			user.setWaitDone(user.getWaitDone()+1);
		}
		
		if("minus".equals(type))
		{
			user.setWaitDone(user.getWaitDone()-1);
		}
		
		getHibernateTemplate().update(user);
	}
	
	public void updateWaitCheck(String userNo, String type)
	{
		User user = getHibernateTemplate().get(User.class, userNo);
		
		if("plus".equals(type))
		{
			user.setWaitCheck(user.getWaitCheck()+1);
		}
		
		if("minus".equals(type))
		{
			user.setWaitCheck(user.getWaitCheck()-1);
		}
		
		getHibernateTemplate().update(user);
	}
	
	public void updateBeenReject(String userNo, String type)
	{
		User user = getHibernateTemplate().get(User.class, userNo);
		
		if("plus".equals(type))
		{
			user.setBeenReject(user.getBeenReject()+1);
		}
		
		if("minus".equals(type))
		{
			user.setBeenReject(user.getBeenReject()-1);
		}
		
		getHibernateTemplate().update(user);
	}
}	