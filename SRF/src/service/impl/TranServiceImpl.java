package service.impl;

import java.util.Date;
import java.util.List;

import model.Attach;
import model.Flow;
import model.Phone;
import model.Tran;
import model.Video;
import service.TranService;
import dao.TranDAO;

public class TranServiceImpl implements TranService
{
	TranDAO tranDAO;

	public TranDAO getTranDAO()
	{
		return tranDAO;
	}

	public void setTranDAO(TranDAO tranDAO)
	{
		this.tranDAO = tranDAO;
	}
	
	public List<Tran> findTran(String tranNo, String title, String applyUser, String deptName, String fromDate, String toDate, String supervisor, String status, String reject)
	{
		return tranDAO.findTran(tranNo, title, applyUser, deptName, fromDate, toDate, supervisor, status, reject);
	}
	
	public List<Tran> findTran(String tranNo)
	{
		return tranDAO.findTran(tranNo);
	}
	
	public List<Tran> findMenuTran(String loginNo, String type)
	{
		return tranDAO.findMenuTran(loginNo, type);
	}

	public List<String> findClass()
	{
		return tranDAO.findClass();
	}

	public List<String> findPlace()
	{
		return tranDAO.findPlace();
	}
	
	public List<String> findIt()
	{
		return tranDAO.findIt();
	}
	
	public List findFlow(String tranNo)
	{
		return tranDAO.findFlow(tranNo);
	}
	
	public String findTranNo()
	{
		return tranDAO.findTranNo();
	}
	
	public String findVideoNo()
	{
		return tranDAO.findVideoNo();
	}
	
	public String findPhoneNo()
	{
		return tranDAO.findPhoneNo();
	}
	
	public void createTran(Tran tran)
	{
		tranDAO.createTran(tran);
	}
	
	public void createFlow(Flow flow)
	{
		tranDAO.createFlow(flow);
	}
	
	public void createVideo(Video video)
	{
		tranDAO.createVideo(video);
	}
	
	public void createPhone(Phone phone)
	{
		tranDAO.createPhone(phone);
	}
	
	public void createAttach(Attach attach)
	{
		tranDAO.createAttach(attach);
	}
	
	public void updateTran(Tran tran)
	{
		tranDAO.updateTran(tran);
	}
	
	public void updateVideo(Video video)
	{
		tranDAO.updateVideo(video);
	}
	
	public void updatePhone(Phone phone)
	{
		tranDAO.updatePhone(phone);
	}
	
	public void approveTran(String tranNo, String loginNo, String itOwner, String itManager, String doneDate, String doneHour, String checkResult, String checkMemo, int status, Date date)
	{
		tranDAO.approveTran(tranNo, loginNo, itOwner, itManager, doneDate, doneHour, checkResult, checkMemo, status, date);
	}
	
	public void rejectTran(String tranNo, String rejectUser, String rejectReason, boolean invalid, int status, Date date)
	{		
		tranDAO.rejectTran(tranNo, rejectUser, rejectReason, invalid, status, date);
	}
	
	public String getStatus(String tranNo)
	{
		return tranDAO.getStatus(tranNo);
	}
	
	public String getLastStatus(String tranNo)
	{
		return tranDAO.getLastStatus(tranNo);
	}
	
	public void deleteAttach(String tranNo)
	{
		tranDAO.deleteAttach(tranNo);
	}
}
