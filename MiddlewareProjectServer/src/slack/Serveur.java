package slack;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Serveur {
    public static void main(String[] args){
        System.out.println("Creation de l'objet  serveur");
        System.out.println("System property codebase" + System.getProperty("java.rmi.server.codebase"));
        
        HelloWorld hellow=null;
        try {
            hellow = new HelloWorldImpl();
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        System.out.println("Referencement dans le registry...");
        try {
            Registry registry = LocateRegistry.createRegistry(2002);
            System.out.println("on a lacces au registry ecoutant sur 2002");
            registry.rebind("HelloWorld", hellow);
            // ou bien, en utilisant java.rmi.Naming
            // java.rmi.Naming.rebind("HelloWorld", hellow); // nom choisi dans l'annuaire, celui ci étant le registry ecoutant 
            // derrière le port 1099,  est HelloWorld
        } catch (RemoteException e) {
            // le registry lui même est un objet rmi, dont l'accès pourrait être impossible 
            // bien que peu probable car le registry s exécute sur la même machine que l'objet serveur
            // l'accès 'remote' est donc fait en local, ie entre 2 JVMs écoutant sur deux ports differents
            e.printStackTrace();
        }
        System.out.println("Attente d invocation, Ctrl-C pour stopper!");
    }
}
