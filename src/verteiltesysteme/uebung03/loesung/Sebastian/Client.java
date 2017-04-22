package verteiltesysteme.uebung03.loesung.Sebastian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Sebastian
 */
public class Client {

    public Client() {
        super();
    }

    public static void main(String[] args) {

        int port;
        String server;

        // Try to read configuration from command line parameters
        try {
            server = args[1];
            port = Integer.valueOf(args[2]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            // No parameter was set, use default
            server = "localhost";
            port = 1234;
        }

        try {
            while (true) {
                Socket socket = new Socket(server, port);
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                PrintWriter writer = new PrintWriter(out);

                // Read hello from Server
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String antwort = reader.readLine();
                System.out.println(antwort);

                // Write hello to Server
                writer.println("Hallo Server");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
