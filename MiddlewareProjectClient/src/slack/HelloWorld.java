package slack;

import java.rmi.*;

public interface HelloWorld extends Remote {
	public String sayHello() throws RemoteException;
	// on d�clare ici que sayHello peut jeter l exception, et donc, meme pas besoin
	// de le rajouter dans le code d 'implementation de sayHello car Remote est une I/O Exception

	public Resultat sayResultat() throws RemoteException;
	public Service logon() throws RemoteException;
}
