package model;

import java.io.Serializable;
import java.util.Set;

public class Dept implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int deptNo;
	private String deptName;
	private Set User;
	
	public Dept() {}

	public int getDeptNo()
	{
		return deptNo;
	}

	public void setDeptNo(int deptNo)
	{
		this.deptNo = deptNo;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public Set getUser()
	{
		return User;
	}

	public void setUser(Set user)
	{
		User = user;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}