package model;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String videoNo;
	private Date videoDate;
	private String videoContact;
	private int videoPlace;
	
	public Video() {}

	public String getVideoNo()
	{
		return videoNo;
	}

	public void setVideoNo(String videoNo)
	{
		this.videoNo = videoNo;
	}

	public Date getVideoDate()
	{
		return videoDate;
	}

	public void setVideoDate(Date videoDate)
	{
		this.videoDate = videoDate;
	}

	public String getVideoContact()
	{
		return videoContact;
	}

	public void setVideoContact(String videoContact)
	{
		this.videoContact = videoContact;
	}

	public int getVideoPlace()
	{
		return videoPlace;
	}

	public void setVideoPlace(int videoPlace)
	{
		this.videoPlace = videoPlace;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}