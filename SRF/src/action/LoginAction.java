package action;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import model.User;
import service.UserService;
import service.ValidateService;
import sun.misc.BASE64Encoder;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport
{
	private String loginNo;
	private String userNo;
	private String userPwd;
	private int hadApply;
	private int waitApprove;
	private int waitDone;
	private int waitCheck;
	private int beenReject;
	
	private static KeyGenerator keygen;
	private static SecretKey secretKey;
	private static SecretKeySpec secretKeySpec;
	private static Cipher c;
	
	List<User> user ;
	
	UserService userService;
	ValidateService validateService;
	
	@Override
	public String execute() throws Exception
	{
		user = userService.findUser(userNo);
		hadApply = user.get(0).getHadApply();
		waitApprove = user.get(0).getWaitApprove();
		waitDone = user.get(0).getWaitDone();
		waitCheck = user.get(0).getWaitCheck();
		beenReject = user.get(0).getBeenReject();
		
		return SUCCESS;
	}
	
	@Override
	public void validate()
	{
		if(userNo == null || userNo.isEmpty())
		{
			this.addFieldError("userNo", "工號不可為空！");
		}
		
		if(userPwd == null || userPwd.isEmpty())
		{
			this.addFieldError("userPwd", "密碼不可為空！");
		}

		if (!validateService.hasUserNo(userNo))
		{
			this.addFieldError("userNo", "工號未註冊！");
		}
		
		try
		{
			if (!validateService.hasUserPwd(userNo, enccode(userPwd).replace("\"", " ").trim()))
			{
				this.addFieldError("userPwd", "密碼錯誤！");
			}
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
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

	public String getLoginNo()
	{
		return loginNo;
	}

	public void setLoginNo(String loginNo)
	{
		this.loginNo = loginNo;
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

	public static KeyGenerator getKeygen()
	{
		return keygen;
	}

	public static void setKeygen(KeyGenerator keygen)
	{
		LoginAction.keygen = keygen;
	}

	public static SecretKey getSecretKey()
	{
		return secretKey;
	}

	public static void setSecretKey(SecretKey secretKey)
	{
		LoginAction.secretKey = secretKey;
	}

	public static SecretKeySpec getSecretKeySpec()
	{
		return secretKeySpec;
	}

	public static void setSecretKeySpec(SecretKeySpec secretKeySpec)
	{
		LoginAction.secretKeySpec = secretKeySpec;
	}

	public static Cipher getC()
	{
		return c;
	}

	public static void setC(Cipher c)
	{
		LoginAction.c = c;
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

	public ValidateService getValidateService()
	{
		return validateService;
	}

	public void setValidateService(ValidateService validateService)
	{
		this.validateService = validateService;
	}
}
