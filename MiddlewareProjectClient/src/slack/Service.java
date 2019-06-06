package slack;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 我们用这个吧
public interface Service extends Remote {
	public boolean login(String username, String password); //成功登陆了才有以下操作及建立durablechat
	public String login(String username, String password); // h或者成功登陆了我们就返回DEFAULT_BROKER_NAME 给她让他知道订阅哪个 在durable的 line62
	// 关于group的列表
	// 哪个user有哪些groups 从一个我们已经知道的文件或者map获取吧
	public List<String> getMyGroups(String username);  // 这个要吗 还是当用户进入新的group的时候我们去update他的group list，再判断他加入的list有没有这个group
	public List<String> getAllGroups();
	public void updateMygGroupe(String newGroup);
	public void lofoff() //这个应该不用吧？
	/*
	int getVal() throws RemoteException;
	int setVal(int v, String cname) throws RemoteException;
	// le service offert consiste � multiplier val par facteur v 
}
