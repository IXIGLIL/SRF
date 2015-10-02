package service;

import java.util.Date;
import java.util.List;

import model.Attach;
import model.Flow;
import model.Phone;
import model.Tran;
import model.Video;


public interface TranService
{
	public List<Tran> findTran (String tranNo, String title, String applyUser, String deptName, String fromDate, String toDate, String supervisor, String status, String reject);
	public List<Tran> findTran(String tranNo);
	public List<Tran> findMenuTran (String loginNo, String type);
	public List<String> findClass();
	public List<String> findPlace();
	public List<String> findIt();
	public List findFlow(String tranNo);
	public String findTranNo();
	public String findVideoNo();
	public String findPhoneNo();
	public void createTran(Tran tran);
	public void createFlow(Flow flow);
	public void createVideo(Video video);
	public void createPhone(Phone phone);
	public void createAttach(Attach attach);
	public void updateTran(Tran tran);
	public void updateVideo(Video video);
	public void updatePhone(Phone phone);
	public void approveTran(String tranNo, String loginNo, String itOwner, String itManager, String doneDate, String doneHour, String checkResult, String checkMemo, int status, Date date);
	public void rejectTran(String tranNo, String rejectUser, String rejectReason, boolean invalid, int status, Date date);
	public String getStatus(String tranNo);
	public String getLastStatus(String tranNo);
	public void deleteAttach(String tranNo);
}
