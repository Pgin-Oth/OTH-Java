package verteiltesysteme.uebung04.server;

/**
 * Created by chx34972 on 25.04.2017.
 */
public class ClientEndPoint extends Thread {
    private Server server;
    public ClientEndPoint(Server server){
        this.server = server;
    }
    @Override
    public void run(){

    }
}
