package slack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;


public class Client {

	public static void main(String[] args){


		System.out.println("Recherche de l objet serveur");
		String myname = args[0];

		if (System.getSecurityManager() == null) { 
			System.setSecurityManager(new SecurityManager()); 
		} // et plus RMISecurityManager devenu obsolete
		/*
		On peut remplacer ces lignes ci dessus en �crivant -Djava.security.manager 
		et pour debugger -Djava.security.debug=policy */
		HelloWorld hw=null; // hw est un stub vers l objet remote, renseign� par le lookup
		try {
			// rechercher sur cette machine, localhost, un objet remote de nom HelloWorld 			
			Object o=Naming.lookup("rmi://127.0.0.1:2002/HelloWorld");
			//System.out.println("proxy recu est " + o);
			hw = (HelloWorld) o;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rs=null;
		Resultat r=null;
		try {
			rs = hw.sayHello();
			r = hw.sayResultat();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Resultat de l'appel via le remote objet trouv� sur le registry 2002  est "+rs);
		System.out.println("Resultat de l'appel via le remote objet trouv� sur le registry 2002  est "+r);
		try {
			FileOutputStream fo=new FileOutputStream(new File("essai"));
			fo.write(2);
			// si on n a pas de permission de write fichier, ce write ne fonctionne pas 
			// Il faut que nous ayons un securityManager activ� et les bonnes permissions
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Service s=null; 		
		try {
			s=hw.logon();
			System.out.println("On a recu une ref vers service distant "+s );
			for (int i=1;i<14;i++){
				int res=s.setVal(2,myname); // myname est une chaine == son nom
				Thread.sleep(1000);
				System.out.println("Thread "+Thread.currentThread().getName() +" Valeur rendue par le service"+res);
			}
			}catch (Exception e) {
			e.printStackTrace();
		}
	}
}