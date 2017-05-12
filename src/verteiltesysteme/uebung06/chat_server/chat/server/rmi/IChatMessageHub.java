package verteiltesysteme.uebung06.chat_server.chat.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatMessageHub extends Remote {
	public void addChatListener(IChatListener listener) throws RemoteException;
	public void removeChatListener(IChatListener listener) throws RemoteException;
	public void publish(String fromUser, String message, boolean isAdmin) throws RemoteException;
}
