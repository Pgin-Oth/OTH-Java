package verteiltesysteme.uebung06.chat_server.chat.client.rmi;

public interface IMessageSender {
	public void openChatConnection(String username, String registry, String bindingName,
			IMessageGui messageGui);
	public void sendChatMessage(String message);
	public void closeChatConnection();
}
