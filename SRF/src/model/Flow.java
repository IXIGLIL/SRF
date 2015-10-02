package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import service.TransferService;


public class Flow implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String tranNo;
	private int flowLastStatus;
	private int flowStatus;
	private String lastStatusName;
	private String statusName;
	private Date flowDate;
	private Tran tran;
	private Set Status;
	
	public Flow() {}
	
	public Flow(String tranNo, String lastStatusName, String statusName, Date flowDate)
	{
		this.tranNo = tranNo;
		this.lastStatusName = lastStatusName;
		this.statusName = statusName;
		this.flowDate = flowDate;
	}

	public String getTranNo()
	{
		return tranNo;
	}

	public void setTranNo(String tranNo)
	{
		this.tranNo = tranNo;
	}

	public int getFlowLastStatus()
	{
		return flowLastStatus;
	}

	public void setFlowLastStatus(int flowLastStatus)
	{
		this.flowLastStatus = flowLastStatus;
	}

	public int getFlowStatus()
	{
		return flowStatus;
	}

	public void setFlowStatus(int flowStatus)
	{
		this.flowStatus = flowStatus;
	}

	public String getLastStatusName()
	{
		return lastStatusName;
	}

	public void setLastStatusName(String lastStatusName)
	{
		this.lastStatusName = lastStatusName;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	public Date getFlowDate()
	{
		return flowDate;
	}

	public void setFlowDate(Date flowDate)
	{
		this.flowDate = flowDate;
	}

	public Tran getTran()
	{
		return tran;
	}

	public void setTran(Tran tran)
	{
		this.tran = tran;
	}

	public Set getStatus()
	{
		return Status;
	}

	public void setStatus(Set status)
	{
		Status = status;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}