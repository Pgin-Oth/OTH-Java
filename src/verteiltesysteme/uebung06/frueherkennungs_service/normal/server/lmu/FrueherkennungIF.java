package verteiltesysteme.uebung06.frueherkennungs_service.normal.server.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    public Bericht analysieren(Roentgenbild bild) throws RemoteException;
    
}
