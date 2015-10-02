package action;

import java.util.List;

import model.Tran;
import service.TranService;

import com.opensymphony.xwork2.ActionSupport;

public class SearchTranAction extends ActionSupport
{
	private String loginNo;
	private String tranNo;
	private String title;
	private String applyUser;
	private String userName;
	private String deptName;
	private String fromDate;
	private String toDate;
	private String supervisor;
	private String status;
	private int hadApply;
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private int beenReject;
	private String reject;
	private String search;
	
	List<Tran> tranList;
	TranService tranService;
	
	@Override
	public String execute() throws Exception
	{			
		if(hadApply > 0)							// 若按下已申請，則帶回已申請清單
		{
			tranList = tranService.findMenuTran(loginNo, "hadApply");
		}
		
		else if(waitApprove > 0)					// 若按下待審核，則帶回待審核清單
		{
			tranList = tranService.findMenuTran(loginNo, "waitApprove");
		}
		
		else if(waitDone > 0)						// 若按下待實作，則帶回待實作清單
		{	
			tranList = tranService.findMenuTran(loginNo, "waitDone");
		}
		
		else if(waitCheck > 0)						// 若按下待驗收，則帶回待驗收清單
		{	
			tranList = tranService.findMenuTran(loginNo, "waitCheck");
		}
		
		else if(beenReject > 0)						// 若按下退件，則帶回退件清單
		{
			tranList = tranService.findMenuTran(loginNo, "beenReject");
		}
		
		else if("搜尋".equals(search))				// 一般搜尋；若wait皆為0，則return SUCCESS帶回空的tranList
		{
			tranList = tranService.findTran(tranNo, title, applyUser, deptName, fromDate, toDate, supervisor, status, reject);
		}
		
		return SUCCESS;
	}

	public String getLoginNo()
	{
		return loginNo;
	}

	public void setLoginNo(String loginNo)
	{
		this.loginNo = loginNo;
	}

	public String getTranNo()
	{
		return tranNo;
	}

	public void setTranNo(String tranNo)
	{
		this.tranNo = tranNo;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getApplyUser()
	{
		return applyUser;
	}

	public void setApplyUser(String applyUser)
	{
		this.applyUser = applyUser;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
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

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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

	public String getReject()
	{
		return reject;
	}

	public void setReject(String reject)
	{
		this.reject = reject;
	}

	public String getSearch()
	{
		return search;
	}

	public void setSearch(String search)
	{
		this.search = search;
	}

	public List<Tran> getTranList()
	{
		return tranList;
	}

	public void setTranList(List<Tran> tranList)
	{
		this.tranList = tranList;
	}

	public TranService getTranService()
	{
		return tranService;
	}

	public void setTranService(TranService tranService)
	{
		this.tranService = tranService;
	}
}
