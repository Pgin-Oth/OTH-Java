package verteiltesysteme.uebung06.frueherkennungs_service.callback.client.unir;

import verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu.Bericht;
import verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu.BerichtCallbackIF;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class BerichtCallback implements BerichtCallbackIF { // und somit ein Remote!

    @Override
    public void uebertrageBericht(Bericht bericht) throws RemoteException {
        
        // Bericht wurde vom Server serialisiert und mit hilfe des Callback-Stub
        // zurueck an den Client (mich!) uebertragen
        System.out.println("Bericht empfangen mit Datum="+ bericht.getDatum() + " und Diagnose=" + bericht.getDiagnose() + " und Vorgehen=" + bericht.getWeiteresVorgehen());


        // kleines Hilfskonstrukt, damit der Client sich wieder beendet ohne Fehlermeldungen auf Serverseite
        // nicht pruefungsrelevant :-)
        new Thread( () -> { try {TimeUnit.SECONDS.sleep(1);
                                 System.exit(0);} 
                            catch(Exception e){}}
        ).start();

    }
    
}
