package slack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.reflect.Method;

public class HelloWorldImpl extends UnicastRemoteObject implements HelloWorld {
	private static final long serialVersionUID = 1L;

	protected HelloWorldImpl() throws RemoteException {
		super();
	}
	
	public String sayHello()  {
		String result = "Salut tout le monde ";	
		System.out.println("Methode sayHello invoqu�e");
		return result;
	}

	@Override
	public Resultat sayResultat() throws RemoteException {
		System.out.println("Methode sayResultat invoqu�e");
		Resultat r1 = new Resultat();
		return r1 ;
	}

	ServiceImpl simpl=new ServiceImpl(); // simpl n'existe qu'en un exemplaire
	@Override
	public Service logon() throws RemoteException {
		return simpl; // on renvoie au client un stub vers ServiceImpl
	}

}
