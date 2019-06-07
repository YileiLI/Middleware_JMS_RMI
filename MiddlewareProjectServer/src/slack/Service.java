package slack;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Service extends Remote {
    String login(String username, String password)throws RemoteException;//retourner le pseudo
    String getAllGroups(String username) throws RemoteException;//retourner la liste de groupes
    boolean subscribeGroup(String username, String group) throws RemoteException;//subscribe un nouveau groupe
    boolean isSubscribeGroup(String username, String group) throws RemoteException;
}
