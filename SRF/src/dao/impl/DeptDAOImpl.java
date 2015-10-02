package dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.DeptDAO;

public class DeptDAOImpl extends HibernateDaoSupport implements DeptDAO
{
	public List findDept ()
	{
		return (List<String>) this.getHibernateTemplate().find("from Dept order by deptNo");
	}
}	