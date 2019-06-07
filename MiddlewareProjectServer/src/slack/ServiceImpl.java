package slack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceImpl extends UnicastRemoteObject implements Service{

	Data data;
	
	public ServiceImpl(Data data) throws RemoteException {
		super();
		this.data = data;
	}

    @Override
    public String login(String username, String password) throws RemoteException {
        return data.login(username, password);
    }

    @Override
    public String getAllGroups(String username) throws RemoteException {
        return data.getUserGroups(username);
    }

    @Override
    public synchronized boolean subscribeGroup(String username, String group) throws RemoteException {
     // la m�thode est synchronized car interdit que plusieurs clients modifient val en meme temps
        data.updateSubscribed(username, group);
        try {
            Thread.sleep(500); // cela permet de simuler un traitement long sur val, qui
            // justifie la gestion de la concurrence des acc�s � val, par le mot synchronized
            // Avant d'avoir mis le mot synchronized, ce sleep permettait d'avoir une chance
            // de voir s'executer deux threads qui , en concurrence, modifient et lisent (return)
            // la valeur de val. Mettre ensuite le synchronized permet de s'assurer que ces
            // clients n'auront plus de tels probl�mes d'acc�s en concurrence � val
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.isSubscribed(username, group);
    }

    @Override
    public boolean isSubscribeGroup(String username, String group) throws RemoteException {
        return data.isSubscribed(username, group);
    }

}

