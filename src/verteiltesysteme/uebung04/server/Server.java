package verteiltesysteme.uebung04.server;

import com.sun.deploy.util.SessionState;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket servSocket;
    public Server(ServerSocket servSocket) {
        this.servSocket = servSocket;
    }

    public ServerSocket getServSocket(){
        return this.servSocket;
    }
    public static void main(String[] args) {
        int port;
        try {
            port = Integer.valueOf(args[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            // Parameter is not set, use default one
            port = 1234;
        }

        try {
            ServerSocket servSocket = new ServerSocket(port);
            Server server = new Server(servSocket);
            Hub hub = new Hub(server);
            Aceptor aceptor = new Aceptor(server);
            ClientEndPoint clientEndPoint = new ClientEndPoint(server);



            while (true) {
                try (Socket s = server.getServSocket().accept()) {
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream();

                    PrintWriter writer = new PrintWriter(out);

                    // Write Hello to client
                    writer.println("Hallo Client");
                    writer.flush();

                    // Read answer from Client
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String antwort = reader.readLine();
                    System.out.println(antwort);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
