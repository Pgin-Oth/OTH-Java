package verteiltesysteme.uebung06.chat_server.chat_rmi_implementierung.server.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Server {
	
	private static Logger log = Logger.getLogger(Server.class.getCanonicalName());
	
	public static void main(String[] args) {
		try {
			// Starten der rmiregistry auf Port 1099 vom Programm aus (in selber JVM --> keine Classpath-Probleme)
			LocateRegistry.createRegistry(1099);
			// Objekt der Implementierung erzeugen
			IChatMessageHub messageHub = new ChatServer();
			// Remote-Stub-Objekt erzeugen lassen
			IChatMessageHub messageHubStub = (IChatMessageHub) UnicastRemoteObject.exportObject(messageHub, 0);
			// Verbindung zur Registry aufbauen (die oben gestartet wurde)
			Registry registry = LocateRegistry.getRegistry(1099);
			// Remote-Stub-Objekt in der Registry veröffentlichen
			registry.rebind("Chatserver", messageHubStub);
			
			log.info("Chatserver in Registry veröffentlicht");
		} catch (RemoteException e) {
			log.severe("Fehler bei Serverstart: " + e.getLocalizedMessage() );
		}
	}

}
