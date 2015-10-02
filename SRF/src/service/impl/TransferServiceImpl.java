package service.impl;

import java.util.List;

import model.Phone;
import model.Video;
import service.TransferService;
import dao.TransferDAO;

public class TransferServiceImpl implements TransferService
{
	TransferDAO transferDAO;

	public TransferDAO getTransferDAO()
	{
		return transferDAO;
	}

	public void setTransferDAO(TransferDAO transferDAO)
	{
		this.transferDAO = transferDAO;
	}

	public String deptNameToNo(String deptName)
	{
		return transferDAO.deptNameToNo(deptName);
	}

	public String deptNoToName(int deptNo)
	{
		return transferDAO.deptNoToName(deptNo);
	}

	public String userNameToNo(int userDept, String userName)
	{
		return transferDAO.userNameToNo(userDept, userName);
	}

	public String userNoToName(String userNo)
	{
		return transferDAO.userNoToName(userNo);
	}

	public String classNoToName(int classNo)
	{
		return transferDAO.classNoToName(classNo);
	}
	
	public String classNameToNo(String className)
	{
		return transferDAO.classNameToNo(className);
	}

	public List<Video> videoNoToDetail(String videoNo)
	{
		return transferDAO.videoNoToDetail(videoNo);
	}

	public List<Phone> phoneNoToDetail(String phoneNo)
	{
		return transferDAO.phoneNoToDetail(phoneNo);
	}
	
	public String placeNoToName(int placeNo)
	{
		return transferDAO.placeNoToName(placeNo);
	}
	
	public String placeNameToNo(String placeName)
	{
		return transferDAO.placeNameToNo(placeName);
	}
	
	public String statusNoToName(int statusNo)
	{
		return transferDAO.statusNoToName(statusNo);
	}
	
	public String statusNameToNo(String statusName)
	{
		return transferDAO.statusNameToNo(statusName);
	}
	
	public String attachNoToName(String tranNo)
	{
		return transferDAO.attachNoToName(tranNo);
	}
}
