package verteiltesysteme.uebung06.chat_server.chat.server.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Server {
	
	private static Logger log = Logger.getLogger(Server.class.getCanonicalName());
	
	public static void main(String[] args) {
			// Starten der rmiregistry auf Port 1099 vom Programm aus (in selber JVM --> keine Classpath-Probleme)

			// Objekt der Implementierung erzeugen

			// Remote-Stub-Objekt erzeugen lassen

			// Verbindung zur Registry aufbauen (die oben gestartet wurde)

			// Remote-Stub-Objekt in der Registry veröffentlichen

            // Zur Ausgabe von Meldungen nutzen Sie bitte
            // log.info("..."); bzw.
            // log.severe("...");
	}

}
