package model;

import java.io.Serializable;
import java.util.Set;

public class Attach implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String tranNo;
	private String fileName;
	
	public Attach() {}

	public String getTranNo()
	{
		return tranNo;
	}

	public void setTranNo(String tranNo)
	{
		this.tranNo = tranNo;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}