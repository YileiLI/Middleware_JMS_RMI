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


//		System.out.println("Recherche de l objet serveur");
//		String myname = args[0];
	    String username = "";
	    String password = "";
	    String pseudo = null;

		if (System.getSecurityManager() == null) { 
			System.setSecurityManager(new SecurityManager()); 
		} // et plus RMISecurityManager devenu obsolete
		/*
		On peut remplacer ces lignes ci dessus en �crivant -Djava.security.manager 
		et pour debugger -Djava.security.debug=policy */
		Service service=null; // hw est un stub vers l objet remote, renseign� par le lookup
		try {
			// rechercher sur cette machine, localhost, un objet remote de nom HelloWorld 			
			Object o=Naming.lookup("rmi://127.0.0.1:2002/Service");
			//System.out.println("proxy recu est " + o);
			service = (Service) o;
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
		
		try {
		    //log in
		    boolean flag = true;
		    java.io.BufferedReader stdin =
	                new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		    if (args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i];


                    if (arg.equals("-u")) {
                        if (!(i == args.length - 1 || args[i+1].startsWith("-"))) {
                            username = args[++i];
                        }
                        continue;
                    }

                    if (arg.equals("-p")) {
                        if (!(i == args.length - 1 || args[i+1].startsWith("-"))) {
                            password = args[++i];
                            
                            }
                        }
                        
                        continue;
                    }
            }
		    boolean flag2 = false;
		    while (flag) {
		        
                    if (username.equals("")||flag2) {
                        System.out.println("Please enter the username:");
                        String s = stdin.readLine();

                        if (s.length()>0)
                        {
                            username = s;
                        }
                    }
                    if (password.equals("")||flag2) {
                        System.out.println("Please enter the password:");
                        String s = stdin.readLine();

                        if (s.length()>0)
                        {
                            password = s;
                        }
                    }
                
                //verify the username and password
	            pseudo = service.login(username, password);
	            if (pseudo != null) {
                    flag = false;
                    System.out.println("Welcome back " + pseudo);
                }else {
                    System.out.println("Please try again. \n");
                    flag2 = true;
                }
                
            }
		    
		    //show menu
		    while (true) {
		        StringBuffer menu = new StringBuffer();
		        menu.append("==================MENU=========================\n");
		        menu.append("options:\n");
		        menu.append("  1 - to see all the groups existed\n");
		        menu.append("  2 - to subscribe a group\n");
		        menu.append("  3 - open one group discussion\n");
		        menu.append("  CTRL + C to exit\n");
		        menu.append("===========Enter the option number=============\n");
		        System.out.println(menu);
		        stdin =new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		        String opt;
		        try {
		            opt = stdin.readLine();
		            switch (Integer.parseInt(opt)) {
	                case 1:
	                    System.out.println(service.getAllGroups(username));
	                    break;
	                    
	                case 2:
	                    System.out.println("Enter the group name to subscrib");    
	                    String gr = stdin.readLine();
	                    if (service.subscribeGroup(username, gr)) {
	                        DurableChat durableChat = new DurableChat();
	                        durableChat.DurableChatter (pseudo, password, gr, false);
	                        System.out.println(gr + " subscribed √");
                        } else {
                            System.out.println(gr + " not subscribed. Maybe try later.");
                        }
	                    break;
	                case 3:
	                    System.out.println("Enter the group name to open");
	                    gr = stdin.readLine();
                        if (service.isSubscribeGroup(username, gr)) {
                            DurableChat durableChat = new DurableChat();
                            durableChat.DurableChatter (pseudo, password, gr, true);

                        } else {
                            System.out.println(gr + " not subscribed. Please cubsribe first.");
                        }
	                    break;
	                    
	                default:
	                    System.out.println("Please try again.");
	                    break;
	                }
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		        
                
            }
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		

	}
}