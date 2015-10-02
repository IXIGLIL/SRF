package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Attach;
import model.Flow;
import model.Phone;
import model.Tran;
import model.User;
import model.Video;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import service.UserService;
import dao.TranDAO;

public class TranDAOImpl extends HibernateDaoSupport implements TranDAO
{
	UserService userService;

	public List<Tran> findTran(String tranNo, String title, String applyUser, String deptName, String fromDate, String toDate, String supervisor, String status, String reject)
	{		
		String sql = "select new Tran(T.tranNo, T.title, (select className from Class where classNo = T.tranClass) as tranClass, U.userName, D.deptName, T.applyDate, T.updateDate, (select userName from User where userNo = T.updateUser) as updateUser, (select userName from User U where U.userNo = T.supervisor) as supervisor, T.status, (select statusName from Status where statusNo = T.status) as statusName) from Tran T, User U, Dept D where T.userNo = U.userNo and T.userDept = D.deptNo and T.tranNo is not null ";
		
		if(tranNo != null && !"".equals(tranNo))
		{
			sql = sql + " and T.tranNo like '%" + tranNo + "%' ";
		}
		
		if(title != null && !"".equals(title))
		{
			sql = sql + " and T.title like '%" + title + "%' ";
		}
		
		if(title != null && !"".equals(applyUser))
		{
			sql = sql + " and ((T.userNo like '%" + applyUser + "%') or (U.userName like '%" + applyUser + "%')) ";
		}
		
		if(deptName != null && !"".equals(deptName))
		{
			sql = sql + " and D.deptName like '%" + deptName + "%' ";
		}
		
		if(fromDate != null && !"".equals(fromDate))
		{
			sql = sql + " and to_char(T.applyDate, 'yyyy-mm-dd') >= '" + fromDate + "' ";
		}
		
		if(toDate != null && !"".equals(toDate))
		{
			sql = sql + " and to_char(T.applyDate, 'yyyy-mm-dd') <= '" + toDate + "' ";
		}
		
		if(supervisor != null && !"".equals(supervisor))
		{
			sql = sql + " and ((T.supervisor like '%" + supervisor + "%' or T.supervisor = (select U.userNo from User U where U.userName like '%" + supervisor + "%'))) ";
		}
		
		if(status != null && !"".equals(status))
		{
			sql = sql + " and (T.status like '%" + status + "%' or T.statusName = (select statusName from Status where statusName like '%" + status + "%'))" ;
		}
		
		if("Y".equals(reject))
		{
			sql = sql + " and (T.status between '1' and '10') ";
		}
		
		if("N".equals(reject))
		{
			sql = sql + " and (T.status between '1' and '9') ";
		}
		
		sql = sql + " order by T.tranNo ";

		return super.getHibernateTemplate().find(sql);
	}
	
	public List<Tran> findMenuTran(String userNo, String type)
	{
		String sql = "select new Tran(T.tranNo, T.title, (select className from Class where classNo = T.tranClass) as tranClass, U.userName, D.deptName, T.applyDate, T.updateDate, (select userName from User where userNo = T.updateUser) as updateUser, (select userName from User U where U.userNo = T.supervisor) as supervisor, T.status, (select statusName from Status where statusNo = T.status) as statusName) from Tran T, User U, Dept D where T.userNo = U.userNo and T.userDept = D.deptNo and T.tranNo is not null and T.status > 0 ";
		
		if("hadApply".equals(type))
		{
			sql = sql + " and (T.userNo = '" + userNo + "' and T.status = '1' ";
		}
		
		if("waitApprove".equals(type))									// 非梅君搜尋一般待審核
		{
			sql = sql + " and ((T.supervisor = '" + userNo + "' and T.supervisorCheck is null and T.status = '1') ";
		}
		
		if("waitApprove".equals(type) && "0001537".equals(userNo))		// 梅君除了一般待審核外還有待資訊處審核
		{
			sql = sql + " or (T.supervisorCheck = 'Y' and T.itCheck is null and T.status = '2') ";
		}
		
		if("waitDone".equals(type))
		{
			sql = sql + " and (T.itOwner = '" + userNo + "' and T.status = '3' ";
		}

		
		if("waitCheck".equals(type))
		{
			sql = sql + " and (T.userNo = '" + userNo + "' and T.status = '4' ";
		}
		
		if("beenReject".equals(type))
		{
			sql = sql + " and ((T.userNo = '" + userNo + "' and T.lastStatus in ('1', '6', '7') and T.status = '6') or (T.supervisor = '" + userNo + "' and T.lastStatus in ('2', '6', '8') and T.status = '7') or (T.lastStatus in ('3', '9') and T.status = '8' and '" + userNo + "' = '0001537') or (T.lastStatus in ('4') and T.status = '9' and T.itOwner = '" + userNo + "')";
		}		
		
		sql = sql + " ) order by T.tranNo ";
		
		return super.getHibernateTemplate().find(sql);
	}
	
	public List<Tran> findTran(String tranNo)
	{
		return super.getHibernateTemplate().find("from Tran where tranNo = '" + tranNo + "'");
	}

	public List<String> findClass()
	{
		return (List<String>) this.getHibernateTemplate().find("from Class order by classNo");
	}

	public List<String> findPlace()
	{
		return (List<String>) this.getHibernateTemplate().find("from Place order by placeNo");
	}
	
	public List<String> findIt()
	{
		return (List<String>) this.getHibernateTemplate().find("from User where userDept = 66 order by userNo");
	}
	
	public List findFlow(String tranNo)
	{
		return super.getHibernateTemplate().find("select new Flow(F.tranNo, (select statusName from Status S where S.statusNo = F.flowLastStatus) as lastStatusName, (select statusName from Status S where S.statusNo = F.flowStatus) as statusName, F.flowDate) from Flow F, Status S, Tran T where T.tranNo = '" + tranNo + "' and T.tranNo = F.tranNo and T.status = S.statusNo order by F.flowDate");
	}
	
	public String findTranNo()
	{
		return super.getHibernateTemplate().find("select lpad(max(tranNo)+1,8,0) as tranNo from Tran").toString();
	}
	
	public String findVideoNo()
	{
		return super.getHibernateTemplate().find("select lpad(max(videoNo)+1,8,0) as videoNo from Video").toString();
	}
	
	public String findPhoneNo()
	{
		return super.getHibernateTemplate().find("select lpad(max(phoneNo)+1,8,0) as phoneNo from Phone").toString();
	}
	
	public void createTran(Tran tran)
	{
		super.getHibernateTemplate().save(tran);
	}
	
	public void createFlow(Flow flow)
	{
		super.getHibernateTemplate().save(flow);
	}
	
	public void createVideo(Video video)
	{
		super.getHibernateTemplate().save(video);
	}
	
	public void createPhone(Phone phone)
	{
		super.getHibernateTemplate().save(phone);
	}
	
	public void createAttach(Attach attach)
	{
		super.getHibernateTemplate().save(attach);
	}
	
	public void updateTran(Tran tran)
	{
		super.getHibernateTemplate().update(tran);
	}
	
	public void updateVideo(Video video)
	{
		super.getHibernateTemplate().update(video);
	}
	
	public void updatePhone(Phone phone)
	{
		super.getHibernateTemplate().update(phone);
	}
	
	public void approveTran(String tranNo, String loginNo, String itOwner, String itManager, String doneDate, String doneHour, String checkResult, String checkMemo, int status, Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Tran tran = getHibernateTemplate().get(Tran.class, tranNo);
		User user = getHibernateTemplate().get(User.class, loginNo);
		
		if(tran.getStatus() == 1)
		{
			tran.setSupervisorCheck("Y");		// 主管審核
		}
		
		if(tran.getStatus() == 2)
		{
			tran.setItCheck("Y");			// 梅君審核
			tran.setItOwner(itOwner);
			tran.setItManager(user.getSupervisor());
			tran.setReceiveDate(date);
		}
		
		if(tran.getStatus() == 3)				// 實作中
		{
			try
			{
				tran.setDoneDate(sdf.parse(doneDate + " 00:00:00"));
			}
			
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			
			tran.setDoneHour(doneHour);
		}
		
		if(tran.getStatus() == 4)				// 驗收中
		{			
			tran.setCheckUser(loginNo);
			tran.setCheckResult(checkResult);
			tran.setCheckMemo(checkMemo);
		}

		tran.setLastStatus(tran.getStatus());
		tran.setStatus(status);

		tran.setUpdateDate(date);
		tran.setUpdateUser(loginNo);
		
		tran.setRejectReason(null);
		tran.setRejectDate(null);
		
		getHibernateTemplate().update(tran);
	}
	
	public void rejectTran(String tranNo, String rejectUser, String rejectReason, boolean invalid, int status, Date date)
	{		
		Tran tran = getHibernateTemplate().get(Tran.class, tranNo);
		
		tran.setLastStatus(tran.getStatus());
		tran.setStatus(status);
		
		if(tran.getStatus() == 6)
		{
			tran.setSupervisorCheck(null);
		}
		
		if(tran.getStatus() == 7)
		{
			tran.setItCheck(null);
			tran.setReceiveDate(null);
		}
		
		if(tran.getStatus() == 10)
		{
			tran.setActive("N");
		}
		
		tran.setRejectUser(rejectUser);
		tran.setRejectReason(rejectReason);
		tran.setRejectDate(date);
		tran.setUpdateUser(rejectUser);
		tran.setUpdateDate(date);
		
		getHibernateTemplate().update(tran);
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	public String getStatus(String tranNo)
	{
		return super.getHibernateTemplate().find("select status from Tran where tranNo = '" + tranNo + "' ").toString();
	}
	
	public String getLastStatus(String tranNo)
	{
		return super.getHibernateTemplate().find("select lastStatus from Tran where tranNo = '" + tranNo + "' ").toString();
	}
	
	public void deleteAttach(String tranNo)
	{
		Attach attach = getHibernateTemplate().get(Attach.class, tranNo);
		Tran tran = getHibernateTemplate().get(Tran.class, tranNo);
		
		tran.setAttach("N");
		
		super.getHibernateTemplate().update(tran);
		super.getHibernateTemplate().delete(attach);
	}
}	