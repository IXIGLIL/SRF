package dao;

import java.util.List;

import model.User;

public interface UserDAO
{
	public List<User> findUser (String userNo);
	public List<String> findSupervisor (int dept);
	public void register(User user);
	public void updateHadApply(String userNo, String type);
	public void updateWaitApprove(String userNo, String type);
	public void updateWaitDone(String userNo, String type);
	public void updateWaitCheck(String userNo, String type);
	public void updateBeenReject(String userNo, String type);
}
