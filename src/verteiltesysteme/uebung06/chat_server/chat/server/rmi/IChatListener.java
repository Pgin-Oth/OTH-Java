package verteiltesysteme.uebung06.chat_server.chat.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatListener extends Remote {
	public void onMessage(String fromUser, String message, boolean isAdmin) throws RemoteException;
	public String getUsername() throws RemoteException;
}
