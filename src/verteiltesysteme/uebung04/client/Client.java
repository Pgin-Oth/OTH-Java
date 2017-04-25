package verteiltesysteme.uebung04.client;

import javax.sound.midi.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private Socket socket;
    private String server;
    private int port;
    private String user;

    public Client(Socket socket) {
        super();
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public static void main(String[] args) {

        int port;
        String server, user;

        // Try to read configuration from command line parameters
        try {
            server = args[1];
            port = Integer.valueOf(args[2]);
            user = args[3];
        } catch (ArrayIndexOutOfBoundsException ex) {
            // No parameter was set, use default
            server = "127.0.0.1";
            port = 1234;
            user = "XiChen";
        }
        try {
            Socket socket = new Socket(server, port);
            Client client = new Client(socket);
            client.setUser(user);
            Receiver receiver = new Receiver(client);
            Sender sender = new Sender(client, receiver);
            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
