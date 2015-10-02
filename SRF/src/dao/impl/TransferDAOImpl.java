package dao.impl;

import java.util.List;

import model.Phone;
import model.Video;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.TransferDAO;

public class TransferDAOImpl extends HibernateDaoSupport implements TransferDAO
{
	public String deptNameToNo (String deptName)
	{
		return super.getHibernateTemplate().find("select deptNo from Dept where deptName = '" + deptName + "' ").toString();
	}
	
	public String deptNoToName (int deptNo)
	{
		return super.getHibernateTemplate().find("select deptName from Dept where deptNo = '" + deptNo + "' ").toString();
	}
	
	public String userNameToNo(int userDept, String userName)
	{
		String sql = "select userNo from User where userName = '" + userName + "' ";
		
		if(userDept != -1)
		{
			sql = sql + " and userDept = '" + userDept + "' ";
		}
		
		return super.getHibernateTemplate().find(sql).toString();
	}
	
	public String userNoToName(String userNo)
	{
		return super.getHibernateTemplate().find("select userName from User where userNo = '" + userNo + "' ").toString();
	}
	
	public String classNoToName(int classNo)
	{
		return super.getHibernateTemplate().find("select className from Class where classNo = '" + classNo + "' ").toString();
	}
	
	public String classNameToNo(String className)
	{
		return super.getHibernateTemplate().find("select classNo from Class where className = '" + className + "' ").toString();
	}
	
	public List<Video> videoNoToDetail(String videoNo)
	{
		return super.getHibernateTemplate().find("from Video where videoNo = '" + videoNo + "' ");
	}
	
	public List<Phone> phoneNoToDetail(String phoneNo)
	{
		return super.getHibernateTemplate().find("from Phone where phoneNo ='" + phoneNo + "' ");
	}
	
	public String placeNoToName(int placeNo)
	{
		return super.getHibernateTemplate().find("select placeName from Place where placeNo = '" + placeNo + "' ").toString();
	}
	
	public String placeNameToNo(String placeName)
	{
		return super.getHibernateTemplate().find("select placeNo from Place where placeName = '" + placeName + "' ").toString();
	}
	
	public String statusNoToName(int statusNo)
	{
		return super.getHibernateTemplate().find("select statusName from Status where statusNo = '" + statusNo + "' ").toString();
	}
	
	public String statusNameToNo(String statusName)
	{
		return super.getHibernateTemplate().find("select statusNo from Status where statusName = '" + statusName + "' ").toString();
	}
	
	public String attachNoToName(String tranNo)
	{
		return super.getHibernateTemplate().find("select fileName from Attach where tranNo = '" + tranNo + "' ").toString();
	}
}	