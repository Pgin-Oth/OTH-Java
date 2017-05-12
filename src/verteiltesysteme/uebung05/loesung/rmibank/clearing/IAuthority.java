package verteiltesysteme.uebung05.loesung.rmibank.clearing;

import java.rmi.Remote;
import java.rmi.RemoteException;

import verteiltesysteme.uebung05.loesung.rmibank.server.Cheque;

public interface IAuthority extends Remote {
	public void requestApproval(Cheque scheck) throws RemoteException;
	public boolean obtainApproval() throws RemoteException;
}
