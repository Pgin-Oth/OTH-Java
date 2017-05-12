package verteiltesysteme.uebung06.chat_server.chat_rmi_implementierung.client.rmi;

public class Client {

	public static void main(String[] args)  {
		new ChatGui(new ClientEndpoint());
	}

}
