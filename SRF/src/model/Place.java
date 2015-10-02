package model;

import java.io.Serializable;
import java.util.Set;


public class Place implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int placeNo;
	private String placeName;
	private Set Video;
	
	public Place() {}

	public int getPlaceNo()
	{
		return placeNo;
	}

	public void setPlaceNo(int placeNo)
	{
		this.placeNo = placeNo;
	}

	public String getPlaceName()
	{
		return placeName;
	}

	public void setPlaceName(String placeName)
	{
		this.placeName = placeName;
	}

	public Set getVideo()
	{
		return Video;
	}

	public void setVideo(Set video)
	{
		Video = video;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}