/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung07.crm;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import javax.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.server.ResourceConfig;
import verteiltesysteme.uebung07.rest.StudentResource;

public class ServerREST {
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException {
        
        ResourceConfig config = new ResourceConfig();
        config.register(StudentResource.class);

        HttpServer server = HttpServer.create(new InetSocketAddress(9001), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        server.createContext("/webresources", handler);
        server.start();

        JOptionPane.showMessageDialog(null, "Server stoppen...");
        server.stop(0);
    }
}
