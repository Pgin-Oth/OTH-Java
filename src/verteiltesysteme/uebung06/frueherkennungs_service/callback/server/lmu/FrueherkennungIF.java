package verteiltesysteme.uebung06.frueherkennungs_service.callback.server.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    public void analysieren(Roentgenbild bild, BerichtCallbackIF callback) throws RemoteException;
    
}
