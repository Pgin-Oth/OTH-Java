package verteiltesysteme.uebung04.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chx34972 on 25.04.2017.
 */
public class Sender extends Thread {
    private Client client;
    private Receiver receiver;

    public Sender(Client client, Receiver receiver) {
        this.client = client;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        try {
            Socket socket = this.client.getSocket();
            String input = "";
            OutputStream out = null;
            out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            input = "OPEN#" + client.getUser();
            writer.println(input);
            writer.flush();
            while (true) {
                Scanner inputReader = new Scanner(System.in);
                input = inputReader.nextLine();
                writer.println(input);
                writer.flush();
                if (input.equals("EXIT")) {
                    this.receiver.setActive(false);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
