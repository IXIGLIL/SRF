package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Attach;
import model.Flow;
import model.Phone;
import model.Tran;
import model.Video;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import service.TranService;
import service.TransferService;
import service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class CreateTranAction extends ActionSupport
{
	private String loginNo;
	private String tranNo;
	private String userNo;
	private int tranClass;
	private String tranClassName;
	private String applyDate;
	private String userDept;
	private String userName;
	private String supervisor;
	private String videoNo;
	private String videoDate;
	private int videoPlace;
	private String videoContact;
	private String phoneNo;
	private String phoneDate;
	private String phoneContact;
	private String title;
	private String format;
	private String memo;
	private String attach;
	private String requestDate;
	private String active;

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private List<String> classList = new ArrayList<String>();
	private List<String> placeList = new ArrayList<String>();
	
	UserService userService;
	TranService tranService;
	TransferService transferService;

	@Override
	public String execute() throws Exception
	{
		Date date = new Date();
		date = new Date(date.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		int dept = Integer.parseInt(transferService.deptNameToNo(userDept).toString().replace('[', ' ').replace(']', ' ').trim());
		
		Tran tran = new Tran();
		tran.setTranNo(tranService.findTranNo().toString().replace('[', ' ').replace(']', ' ').trim());
		tran.setUserNo(transferService.userNameToNo(dept, userName).toString().replace('[', ' ').replace(']', ' ').trim());
		tran.setTranClass(tranClass);
		tran.setTranClassName(transferService.classNoToName(tranClass));
		tran.setApplyDate(date);
		tran.setUserDept(dept);
		tran.setSupervisor(transferService.userNameToNo(dept, supervisor).toString().replace('[', ' ').replace(']', ' ').trim());
		tran.setTitle(title);
		tran.setFormat(format);
		tran.setMemo(memo);
		tran.setAttach("N");
		tran.setLastStatus(0);
		tran.setStatus(1);
		tran.setActive("Y");
		tran.setUpdateUser(tran.getUserNo());
		tran.setUpdateDate(date);
		
		if(tran.getTranClass() == 6)
		{
			tran.setVideoNo(tranService.findVideoNo().toString().replace('[', ' ').replace(']', ' ').trim());
			
			Video video = new Video();
			video.setVideoNo(tran.getVideoNo());			
			video.setVideoPlace(videoPlace);
			video.setVideoContact(videoContact);
			
			try
			{
				video.setVideoDate(sdf.parse(videoDate));
			}
			
			catch (Exception e)
			{
				this.addFieldError("videoDateFormat", "視訊會議日期格式不符！");
				return INPUT;
			}
			
			tranService.createVideo(video);
		}

		if(tran.getTranClass() == 7)
		{
			tran.setPhoneNo(tranService.findPhoneNo().toString().replace('[', ' ').replace(']', ' ').trim());
			
			Phone phone = new Phone();
			phone.setPhoneNo(tran.getPhoneNo());
			phone.setPhoneContact(phoneContact);
			
			try
			{
				phone.setPhoneDate(sdf.parse(phoneDate));
			}
			
			catch (Exception e)
			{
				this.addFieldError("videoDateFormat", "電話會議日期格式不符！");
				return INPUT;
			}

			tranService.createPhone(phone);
		}
		
		if(!requestDate.isEmpty() && requestDate != null)
		{
			try
			{
				tran.setRequestDate(sdf.parse(requestDate + " 00:00:00"));
			}
			
			catch (Exception e)
			{
				this.addFieldError("requestDateFormat", "需求日期格式不符！");
				return INPUT;
			}
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
			    
				Attach attach = new Attach();
				attach.setTranNo(tran.getTranNo());
				attach.setFileName(fileUploadFileName);
				
				tranService.createAttach(attach);
				
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

//		if(!(attachFile == null))		// 處理附檔
//		{
//			try
//			{
//				File destFile = new File("//files/資訊處/個人資料(Personal)/David/SRF/", tran.getTranNo() + "_" + attachName);
//				FileUtils.copyFile(attachFile, destFile);
//				
//				tran.setAttach("Y");
//				
//				Attach attach = new Attach();
//				attach.setTranNo(tran.getTranNo());
//				attach.setFileName(attachName);
//				
//				tranService.createAttach(attach);
//			}
//			
//			catch (IOException e)
//			{
//				e.printStackTrace();
//				return INPUT;
//			}
//		}

		tranNo = tran.getTranNo();
		applyDate = sdf.format(tran.getApplyDate());
		videoNo = tran.getVideoNo();
		phoneNo = tran.getPhoneNo();
		tranClassName = transferService.classNoToName(tran.getTranClass()).replace("[", " ").replace("]", " ").trim();
		
		Flow flow = new Flow();
		flow.setFlowDate(tran.getApplyDate());
		flow.setFlowStatus(tran.getStatus());
		flow.setTranNo(tranNo);
		
		userService.updateHadApply(tran.getUserNo(), "plus");			// 申請人申請+1
		userService.updateWaitApprove(tran.getSupervisor(), "plus");	// 主管待審核+1
		tranService.createTran(tran);
		tranService.createFlow(flow);

		return SUCCESS;
	}

	@Override
	public void validate()
	{
		findClass();
		findPlace();

		if ("".equals(title))
		{
			this.addFieldError("title", "主旨不可為空！");
		}
		
		if(tranClass == 0 || tranClass == -1)
		{
			this.addFieldError("tranClass", "分類不可為空！");
		}

		if (tranClass == 6 && ("".equals(videoDate)))
		{
			this.addFieldError("videoDate", "視訊會議日期不可為空！");
		}

		if (tranClass == 6 && videoPlace == -1)
		{
			this.addFieldError("videoPlace", "視訊會議地點不可為空！");
		}

		if (tranClass == 6 && ("".equals(videoContact)))
		{
			this.addFieldError("videoContact", "視訊會議聯絡人不可為空！");
		}

		if (tranClass == 7 && ("".equals(phoneDate)))
		{
			this.addFieldError("phoneDate", "電話會議日期不可為空！");
		}

		if (tranClass == 7 && ("".equals(phoneContact)))
		{
			this.addFieldError("phoneContact", "電話會議連絡人不可為空！");
		}

		if ("".equals(requestDate))
		{
			this.addFieldError("requestDate", "需求日期不可為空！");
		}

		if (tranClass > 0 && tranClass != 6 && ((!"".equals(videoDate)) || (videoPlace > 0) || (!"".equals(videoContact))))
		{
			this.addFieldError("notVideo", "分類非視訊會議！");
		}

		if (tranClass > 0 && tranClass != 7 && ((!"".equals(phoneDate)) || (!"".equals(phoneContact))))
		{
			this.addFieldError("notPhone", "分類非電話會議！");
		}
	}

	public String findClass()
	{
		classList = tranService.findClass();
		return SUCCESS;
	}

	public String findPlace()
	{
		placeList = tranService.findPlace();
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

	public String getUserNo()
	{
		return userNo;
	}

	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
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

	public String getApplyDate()
	{
		return applyDate;
	}

	public void setApplyDate(String applyDate)
	{
		this.applyDate = applyDate;
	}

	public String getUserDept()
	{
		return userDept;
	}

	public void setUserDept(String userDept)
	{
		this.userDept = userDept;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String supervisor)
	{
		this.supervisor = supervisor;
	}

	public String getVideoNo()
	{
		return videoNo;
	}

	public void setVideoNo(String videoNo)
	{
		this.videoNo = videoNo;
	}

	public String getVideoDate()
	{
		return videoDate;
	}

	public void setVideoDate(String videoDate)
	{
		this.videoDate = videoDate;
	}

	public int getVideoPlace()
	{
		return videoPlace;
	}

	public void setVideoPlace(int videoPlace)
	{
		this.videoPlace = videoPlace;
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

	public String getAttach()
	{
		return attach;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public String getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
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

	public List<String> getClassList()
	{
		return classList;
	}

	public void setClassList(List<String> classList)
	{
		this.classList = classList;
	}

	public List<String> getPlaceList()
	{
		return placeList;
	}

	public void setPlaceList(List<String> placeList)
	{
		this.placeList = placeList;
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