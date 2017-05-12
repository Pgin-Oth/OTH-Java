package verteiltesysteme.uebung06.frueherkennungs_service.future.server.lmu;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AnalyseIF extends Remote {
    public Bericht abholen() throws RemoteException;
    public boolean isAbholbereit() throws RemoteException;
    public void abbrechen() throws RemoteException;
}
