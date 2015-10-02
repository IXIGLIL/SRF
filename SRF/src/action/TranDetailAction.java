package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Attach;
import model.Flow;
import model.Phone;
import model.Tran;
import model.Video;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import service.TranService;
import service.TransferService;
import service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class TranDetailAction extends ActionSupport
{	
	private String loginNo;
	private String tranNo;
	private int tranClass;
	private String title;
	private String format;
	private String memo;
	private String applyUser;
	private String userName;
	private String userDept;
	private String videoNo;
	private int videoPlace;
	private String videoDate;
	private String videoContact;
	private String phoneNo;
	private String phoneDate;
	private String phoneContact;
	private String attach;
	private String attachName;
	private String downloadAttach;
	private String deleteAttach;
	private String deleteAttachFile;
	private String applyDate;
	private String itCheck;
	private String supervisorCheck;
	private String supervisor;
	private String supervisorNo;
	private String rejectUser;
	private String rejectReason;
	private String itOwnerNo;
	private String itOwner;
	private String itManager;
	private String requestDate;
	private String receiveDate;
	private String rejectDate;
	private String doneDate;
	private String doneHour;
	private String checkUser;
	private String checkResult;
	private String checkMemo;
	private String updateDate;
	private String updateUser;
	private String active;
	private String lastStatus;
	private String status;
	private String flowStatus;
	private Date flowDate;
	private String updateTran;
	private String rejectTran;
	private String approveTran;

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private List<String> itList = new ArrayList<String>();
	
	List flowList;
	List<Tran> tranList;
	List<Video> videoList;
	List<Phone> phoneList;
	UserService userService;
	TranService tranService;
	TransferService transferService;
	
	public String findDetail()
	{		
		Date date = new Date();
		date = new Date(date.getTime());
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		flowList = tranService.findFlow(tranNo);
		tranList = tranService.findTran(tranNo);
		
		Iterator tranIterator = tranList.iterator();
		
		while (tranIterator.hasNext())
		{
			Tran tran = new Tran();
			tran = (Tran) tranIterator.next();
			
			tranNo = tran.getTranNo();
			tranClass = tran.getTranClass();
			userName = transferService.userNoToName(tran.getUserNo()).toString().replace("[", " ").replace("]", " ").trim();
			applyUser = tran.getUserNo();
			title = tran.getTitle();
			format = tran.getFormat();
			memo = tran.getMemo();
			userDept = transferService.deptNoToName(tran.getUserDept()).toString().replace("[", " ").replace("]", " ").trim();
			attach = tran.getAttach();
			attachName = transferService.attachNoToName(tran.getTranNo()).replace("[", " ").replace("]", " ").trim();
			applyDate = sdf.format(tran.getApplyDate());
			itCheck = tran.getItCheck();
			supervisorCheck = tran.getSupervisorCheck();
			supervisor = transferService.userNoToName(tran.getSupervisor()).toString().replace("[", " ").replace("]", " ").trim();
			supervisorNo = tran.getSupervisor();
			rejectUser = transferService.userNoToName(tran.getRejectUser()).toString().replace("[", " ").replace("]", " ").trim();
			rejectReason = tran.getRejectReason();
			itOwnerNo = tran.getItOwner();
			itOwner = transferService.userNoToName(tran.getItOwner()).toString().replace("[", " ").replace("]", " ").trim();
			itManager = transferService.userNoToName(tran.getItManager()).toString().replace("[", " ").replace("]", " ").trim();
			doneHour = tran.getDoneHour();
			checkUser = transferService.userNoToName(tran.getCheckUser()).toString().replace("[", " ").replace("]", " ").trim();
			checkResult = tran.getCheckResult();
			checkMemo = tran.getCheckMemo();
			updateUser = transferService.userNoToName(tran.getUpdateUser()).toString().replace("[", " ").replace("]", " ").trim();
			active = tran.getActive();
			lastStatus = Integer.toString(tran.getLastStatus());
			status = transferService.statusNoToName(tran.getStatus()).toString().replace("[", " ").replace("]", " ").trim();
			updateDate = sdf.format(tran.getUpdateDate());
			requestDate = sdf.format(tran.getRequestDate());
			
//			if("實作中".equals(status) || "驗收中".equals(status) || "結案".equals(status))
			if(!("主管審核".equals(status) || "主管退件".equals(status) || "資訊處退件".equals(status)))
			{
				receiveDate = sdf.format(tran.getReceiveDate());
			}
			
			if("驗收中".equals(status) || "結案".equals(status))
			{
				doneDate = sdf.format(tran.getDoneDate());
			}
			
			if("退件".equals(status))
			{
				rejectDate = sdf.format(tran.getRejectDate());
			}
			
			if (tran.getVideoNo() != null)
			{
				videoList = transferService.videoNoToDetail(tran.getVideoNo());			
				videoNo = tran.getVideoNo();
				videoDate = videoList.get(0).getVideoDate().toString();
				videoContact = transferService.userNoToName(videoList.get(0).getVideoContact()).toString().replace("[", " ").replace("]", " ").trim().isEmpty() ? videoList.get(0).getVideoContact() : transferService.userNoToName(videoList.get(0).getVideoContact()).toString().replace("[", " ").replace("]", " ").trim();
				videoPlace = videoList.get(0).getVideoPlace();
			}
			
			if (tran.getPhoneNo() != null)
			{
				phoneList = transferService.phoneNoToDetail(tranList.get(0).getPhoneNo());
				
				phoneNo = tranList.get(0).getPhoneNo();
				phoneDate = phoneList.get(0).getPhoneDate().toString();
				phoneContact = transferService.userNoToName(phoneList.get(0).getPhoneContact()).toString().replace("[", " ").replace("]", " ").trim().isEmpty() ? phoneList.get(0).getPhoneContact() : transferService.userNoToName(phoneList.get(0).getPhoneContact()).toString().replace("[", " ").replace("]", " ").trim();
			}
		}
		
		return SUCCESS;
	}
	
	public String detailAction()
	{		
		if(updateTran != null)
		{
			return updateTran();
		}
		
		if(rejectTran != null)
		{
			return rejectTran();
		}
		
		if(approveTran != null)
		{
			return approveTran();
		}
		
		if(deleteAttach != null)
		{
			return deleteAttach();
		}
		
		if(downloadAttach != null)
		{
			return downloadFile();
		}
		
		return INPUT;
	}
	
	public String updateTran()
	{			
		boolean update = true;
		status = transferService.statusNoToName(Integer.parseInt(tranService.getStatus(tranNo).replace("[", " ").replace("]", " ").trim())).replace("[", " ").replace("]", " ").trim();
		
		if (!("主管審核".equals(status) || "主管退件".equals(status) || "驗收中".equals(status)))//"主管退件".equals(status) || "資訊處退件".equals(status) || "實作者退件".equals(status) || "驗收者退件".equals(status)))
		{
			this.addFieldError("updateStatusCheck", "未在可更新階段(主管審核、驗收中)！");
			update = false;
		}
		
		if(!loginNo.equals(applyUser))
		{
			this.addFieldError("updateUserCheck", "更新權限不足(申請人)！");
			update = false;
		}
		
		if(update)
		{
			Date date = new Date();
			date = new Date(date.getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			Tran tran = new Tran();
			tran.setTranNo(tranNo);
			tran.setTranClass(tranClass);
			tran.setUserDept(Integer.parseInt(transferService.deptNameToNo(userDept).replace("[", " ").replace("]", " ").trim()));
			tran.setUserNo(transferService.userNameToNo(tran.getUserDept(), userName).replace("[", " ").replace("]", " ").trim());
			tran.setTitle(title);
			tran.setFormat(format);
			tran.setMemo(memo);
			tran.setAttach(attach);
			tran.setItCheck(itCheck);
			tran.setSupervisorCheck(supervisorCheck);
			tran.setSupervisor(transferService.userNameToNo(tran.getUserDept(),supervisor).replace("[", " ").replace("]", " ").trim());
			tran.setRejectUser(transferService.userNameToNo(tran.getUserDept(),rejectUser).replace("[", " ").replace("]", " ").trim());
			tran.setRejectReason(rejectReason);
			tran.setItOwner(transferService.userNameToNo(tran.getUserDept(),itOwner).replace("[", " ").replace("]", " ").trim());
			tran.setItManager(transferService.userNameToNo(tran.getUserDept(),itManager).replace("[", " ").replace("]", " ").trim());
			tran.setVideoNo(videoNo);
			tran.setPhoneNo(phoneNo);
			tran.setDoneHour(doneHour);
			tran.setCheckUser(transferService.userNameToNo(-1,checkUser).replace("[", " ").replace("]", " ").trim());
			tran.setCheckResult(checkResult);
			tran.setCheckMemo(checkMemo);
			tran.setUpdateUser(applyUser);
			tran.setLastStatus(Integer.parseInt(lastStatus));
			tran.setStatus(Integer.parseInt(transferService.statusNameToNo(status).replace("[", " ").replace("]", " ").trim()));
			tran.setUpdateDate(date);
			tran.setAttach(attach);
			
			try
			{
				tran.setApplyDate(sdf.parse(applyDate));
				tran.setRequestDate(sdf.parse(requestDate));
			}
			
			catch (ParseException e)
			{
				this.addFieldError("requestDateFormat", "需求日期格式不符！");
				return INPUT;
			}
			
			if("驗收中".equals(status))
			{
				receiveDate = sdf.format(tran.getReceiveDate());
				doneDate = sdf.format(tran.getDoneDate());
			}
			
			if (videoNo != null)
			{
				Video video = new Video();
				
				video.setVideoNo(videoNo);
				video.setVideoPlace(videoPlace);
				video.setVideoContact(transferService.userNameToNo(-1, videoContact).replace("[", " ").replace("]", " ").trim().isEmpty() ? videoContact : transferService.userNameToNo(-1, videoContact).replace("[", " ").replace("]", " ").trim());
				
				try
				{
					video.setVideoDate(sdf.parse(videoDate));
				}
				
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				
				tranService.updateVideo(video);
			}
			
			if (phoneNo != null)
			{				
				Phone phone = new Phone();
				
				phone.setPhoneNo(phoneNo);
				phone.setPhoneContact(transferService.userNameToNo(-1, phoneContact).replace("[", " ").replace("]", " ").trim().isEmpty() ? phoneContact : transferService.userNameToNo(-1, phoneContact).replace("[", " ").replace("]", " ").trim());
				
				try
				{
					phone.setPhoneDate(sdf.parse(phoneDate));
				}
				
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				
				tranService.updatePhone(phone);
			}
			
			if(fileUpload != null)
			{
				FTPClient ftp = new FTPClient();
			    try
			    {
				    ftp.connect("192.168.0.2");
				    ftp.login("jyunda", "P@SSW0RD");
				    int reply = ftp.getReplyCode();
				    
				    if(!FTPReply.isPositiveCompletion(reply))
				    {
				    	ftp.disconnect();
				    	return INPUT;
				    }
				    
				    ftp.enterLocalPassiveMode();
				    ftp.setFileType(FTP.BINARY_FILE_TYPE);
				    ftp.changeWorkingDirectory("/home/jyunda/SRF/");
				    FileInputStream isInputStream = new FileInputStream(fileUpload);
				    
				    tran.setAttach("Y");
				    attach = "Y";
				    attachName = fileUploadFileName;
				    
					Attach attach = new Attach();
					attach.setTranNo(tran.getTranNo());
					attach.setFileName(fileUploadFileName);
					
					tranService.createAttach(attach);
					tran.setAttach("Y");
					
				    ftp.storeFile(tran.getTranNo() + "_" + fileUploadFileName, isInputStream);

				    isInputStream.close();
				    ftp.logout();
			    }
			    
			    catch(IOException e)
			    {
			    	e.printStackTrace();
			    }
			    
			    finally
			    {
			    	if(ftp.isConnected())
			    	{
			    		try
			    		{
			    			ftp.disconnect();
			    		}
			    		
			    		catch(IOException e) {}
			    	}
			    }
			}
			
			tranService.updateTran(tran);
					
			this.addFieldError("updateSuccess", "單據更新完成！");
		}
		
		return SUCCESS;
	}
	
	public String approveTran()
	{	
		Date date = new Date();
		date = new Date(date.getTime());
		
		status = transferService.statusNoToName(Integer.parseInt(tranService.getStatus(tranNo).replace("[", " ").replace("]", " ").trim())).replace("[", " ").replace("]", " ").trim();
		lastStatus = tranService.getLastStatus(tranNo).replace("[", " ").replace("]", " ").trim();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		boolean approve = true;
		
		supervisor = transferService.userNameToNo(Integer.parseInt(transferService.deptNameToNo(userDept).replace("[", " ").replace("]", " ").trim()), supervisor).replace("[", " ").replace("]", " ").trim();
		
		if("申請人作廢".equals(status))
		{
			if(!loginNo.equals(applyUser))
			{
				this.addFieldError("approveRight", "審核權限不足(申請人)！");
				approve = false;
			}
		}
		
		// 主管審核 → 資訊處審核
		if("主管審核".equals(status) && !"資訊處審核".equals(status))			// 部門主管審核
		{
			if (!loginNo.equals(supervisor))							// 但登入帳號(userNo)不為supervisor的話則無權限做審核
			{
				this.addFieldError("approveRight", "審核權限不足(申請人部門主管)！");
				approve = false;
			}
		}
		
		// 資訊處審核 → 實作中
		if("資訊處審核".equals(status) && !"實作中".equals(status))		// IT審核
		{
			if (!"0001537".equals(loginNo))							// 但登入帳號(userNo)不為梅君
			{
				this.addFieldError("approveRight", "審核權限不足(資訊處)！");
				approve = false;
			}
		}
		
		// 主管退件 → 使用者送出
		// 資訊處退件 → 主管送出
		if(("主管退件".equals(status) || "資訊處退件".equals(status)) && !("主管審核".equals(status) || "資訊處審核".equals(status)))
		{
			if(!((loginNo.equals(applyUser) && (("1".equals(lastStatus) || ("7".equals(lastStatus))))) || (loginNo.equals(supervisor)  && (("2".equals(lastStatus) || "8".equals(lastStatus))))))
			{
				// 將主管退件 → 主管審核
				// 主管審核 → 主管退件 or 資訊處退件 → 主管退件
				if((("1".equals(lastStatus)) || ("7".equals(lastStatus)) && ("主管退件".equals(status))))
				{
					this.addFieldError("approveRight", "派送權限不足(申請人)！");
				}
				
				// 將資訊處退件 → 資訊處審核
				if("2".equals(lastStatus) && ("資訊處退件".equals(status)))
				{
					this.addFieldError("approveRight", "派送權限不足(申請人主管)！");
				}
				
				approve = false;
			}
		}
		
		if (approve && ("主管審核".equals(status) || "資訊處審核".equals(status) || "實作中".equals(status) || "驗收中".equals(status) || "主管退件".equals(status) || "資訊處退件".equals(status) || "實作者退件".equals(status) || "申請人作廢".equals(status)))
		{
			if("申請人作廢".equals(status))
			{
				userService.updateHadApply(applyUser, "plus");
				userService.updateWaitApprove(supervisor, "plus");
				this.addFieldError("approveCheck", "單據作廢復原，派送給申請主管審核！");
			}
			
			if("主管審核".equals(status))
			{
				userService.updateHadApply(applyUser, "minus");		// 申請人申請數-1
				userService.updateWaitApprove(loginNo, "minus");	// 主管審核完  wait approve-1
				userService.updateWaitApprove("0001537", "plus");	// 換資訊處審核 wait approve+1
				this.addFieldError("approveCheck", "主管審核通過，派送給資訊處審核！");
			}
			
			if("主管退件".equals(status))
			{
				userService.updateBeenReject(applyUser, "minus");
				userService.updateWaitApprove(supervisor, "plus");
				userService.updateHadApply(applyUser, "plus");
				this.addFieldError("approveSuccess", "申請人派送至申請主管審核！");
			}

			if("資訊處審核".equals(status) || "實作者退件".equals(status))
			{
				if("-1".equals(itOwner))
				{
					this.addFieldError("itCheck", "IT處理人員不可為空！");
					approve = false;
				}
				
				else
				{
					userService.updateWaitApprove("0001537", "minus");	// 資訊處審核完 wait approve -1
					userService.updateWaitDone(itOwner, "plus");		// 實作IT人員 wait done +1
					this.addFieldError("approveCheck", "資訊處審核通過，派送給IT人員處理！");
				}
			}
			
			if ("資訊處退件".equals(status))
			{				
				if(loginNo.equals(supervisor))
				{
					userService.updateBeenReject(supervisor, "minus");
					userService.updateWaitApprove("0001537", "plus");
					this.addFieldError("approveSuccess", "申請主管派送至資訊處審核！");
				}
					
				else
				{
					this.addFieldError("approveCheck", "派送權限不足(申請人主管)！");
				}
			}
			
			if("實作中".equals(status))
			{
				try
				{
					sdf.parse(doneDate + " 00:00:00");
				}
				
				catch (Exception e)
				{
					this.addFieldError("doneDateFormat", "完成日期格式不符！");
					return INPUT;
				}
				
				if("".equals(doneDate))
				{
					this.addFieldError("itCheck", "完成日期不可為空！");
					approve = false;
				}
				
				if("".equals(doneHour))
				{
					this.addFieldError("itCheck", "完成時數不可為空！");
					approve = false;
				}
					
				else
				{
					userService.updateWaitDone(loginNo, "minus");		// 實作完 wait done-1
					userService.updateWaitCheck(transferService.userNameToNo(Integer.parseInt(transferService.deptNameToNo(userDept).replace("[", " ").replace("]", " ").trim()), userName).replace("[", " ").replace("]", " ").trim(), "plus");		// 申請人的wait check +1
					this.addFieldError("approveCheck", "IT實作完成，派送給申請人驗收！");
				}				
			}
			
			if("實作者退件".equals(status))
			{
				if("0001537".equals(loginNo))
				{
					userService.updateBeenReject("0001537", "minus");
					userService.updateWaitApprove(itOwnerNo, "plus");
					this.addFieldError("approveSuccess", "資訊處審核派送至IT實作者！");
				}
					
				else
				{
					this.addFieldError("approveCheck", "派送權限不足(資訊處助理)！");
				}
			}
			
			if("驗收中".equals(status))
			{
				if("".equals(checkResult) || "".equals(checkMemo))
				{
					this.addFieldError("itCheck", "驗收結果/驗收說明不可為空！");
					approve = false;
				}
				
				else
				{
					userService.updateWaitCheck(loginNo, "minus");		// 驗收結束把wait check -1
				}
			}
			
			if("結案".equals(status))
			{
				this.addFieldError("itCheck", "此單已結案！");
				approve = false;
			}
			
			if("作廢".equals(status))
			{
				this.addFieldError("itCheck", "此單已作廢！");
				approve = false;
			}
		}
		
		if(approve)
		{
			Flow flow = new Flow();
			flow.setFlowDate(date);
			flow.setFlowLastStatus(Integer.parseInt(tranService.getStatus(tranNo).replace("[", " ").replace("]", " ").trim()));
			
			System.out.println("status : " + status);
			
			if("申請人作廢".equals(status) && !"主管審核".equals(status))
			{
				flow.setFlowStatus(1);
			}
			
			else if("主管退件".equals(status) && !"主管審核".equals(status))
			{
				flow.setFlowStatus(1);
			}
			
			else if (("資訊處退件".equals(status) || "主管審核".equals(status)) && !"資訊處審核".equals(status))
			{
				flow.setFlowStatus(2);
			}
			
			else if ("實作者退件".equals(status) && !"實作中".equals(status))
			{
				flow.setFlowStatus(3);
			}
			
			else
			{
				flow.setFlowStatus(Integer.parseInt(transferService.statusNameToNo(status).replace("[", " ").replace("]", " ").trim())+1);
			}
			
			flow.setTranNo(tranNo);
			tranService.createFlow(flow);
			
			tranService.approveTran(tranNo, loginNo, itOwner, itManager, doneDate, doneHour, checkResult, checkMemo, flow.getFlowStatus(), date);
			
			this.addFieldError("nextSuccess", "階段派送成功！");
			
			return "next";
		}
		
		if(("主管退件".equals(status) || "資訊處退件".equals(status) || "實作者退件".equals(status) || "驗收者退件".equals(status)) && loginNo.equals(applyUser))
		{
			updateTran();
		}
		
		return SUCCESS;
	}
	
	public String rejectTran()
	{	
		Date date = new Date();
		date = new Date(date.getTime());
		
		boolean reject = true;
		lastStatus = tranService.getLastStatus(tranNo).replace("[", " ").replace("]", " ").trim();
		supervisorNo = transferService.userNameToNo(Integer.parseInt(transferService.deptNameToNo(userDept).replace("[", " ").replace("]", " ").trim()), supervisor).replace("[", " ").replace("]", " ").trim();
		status = transferService.statusNoToName(Integer.parseInt(tranService.getStatus(tranNo).replace("[", " ").replace("]", " ").trim())).replace("[", " ").replace("]", " ").trim();
		
		if(!(	// 以下條件才有退件權限
				("主管審核".equals(status) && loginNo.equals(applyUser))
				|| ("主管審核".equals(status) && loginNo.equals(supervisorNo))
				|| ("主管退件".equals(status) && loginNo.equals(applyUser) && "1".equals(lastStatus))
				|| ("資訊處退件".equals(status) && loginNo.equals(supervisorNo) && "2".equals(lastStatus)) 
				|| ("實作中".equals(status) && loginNo.equals(itOwnerNo) && "2".equals(lastStatus))
				|| ("實作者退件".equals(status) && loginNo.equals("0001537") && "3".equals(lastStatus))
				|| ("資訊處退件".equals(status) && loginNo.equals(supervisorNo) && "8".equals(lastStatus))
				|| ("資訊處審核".equals(status) && "0001537".equals(loginNo))
				|| ("驗收中".equals(status) && loginNo.equals(applyUser))
				|| ("驗收者退件".equals(status) && loginNo.equals(itOwnerNo))
				|| ("實作者退件".equals(status) && "0001537".equals(loginNo))
				))
		{
			this.addFieldError("rejectCheck", "退件權限不足！");
			reject = false;
		}
		
		// 結案及申請人作廢下不可退件
		if(("結案".equals(status) || "申請人作廢".equals(status)))
		{
			this.addFieldError("rejectCheck", "單據未在可退件階段！");
			reject = false;
		}
		
		if(reject && "".equals(rejectReason))
		{
			this.addFieldError("rejectCheck", "退件原因不可為空！");
			reject = false;
		}
		
		if(reject)
		{			
			Flow flow = new Flow();
			flow.setFlowDate(date);
			
			flow.setFlowLastStatus(Integer.parseInt(tranService.getStatus(tranNo).replace("[", " ").replace("]", " ").trim()));
			flow.setTranNo(tranNo);
			
			// 申請人廢單
			if(loginNo.equals(applyUser) && ("主管審核".equals(status) || "主管退件".equals(status)))
			{
				if("主管審核".equals(status))
				{
					userService.updateHadApply(loginNo, "minus");
					userService.updateWaitApprove(supervisorNo, "minus");	// 主管待審核-1
				}
				
				if("主管退件".equals(status))
				{
					userService.updateBeenReject(loginNo, "minus");
				}

				flow.setFlowStatus(10);
				this.addFieldError("rejectCheck", "申請人廢單！");
			}
			
			// 主管審核 → 主管退件 
			if("主管審核".equals(status) && loginNo.equals(supervisorNo))
			{
				userService.updateWaitApprove(loginNo, "minus");	// 自己是主管，自己待審核-1
				userService.updateHadApply(applyUser, "minus");		// 申請人已申請-1(變成退件了)
				userService.updateBeenReject(applyUser, "plus");	// 申請人退件數+1
				flow.setFlowStatus(6);
				this.addFieldError("rejectCheck", "主管退件！");
			}
			
			// 資訊處審核 → 資訊處退件
			if("資訊處審核".equals(status) && "0001537".equals(loginNo))
			{
				userService.updateWaitApprove("0001537", "minus");		// 自己是梅君，自己待審核-1
				userService.updateBeenReject(supervisorNo, "plus");		// 主管退件數+1
				flow.setFlowStatus(7);
				this.addFieldError("rejectCheck", "資訊處退件！");
			}
			
			// 資訊處退件 → 主管退件
			if("資訊處退件".equals(status))
			{
				userService.updateBeenReject(supervisorNo, "minus");	// 主管退件數-1
				userService.updateBeenReject(applyUser, "plus");		// 申請人退件數+1
				flow.setFlowStatus(6);
				this.addFieldError("rejectCheck", "主管退件！");
			}
			
			// 實作中 → 實作者退件
			if("實作中".equals(status))
			{
				userService.updateWaitDone(itOwnerNo, "minus");			// 實作者-1
				userService.updateBeenReject("0001537", "plus");		// 梅君+1
				flow.setFlowStatus(8);
				this.addFieldError("rejectSuccess", "單據退回資訊處！");
			}
			
			// 實作者退件 → 資訊處退件
			if("實作者退件".equals(status))
			{				
				userService.updateBeenReject("0001537", "minus");		// 梅君-1
				userService.updateBeenReject(supervisorNo, "plus");		// 主管待檢核+1
				flow.setFlowStatus(7);
				this.addFieldError("rejectSuccess", "資訊處退件！");
			}
			
			// 驗收者退件 → 實作者退件
			if("驗收者退件".equals(status) && loginNo.equals(itOwnerNo))
			{
				userService.updateBeenReject(itOwnerNo, "minus");		// 實作者退件數-1
				userService.updateBeenReject("0001537", "plus");		// 梅君退件數+1
				flow.setFlowStatus(8);
				this.addFieldError("rejectSuccess", "單據退回資訊處！");
			}
			
			// 驗收中 → 驗收者退件
			if("驗收中".equals(status))
			{
				userService.updateWaitCheck(applyUser, "minus");		// 申請人待檢核-1
				userService.updateBeenReject(itOwnerNo, "plus");		// 實作者退件數+1
				flow.setFlowStatus(9);
				this.addFieldError("rejectSuccess", "單據退回給IT實作者！");
			}
			
			tranService.rejectTran(tranNo, loginNo, rejectReason, (loginNo.equals(applyUser)), flow.getFlowStatus(), date);
				
			if((loginNo.equals(applyUser) && "10".equals(flow.getStatus())))
			{
				this.addFieldError("rejectSuccess", "單據作廢完成！");	
			}
			
			else
			{
				this.addFieldError("rejectSuccess", "單據返回上一階段完成！");
			}

			tranService.createFlow(flow);
				
			return "next";
		}
		
		return SUCCESS;
	}
	
	public String downloadFile()
	{
        boolean ftpFileExist = false;
		attachName = downloadAttach;
		attach = "Y";

		FTPClient ftp = new FTPClient();
		
	    try
	    {
		    ftp.connect("192.168.0.2");
		    ftp.login("jyunda", "P@SSW0RD");
		    int reply = ftp.getReplyCode();
		    
		    if(!FTPReply.isPositiveCompletion(reply))
		    {
		    	ftp.disconnect();
		    	return INPUT;
		    }
		    
		    ftp.enterLocalPassiveMode();
		    ftp.setFileType(FTP.BINARY_FILE_TYPE);
		    ftp.changeWorkingDirectory("/home/jyunda/SRF/");
	        
		    FTPFile[] fs = ftp.listFiles();
	        
	        for (FTPFile ff : fs)
	        {
	        	if (ff.getName().equals(tranNo + "_" + attachName))
	        	{
	        		ftpFileExist = true;
	        		File dir = new File("D:/SRF/");
	        		
	        		if(!dir.exists())
	        		{
	        			dir.mkdirs();
	        		}
	        		
	        		File localFile = new File("D:/SRF/" + File.separator + ff.getName());
	        		OutputStream is = new FileOutputStream(localFile,true);
	        		ftp.setBufferSize(1024);
	        		
	        		if(ftp.setFileType(FTP.BINARY_FILE_TYPE))
	        		{
	        			ftp.retrieveFile(ff.getName(), is);
	        			is.close();
	        			this.addFieldError("downloadCheck", "附檔下載於D:/SRF/" + ff.getName());
	        		}
	        	}
	        }
	        
	        ftp.logout();
	        
	        if(!ftpFileExist)
	        {
	        	return INPUT;
	        }
	    }
	    
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    
	    finally
	    {
	    	if (ftp.isConnected())
	    	{
	    		try
	    		{
	    			ftp.disconnect();
	    		}
	    		
	    		catch (IOException ioe)
	    		{}
	    	}
	    }
	    
	    return SUCCESS;
	}
	
	public String deleteAttach()
	{
		if(!loginNo.equals(applyUser))
		{
			this.addFieldError("deleteCheck", "刪除附檔權限不足(申請人)！");
			return INPUT;
		}
		
		attachName = deleteAttach;

		FTPClient ftp = new FTPClient();
		
	    try
	    {
		    ftp.connect("192.168.0.2");
		    ftp.login("jyunda", "P@SSW0RD");
		    int reply = ftp.getReplyCode();
		    
		    if(!FTPReply.isPositiveCompletion(reply))
		    {
		    	ftp.disconnect();
		    }
		    
		    ftp.enterLocalPassiveMode();
		    ftp.setFileType(FTP.BINARY_FILE_TYPE);
		    ftp.changeWorkingDirectory("/home/jyunda/SRF/");
        	
		    try
		    {
		    	ftp.deleteFile(tranNo + "_" + attachName);
		    	tranService.deleteAttach(tranNo);
		    	this.addFieldError("deleteCheck", "單據"+ tranNo +"附檔" + attachName + "刪除成功！");
		    }
		    
		    catch(IOException e)
		    {
		    	this.addFieldError("deleteCheck", "查無附檔！");
		    }
        	
	        ftp.logout();
	    }
	    
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    
	    finally
	    {
	    	if (ftp.isConnected())
	    	{
	    		try
	    		{
	    			ftp.disconnect();
	    		}
	    		
	    		catch (IOException ioe)
	    		{}
	    	}
	    }
	    
	    return SUCCESS;
	}
	
	public String findIt()
	{
		itList = tranService.findIt();
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

	public int getTranClass()
	{
		return tranClass;
	}

	public void setTranClass(int tranClass)
	{
		this.tranClass = tranClass;
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

	public String getUserDept()
	{
		return userDept;
	}

	public void setUserDept(String userDept)
	{
		this.userDept = userDept;
	}

	public String getVideoNo()
	{
		return videoNo;
	}

	public void setVideoNo(String videoNo)
	{
		this.videoNo = videoNo;
	}

	public int getVideoPlace()
	{
		return videoPlace;
	}

	public void setVideoPlace(int videoPlace)
	{
		this.videoPlace = videoPlace;
	}

	public String getVideoDate()
	{
		return videoDate;
	}

	public void setVideoDate(String videoDate)
	{
		this.videoDate = videoDate;
	}

	public String getVideoContact()
	{
		return videoContact;
	}

	public void setVideoContact(String videoContact)
	{
		this.videoContact = videoContact;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}

	public String getPhoneDate()
	{
		return phoneDate;
	}

	public void setPhoneDate(String phoneDate)
	{
		this.phoneDate = phoneDate;
	}

	public String getPhoneContact()
	{
		return phoneContact;
	}

	public void setPhoneContact(String phoneContact)
	{
		this.phoneContact = phoneContact;
	}

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public String getAttachName()
	{
		return attachName;
	}

	public void setAttachName(String attachName)
	{
		this.attachName = attachName;
	}

	public String getDownloadAttach()
	{
		return downloadAttach;
	}

	public void setDownloadAttach(String downloadAttach)
	{
		this.downloadAttach = downloadAttach;
	}

	public String getDeleteAttach()
	{
		return deleteAttach;
	}

	public void setDeleteAttach(String deleteAttach)
	{
		this.deleteAttach = deleteAttach;
	}

	public String getDeleteAttachFile()
	{
		return deleteAttachFile;
	}

	public void setDeleteAttachFile(String deleteAttachFile)
	{
		this.deleteAttachFile = deleteAttachFile;
	}

	public String getApplyDate()
	{
		return applyDate;
	}

	public void setApplyDate(String applyDate)
	{
		this.applyDate = applyDate;
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

	public String getSupervisorNo()
	{
		return supervisorNo;
	}

	public void setSupervisorNo(String supervisorNo)
	{
		this.supervisorNo = supervisorNo;
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

	public String getItOwnerNo()
	{
		return itOwnerNo;
	}

	public void setItOwnerNo(String itOwnerNo)
	{
		this.itOwnerNo = itOwnerNo;
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

	public String getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}

	public String getReceiveDate()
	{
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate)
	{
		this.receiveDate = receiveDate;
	}

	public String getRejectDate()
	{
		return rejectDate;
	}

	public void setRejectDate(String rejectDate)
	{
		this.rejectDate = rejectDate;
	}

	public String getDoneDate()
	{
		return doneDate;
	}

	public void setDoneDate(String doneDate)
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

	public String getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(String updateDate)
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

	public String getLastStatus()
	{
		return lastStatus;
	}

	public void setLastStatus(String lastStatus)
	{
		this.lastStatus = lastStatus;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getFlowStatus()
	{
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus)
	{
		this.flowStatus = flowStatus;
	}

	public Date getFlowDate()
	{
		return flowDate;
	}

	public void setFlowDate(Date flowDate)
	{
		this.flowDate = flowDate;
	}

	public String getUpdateTran()
	{
		return updateTran;
	}

	public void setUpdateTran(String updateTran)
	{
		this.updateTran = updateTran;
	}

	public String getRejectTran()
	{
		return rejectTran;
	}

	public void setRejectTran(String rejectTran)
	{
		this.rejectTran = rejectTran;
	}

	public String getApproveTran()
	{
		return approveTran;
	}

	public void setApproveTran(String approveTran)
	{
		this.approveTran = approveTran;
	}

	public File getFileUpload()
	{
		return fileUpload;
	}

	public void setFileUpload(File fileUpload)
	{
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType()
	{
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType)
	{
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName()
	{
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName)
	{
		this.fileUploadFileName = fileUploadFileName;
	}

	public List<String> getItList()
	{
		return itList;
	}

	public void setItList(List<String> itList)
	{
		this.itList = itList;
	}

	public List getFlowList()
	{
		return flowList;
	}

	public void setFlowList(List flowList)
	{
		this.flowList = flowList;
	}

	public List<Tran> getTranList()
	{
		return tranList;
	}

	public void setTranList(List<Tran> tranList)
	{
		this.tranList = tranList;
	}

	public List<Video> getVideoList()
	{
		return videoList;
	}

	public void setVideoList(List<Video> videoList)
	{
		this.videoList = videoList;
	}

	public List<Phone> getPhoneList()
	{
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList)
	{
		this.phoneList = phoneList;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public TranService getTranService()
	{
		return tranService;
	}

	public void setTranService(TranService tranService)
	{
		this.tranService = tranService;
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
