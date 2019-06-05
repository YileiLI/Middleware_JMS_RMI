package slack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceImpl extends UnicastRemoteObject implements Service{

	
	int val=1;
	public ServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public int getVal() throws RemoteException {
		return val;
	}
	
	@Override
	public  synchronized  int  setVal(int v, String cname) throws RemoteException {
		// la m�thode est synchronized car interdit que plusieurs clients modifient val en meme temps
		val=val*v;
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
		System.out.println("Thread:"+Thread.currentThread().getName()+" val renvoyee :" + val + " au Client "+cname);
		return val;
	}

}

