package model;

import java.io.Serializable;
import java.util.Date;

public class Phone implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String phoneNo;
	private Date phoneDate;
	private String phoneContact;
	
	public Phone() {}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public Date getPhoneDate()
	{
		return phoneDate;
	}

	public void setPhoneDate(Date phoneDate)
	{
		this.phoneDate = phoneDate;
	}

	public String getPhoneContact()
	{
		return phoneContact;
	}

	public void setPhoneContact(String phoneContact)
	{
		this.phoneContact = phoneContact;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}