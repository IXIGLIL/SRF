package service.impl;

import java.util.List;

import service.DeptService;
import dao.DeptDAO;


public class DeptServiceImpl implements DeptService
{
	DeptDAO deptDAO;

	public void setDeptDAO (DeptDAO deptDAO)
	{
		this.deptDAO = deptDAO;
	}
	
	public List<String> findDept()
	{
		return deptDAO.findDept();
	}
}
