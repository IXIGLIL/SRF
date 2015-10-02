package action;

import java.util.List;

import model.User;
import service.TransferService;
import service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport
{	
	private String loginNo;
	private String tranNo;
	private String userName;
	private String userDept;
	private String supervisor;
	private String create;
	private String search;
	private String menu;
	private int hadApply;
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private int beenReject;
	
	List<User> user;
	
	UserService userService;
	TransferService transferService;
	
	@Override
	public String execute() throws Exception
	{		
		if("查詢需求單".equals(search))
		{
			return "search";
		}
		
		if("申請需求單".equals(create))
		{
			user = userService.findUser(loginNo);
			
			userName = user.get(0).getUserName();
			userDept = this.transferService.deptNoToName(user.get(0).getUserDept()).toString().replace('[', ' ').replace(']', ' ').trim();
			supervisor = this.transferService.userNoToName(user.get(0).getSupervisor()).toString().replace('[', ' ').replace(']', ' ').trim();
			
			return "create";
		}
		
		if("主選單".equals(menu))
		{
			user = userService.findUser(loginNo);
			
			hadApply = user.get(0).getHadApply();
			waitApprove = user.get(0).getWaitApprove();
			waitDone = user.get(0).getWaitDone();
			waitCheck = user.get(0).getWaitCheck();
			beenReject = user.get(0).getBeenReject();
			
			return "menu";
		}
		
		return INPUT;
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

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserDept()
	{
		return userDept;
	}

	public void setUserDept(String userDept)
	{
		this.userDept = userDept;
	}

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getCreate()
	{
		return create;
	}

	public void setCreate(String create)
	{
		this.create = create;
	}

	public String getSearch()
	{
		return search;
	}

	public void setSearch(String search)
	{
		this.search = search;
	}

	public String getMenu()
	{
		return menu;
	}

	public void setMenu(String menu)
	{
		this.menu = menu;
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

	public List<User> getUser()
	{
		return user;
	}

	public void setUser(List<User> user)
	{
		this.user = user;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public TransferService getTransferService()
	{
		return transferService;
	}

	public void setTransferService(TransferService transferService)
	{
		this.transferService = transferService;
	}
}
