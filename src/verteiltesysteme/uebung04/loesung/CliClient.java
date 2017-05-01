package verteiltesysteme.uebung04.loesung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class CliClient {

   private int port;
   private String host;
   private Socket socket;
   private String username;
   private volatile boolean running = true; // volatile ist wie synchronized fuer Variablenzugriffe

   public CliClient(String host, int port, String username) throws Exception {
      this.host = host;
      this.port = port;
      this.username = username;
      this.socket = new Socket(host, port);
      new Thread(new Listener()).start();
      new Thread(new Sender()).start();
   }

   public static void main(String[] args) throws Exception {
      new CliClient(args[0], Integer.parseInt(args[1]), args[2]);
   }

   class Sender implements Runnable {
      @Override
      public void run() {
         /*  Das Senden muss nicht notwendigerweise in ein eigenes Runnable.
          *  Theoretisch koennte dies alles auch in der main-Methode erfolgen (ohne eine CliClient-Instanz).
          */
         try {
            Scanner tastatur = new Scanner(System.in);
            PrintWriter writer = new PrintWriter(CliClient.this.socket.getOutputStream());

            writer.println("OPEN#" + CliClient.this.username);
            writer.flush();
            System.out.println(" >>> User '" + CliClient.this.username + "' wurde am Server angemeldet.");

            while (CliClient.this.running) {
               String input = tastatur.nextLine();
               if (input.equals("EXIT")) {
                  writer.println("EXIT");
                  writer.flush();
                  CliClient.this.socket.close(); // sofern der Server das Socket nicht schliesst
                  System.out.println(" >>> User wurde abgemeldet und Socket geschlossen. Auf Wiedersehen.");
                  CliClient.this.running = false;
               } else if (input.startsWith("PUBL#")) {
                  writer.println(input);
                  writer.flush();
               } else {
                  System.out.println(" >>> Ihre Eingabe '" + input + "' entspricht nicht dem Protokoll und wurde ignoriert.");
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         System.out.println(" >>> Sender beendet");
      }
   }

   class Listener implements Runnable {
      @Override
      public void run() {
         try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(CliClient.this.socket.getInputStream()));
            while(CliClient.this.running) {
               String input = reader.readLine(); // erzeugt SocketException, falls das Socket geschlossen wird
               if(input != null) {
                  if(input.startsWith("EXIT")) {
                     CliClient.this.running = false;
                     System.out.println(" >>> Server hat die Chat-Verbindung beendet.");
                     CliClient.this.socket.close();
                  }
                  else
                     System.out.println(" >>> EMPFANGEN: " + input);
               }
               else {

               }
            }
         }
         catch(Exception e) {
            //e.printStackTrace();
         }
         System.out.println(" >>> Listener beendet");
      }
   }
}
