package model;

import java.io.Serializable;


public class Status implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String statusNo;
	private String statusName;
	
	public Status() {}

	public String getStatusNo()
	{
		return statusNo;
	}

	public void setStatusNo(String statusNo)
	{
		this.statusNo = statusNo;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}