package verteiltesysteme.uebung06.frueherkennungs_service.callback.client.unir;

import verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu.BerichtCallbackIF;
import verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu.FrueherkennungIF;
import verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu.Roentgenbild;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;


public class Client {

    public static void main(String[] args) {
        try {
            Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);

            System.out.println("Registry = " + verzeichnisdienst);
            // Stub von Registry laden
            FrueherkennungIF server = (FrueherkennungIF) verzeichnisdienst.lookup("LMU-Frueherkennungs-Service");
            System.out.println("Server = " + server);
            // Parameter erzeugen (wird dann serialisiert und by-value an den Server uebertragen)
            Roentgenbild roentgenbild = new Roentgenbild( "Max Muster", new Date() );

            // Callback-Objekt implementieren und...
            BerichtCallbackIF callbackImpl = new BerichtCallback();
            // ...Stub generieren usw.
            BerichtCallbackIF callbackStub = (BerichtCallbackIF) UnicastRemoteObject.exportObject(callbackImpl, 0);

            // Methode gegen Stub aufrufen; Aufruf erfolgt remote
            System.out.println("vor analysieren");
            server.analysieren(roentgenbild, callbackStub);
            System.out.println("Roentgenbild wurde an Server uebertragen; Bericht wird uns spaeter ueber Callback zugestellt... bitte warten...");


        } catch (RemoteException | NotBoundException ex) {
                ex.printStackTrace();
        } 
    }
    
}
