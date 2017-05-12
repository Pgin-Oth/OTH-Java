package verteiltesysteme.uebung06.frueherkennungs_service.future.server.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Future;

public interface FrueherkennungIF extends Remote {

    public Future<Bericht> analysieren(Roentgenbild bild) throws RemoteException;
    
}
