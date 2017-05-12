package verteiltesysteme.uebung06.frueherkennungs_service.normal.client.unir;

import verteiltesysteme.uebung06.frueherkennungs_service.normal.server.lmu.Bericht;
import verteiltesysteme.uebung06.frueherkennungs_service.normal.server.lmu.FrueherkennungIF;
import verteiltesysteme.uebung06.frueherkennungs_service.normal.server.lmu.Roentgenbild;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;


public class Client {

    public static void main(String[] args) {
        try {
            Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);
            try {

                // Stub von Registry laden
                FrueherkennungIF server = (FrueherkennungIF) verzeichnisdienst.lookup("LMU-Frueherkennungs-Service");
                // Parameter erzeugen (wird dann serialisiert und by-value an den Server uebertragen)
                Roentgenbild roentgenbild = new Roentgenbild( "Max Muster", new Date() );

                // Methode gegen Stub aufrufen; Aufruf erfolgt remote
                Bericht bericht = server.analysieren(roentgenbild);
                
                // Bericht wurde vom Server serialisiert und by-value zurück übertragen
                System.out.println("Bericht empfangen mit Datum="+ bericht.getDatum() + " und Diagnose=" + bericht.getDiagnose() + " und Vorgehen=" + bericht.getWeiteresVorgehen());

            } catch (NotBoundException | AccessException ex) {
                ex.printStackTrace();
            }
        } catch (RemoteException ex) {
            
        }
    }
    
}
