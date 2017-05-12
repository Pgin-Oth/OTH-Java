package verteiltesysteme.uebung06.chat_server.chat.client.rmi;

public interface IMessageGui {
	public void showNewMessage(String fromUser, String message);
	public void showAdminMessage(String message);
}
