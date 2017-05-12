package verteiltesysteme.uebung06.chat_server.chat_rmi_implementierung.client.rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.logging.Logger;

import verteiltesysteme.uebung06.chat_server.chat_rmi_implementierung.server.rmi.IChatListener;
import verteiltesysteme.uebung06.chat_server.chat_rmi_implementierung.server.rmi.IChatMessageHub;

public class ClientEndpoint implements IChatListener, IMessageSender {

	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	private IMessageGui messageGui;
	private IChatMessageHub hub;
	private String username;
	private IChatListener thisStub;

	public ClientEndpoint() {
		
	}

	
	/*
	 * Methoden des Interface  IMessageSender
	 * (werden von GUI aufgerufen)
	 */

	@Override
	public void openChatConnection(String username, String registryHost, String bindingName, IMessageGui messageGui) {
		this.messageGui = messageGui;
		this.username = username;
		try {
			// Registry-Verbindung aufbauen
			Registry registry = LocateRegistry.getRegistry(registryHost, 1099);
			hub = (IChatMessageHub) registry.lookup(bindingName);
			thisStub = (IChatListener) UnicastRemoteObject.exportObject(this, 0);
			hub.addChatListener(thisStub);
		} catch (RemoteException | NotBoundException e) {
			log.warning("Chat-Connection konnte nicht geoeffnet werden: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void sendChatMessage(String message) {
		try {
			hub.publish(username, message, false);
			log.info("Message gesendet: " + message);
		} catch (RemoteException e) {
			log.warning("Chat-Message konnte nicht gesendet werden: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void closeChatConnection() {
		try {
			hub.removeChatListener(thisStub);
		} catch (RemoteException e) {
			log.warning("Chat-Connection konnte nicht geschlossen werden: " + e.getLocalizedMessage());
		}
	}

	
	
	/*
	 * Methoden des Interface  IChatListener
	 * (werden _remote_ vom MessageHub aufgerufen)
	 */

	@Override
	public void onMessage(String fromUser, String message, boolean isAdmin)
			throws RemoteException {
		if(!isAdmin)
			messageGui.showNewMessage(fromUser, message);
		else
			messageGui.showAdminMessage(message);
	}


	@Override
	public String getUsername() throws RemoteException {
		return username;
	}

}
