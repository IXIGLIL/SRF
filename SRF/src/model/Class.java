package model;

import java.io.Serializable;
import java.util.Set;


public class Class implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String classNo;
	private String className;
	private Set Tran;
	
	public Class() {}

	public String getClassNo()
	{
		return classNo;
	}

	public void setClassNo(String classNo)
	{
		this.classNo = classNo;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public Set getTran()
	{
		return Tran;
	}

	public void setTran(Set tran)
	{
		Tran = tran;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}