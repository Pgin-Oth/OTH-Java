package verteiltesysteme.uebung04.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by chx34972 on 25.04.2017.
 */
public class Receiver extends Thread {
    private Client client;
    private boolean active = true;
    public Receiver(Client client) {
        this.client = client;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    @Override
    public void run() {
        try {
            InputStream in = client.getSocket().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (active) {
                String antwort = reader.readLine();
                System.out.println(antwort);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
