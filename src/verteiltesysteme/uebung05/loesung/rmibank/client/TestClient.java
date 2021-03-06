package verteiltesysteme.uebung05.loesung.rmibank.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import verteiltesysteme.uebung05.loesung.rmibank.server.Cheque;
import verteiltesysteme.uebung05.loesung.rmibank.server.IBanking;

public class TestClient {

	public static void main(String[] args) {
		try {
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			IBanking konto = (IBanking) r.lookup("Konto");
			System.out.println("Testclient: 100 Euro einzahlen");
			konto.deposit(10000);
			System.out.println("Testclient: nochmal 100 Euro einzahlen");
			konto.deposit(10000);
			System.out.println("Testclient: 50 Euro abheben");
			konto.withdraw(5000);
			System.out.println("Testclient: Scheck mit 200 Euro einreichen");
			konto.deposit(new Cheque(2000, "1234", "HVB"));
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
