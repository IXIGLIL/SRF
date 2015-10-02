package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class Tran implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String tranNo;
	private int tranClass;
	private String tranClassName;
	private String title;
	private String format;
	private String memo;
	private String userNo;
	private String userName;
	private int userDept;
	private String deptName;
	private String videoNo;
	private String phoneNo;
	private String attach;
	private Date applyDate;
	private String fromDate;
	private String toDate;
	private String itCheck;
	private String supervisorCheck;
	private String supervisor;
	private String rejectUser;
	private String rejectReason;
	private String itOwner;
	private String itManager;
	private Date requestDate;
	private Date receiveDate;
	private Date rejectDate;
	private Date doneDate;
	private String doneHour;
	private String checkUser;
	private String checkResult;
	private String checkMemo;
	private Date updateDate;
	private String updateUser;
	private String active;
	private int lastStatus;
	private int status;
	private String statusName;
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private Set Flow;
	
	public Tran() {}
	
	public Tran(String tranNo, String title, String tranClassName, String userName, String deptName, Date applyDate, Date updateDate, String updateUser, String supervisor, int status, String statusName)
	{
		this.tranNo = tranNo;
		this.title = title;
		this.tranClassName = tranClassName;
		this.userName = userName;
		this.deptName = deptName;
		this.applyDate = applyDate;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.supervisor = supervisor;
		this.status = status;
		this.statusName = statusName;
	}

	public int getWaitApprove()
	{
		return waitApprove;
	}

	public void setWaitApprove(int waitApprove)
	{
		this.waitApprove = waitApprove;
	}

	public int getWaitDone()
	{
		return waitDone;
	}

	public void setWaitDone(int waitDone)
	{
		this.waitDone = waitDone;
	}

	public int getWaitCheck()
	{
		return waitCheck;
	}

	public void setWaitCheck(int waitCheck)
	{
		this.waitCheck = waitCheck;
	}

	public String getTranNo()
	{
		return tranNo;
	}

	public void setTranNo(String tranNo)
	{
		this.tranNo = tranNo;
	}

	public int getTranClass()
	{
		return tranClass;
	}

	public void setTranClass(int tranClass)
	{
		this.tranClass = tranClass;
	}

	public String getTranClassName()
	{
		return tranClassName;
	}

	public void setTranClassName(String tranClassName)
	{
		this.tranClassName = tranClassName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String getUserNo()
	{
		return userNo;
	}

	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public int getUserDept()
	{
		return userDept;
	}

	public void setUserDept(int userDept)
	{
		this.userDept = userDept;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getVideoNo()
	{
		return videoNo;
	}

	public void setVideoNo(String videoNo)
	{
		this.videoNo = videoNo;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public Date getApplyDate()
	{
		return applyDate;
	}

	public void setApplyDate(Date applyDate)
	{
		this.applyDate = applyDate;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getItCheck()
	{
		return itCheck;
	}

	public void setItCheck(String itCheck)
	{
		this.itCheck = itCheck;
	}

	public String getSupervisorCheck()
	{
		return supervisorCheck;
	}

	public void setSupervisorCheck(String supervisorCheck)
	{
		this.supervisorCheck = supervisorCheck;
	}

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getRejectUser()
	{
		return rejectUser;
	}

	public void setRejectUser(String rejectUser)
	{
		this.rejectUser = rejectUser;
	}

	public String getRejectReason()
	{
		return rejectReason;
	}

	public void setRejectReason(String rejectReason)
	{
		this.rejectReason = rejectReason;
	}

	public String getItOwner()
	{
		return itOwner;
	}

	public void setItOwner(String itOwner)
	{
		this.itOwner = itOwner;
	}

	public String getItManager()
	{
		return itManager;
	}

	public void setItManager(String itManager)
	{
		this.itManager = itManager;
	}

	public Date getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	public Date getReceiveDate()
	{
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate)
	{
		this.receiveDate = receiveDate;
	}

	public Date getRejectDate()
	{
		return rejectDate;
	}

	public void setRejectDate(Date rejectDate)
	{
		this.rejectDate = rejectDate;
	}

	public Date getDoneDate()
	{
		return doneDate;
	}

	public void setDoneDate(Date doneDate)
	{
		this.doneDate = doneDate;
	}

	public String getDoneHour()
	{
		return doneHour;
	}

	public void setDoneHour(String doneHour)
	{
		this.doneHour = doneHour;
	}

	public String getCheckUser()
	{
		return checkUser;
	}

	public void setCheckUser(String checkUser)
	{
		this.checkUser = checkUser;
	}

	public String getCheckResult()
	{
		return checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
	}

	public String getCheckMemo()
	{
		return checkMemo;
	}

	public void setCheckMemo(String checkMemo)
	{
		this.checkMemo = checkMemo;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getUpdateUser()
	{
		return updateUser;
	}

	public void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}

	public int getLastStatus()
	{
		return lastStatus;
	}

	public void setLastStatus(int lastStatus)
	{
		this.lastStatus = lastStatus;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getStatusName()
	{
		return statusName;
	}

	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

	public Set getFlow()
	{
		return Flow;
	}

	public void setFlow(Set flow)
	{
		Flow = flow;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}