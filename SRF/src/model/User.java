package model;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String userNo;
	private String userPwd;
	private String reUserPwd;
	private String userName;
	private int userDept;
	private String userMail;
	private Date createDate;
	private String supervisor;
	private int hadApply;
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private int beenReject;
	
	public User() {}

	public String getUserNo()
	{
		return userNo;
	}

	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
	}

	public String getUserPwd()
	{
		return userPwd;
	}

	public void setUserPwd(String userPwd)
	{
		this.userPwd = userPwd;
	}

	public String getReUserPwd()
	{
		return reUserPwd;
	}

	public void setReUserPwd(String reUserPwd)
	{
		this.reUserPwd = reUserPwd;
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

	public String getUserMail()
	{
		return userMail;
	}

	public void setUserMail(String userMail)
	{
		this.userMail = userMail;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public int getHadApply()
	{
		return hadApply;
	}

	public void setHadApply(int hadApply)
	{
		this.hadApply = hadApply;
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

	public int getBeenReject()
	{
		return beenReject;
	}

	public void setBeenReject(int beenReject)
	{
		this.beenReject = beenReject;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}