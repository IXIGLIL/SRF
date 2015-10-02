package action;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import model.User;
import service.DeptService;
import service.TransferService;
import service.UserService;
import service.ValidateService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport
{
	public String userNo;
	public String userPwd;
	public String reUserPwd;
	public int userDept;
	public String deptName;
	public String userMail;
	public Date createDate;
	public String userName;
	public String supervisor;
	public String register = "";
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private UserService userService;
	private DeptService deptService;
	private TransferService transferService;
	private ValidateService validateService;
	
	private List<String> deptList = new ArrayList<String>();
	private List<String> supervisorList = new ArrayList<String>();

	private static KeyGenerator keygen;
	private static SecretKey secretKey;
	private static SecretKeySpec secretKeySpec;
	private static Cipher c;
	
	public String execute() throws Exception
	{
		Date date = new Date();
		date = new Date(date.getTime());

		User user = new User();
		user.setUserNo(userNo);
		user.setUserPwd(enccode(userPwd).replace("\"", " ").trim());
		user.setUserName(userName);
		user.setReUserPwd(enccode(reUserPwd).replace("\"", " ").trim());
		user.setUserDept(userDept);
		user.setUserMail(userMail);
		user.setCreateDate(date);
		user.setSupervisor(this.transferService.userNameToNo(userDept, supervisor).toString().replace('[', ' ').replace(']', ' ').trim());
		user.setHadApply(0);
		user.setWaitApprove(0);
		user.setWaitDone(0);
		user.setWaitCheck(0);
		user.setBeenReject(0);
		deptName = transferService.deptNoToName(userDept).toString().replace('[', ' ').replace(']', ' ').trim();
		
		this.userService.register(user);
		return SUCCESS;
	}
	
	public static String enccode (String userPwd) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		keygen = KeyGenerator.getInstance("AES");
		keygen.init(128, new SecureRandom("0091952".getBytes()));
		secretKey = keygen.generateKey();
		secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
		c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		String enccode = new BASE64Encoder().encodeBuffer(c.doFinal(userPwd.getBytes()));

		return enccode;
	}
	
	public static String deccode (String code) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		byte[] decode = new BASE64Decoder().decodeBuffer(code);
		keygen = KeyGenerator.getInstance("AES");
		keygen.init(128, new SecureRandom("0091952".getBytes()));
		secretKey = keygen.generateKey();
		secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
		c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, secretKeySpec);
		String deccode = new String(c.doFinal(decode));

		return deccode;
	}
	
	@Override
	public void validate()
	{
		findDept();
		
		if("".equals(userNo))
		{
			this.addFieldError("userNo", "工號不可為空！");
		}
		
		if("".equals(userPwd))
		{
			this.addFieldError("userPwd", "密碼不可為空！");
		}
		
		if("".equals(reUserPwd))
		{
			this.addFieldError("reUserPwd", "重複密碼不可為空！");
		}
		
		if("".equals(userName))
		{
			this.addFieldError("userNo", "姓名不可為空！");
		}
		
		if("".equals(userDept))
		{
			this.addFieldError("userDept", "部門不可為空！");
		}
		
		if("".equals(userMail))
		{
			this.addFieldError("userMail", "公司郵件不可為空！");
		}
		
		if("".equals(supervisor))
		{
			this.addFieldError("supervisor", "主管不可為空！");
		}
		
		if(!"".equals(userNo) && validateService.hasUserNo(userNo))
		{
			this.addFieldError("userNo", "此工號已註冊！");
		}
		
		if(!"".equals(register))
		{
			if(!"".equals(userNo) && (userNo.length() != 7))
			{
				this.addFieldError("userNo", "工號長度不為7！");
			}
			
			if(!"".equals(userPwd) && !"".equals(reUserPwd) && !userPwd.equals(reUserPwd))
			{
				this.addFieldError("reUserPwd", "密碼輸入不一致！");
			}
		}
	}
	
	public String findDept()
	{
		deptList = deptService.findDept();
		return SUCCESS;
	}
	
    public String findSupervisor()
    {
    	supervisorList = userService.findSupervisor(userDept);
    	return SUCCESS;
	}
	
	public String register(User user)
	{
		userService.register(user);
		return SUCCESS;
	}

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

	public String getRegister()
	{
		return register;
	}

	public void setRegister(String register)
	{
		this.register = register;
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

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public DeptService getDeptService()
	{
		return deptService;
	}

	public void setDeptService(DeptService deptService)
	{
		this.deptService = deptService;
	}

	public TransferService getTransferService()
	{
		return transferService;
	}

	public void setTransferService(TransferService transferService)
	{
		this.transferService = transferService;
	}

	public ValidateService getValidateService()
	{
		return validateService;
	}

	public void setValidateService(ValidateService validateService)
	{
		this.validateService = validateService;
	}

	public List<String> getDeptList()
	{
		return deptList;
	}

	public void setDeptList(List<String> deptList)
	{
		this.deptList = deptList;
	}

	public List<String> getSupervisorList()
	{
		return supervisorList;
	}

	public void setSupervisorList(List<String> supervisorList)
	{
		this.supervisorList = supervisorList;
	}

	public static KeyGenerator getKeygen()
	{
		return keygen;
	}

	public static void setKeygen(KeyGenerator keygen)
	{
		RegisterAction.keygen = keygen;
	}

	public static SecretKey getSecretKey()
	{
		return secretKey;
	}

	public static void setSecretKey(SecretKey secretKey)
	{
		RegisterAction.secretKey = secretKey;
	}

	public static SecretKeySpec getSecretKeySpec()
	{
		return secretKeySpec;
	}

	public static void setSecretKeySpec(SecretKeySpec secretKeySpec)
	{
		RegisterAction.secretKeySpec = secretKeySpec;
	}

	public static Cipher getC()
	{
		return c;
	}

	public static void setC(Cipher c)
	{
		RegisterAction.c = c;
	}
}