package dao;

import java.util.List;

import model.Phone;
import model.Video;

public interface TransferDAO
{
	public String deptNameToNo(String deptName);
	public String deptNoToName(int deptNo);
	public String userNameToNo(int userDept, String userName);
	public String userNoToName(String userNo);
	public String classNoToName(int classNo);
	public String classNameToNo(String className);
	public List<Video> videoNoToDetail(String videoNo);
	public List<Phone> phoneNoToDetail(String phoneNo);
	public String placeNoToName(int placeNo);
	public String placeNameToNo(String placeName);
	public String statusNoToName(int statusNo);
	public String statusNameToNo(String statusName);
	public String attachNoToName(String tranNo);
}
