package verteiltesysteme.uebung04.server;

/**
 * Created by chx34972 on 25.04.2017.
 */
public class Aceptor extends Thread {
    private Server server;
    public Aceptor(Server server){
        this.server = server;
    }
    @Override
    public void run(){

    }
}
