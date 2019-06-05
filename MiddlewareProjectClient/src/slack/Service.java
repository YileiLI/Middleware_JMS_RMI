package slack;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote {
	int getVal() throws RemoteException;
	int setVal(int v, String cname) throws RemoteException;
	// le service offert consiste � multiplier val par facteur v 
}
