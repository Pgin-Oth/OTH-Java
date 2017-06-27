package verteiltesysteme.uebung09.rest;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import de.othr.vs.data.entity.Veranstaltung;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("studentisches")
public class VeranstaltungResource {
    
    private static final Logger log = Logger.getLogger(VeranstaltungResource.class.getName());


    @GET
    @Path("veranstaltung/tipps/{suchstring}") // suchwoerter mit + getrennt
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Veranstaltung> getVeranstaltungsTipps(@PathParam("suchstring") String suchstring) {

        // Verbindung zur verteilten Datenstruktur in Hazelcast aufbauen

        
        
        
        String[] suchwoerter = suchstring.split("\\+"); // explode by +

        // über Hazelcast-MapReduce-Algorithmus nach Veranstaltungen suchen,
        // die im Titel oder in der Beschreibung mind. eines der Suchwoerter enthalten
        
        
        KeyValueSource<String, Veranstaltung> source = KeyValueSource.fromMap( /* Referenz auf Hazelcast-Datenstruktur */ );

        JobTracker jobTracker = ServerREST.hazelcast.getJobTracker(UUID.randomUUID().toString());
        Job<String, Veranstaltung> job = jobTracker.newJob(source);

        ICompletableFuture<List<Veranstaltung>> future = job 
                .mapper(new TippMapper())
                .reducer(new TippReducerFactory())
                .submit(new TippCollator()); 
                // startet den MapReduce-Algorithmus

    
        // Liste aller Veranstaltungen, die zu den Suchwoertern passen, zurueckgeben
        return future.get();
    }

    
    
    @POST
    @Path("veranstaltung")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String createVeranstaltung(Veranstaltung v) {

        log.log(Level.INFO, "Neue Veranstaltung {0} mit id {1}", new Object[]{v.getTitel(), v.getId()});

        // Verbindung zur verteilten Datenstruktur in Hazelcast aufbauen
        
        
        
        
        String id = UUID.randomUUID().toString();
        v.setId(id);
        
        // Uebergebene Veranstaltung in verteilte Datenstruktur einfuegen



        
        // hier wird z. B: nur die ID der Veranstaltung zurueckgegeben
        return v.getId();
    }
    
    
    
    
    @GET
    @Path("veranstaltung/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Veranstaltung getVeranstaltung(@PathParam("id") String id) {

        log.log(Level.INFO, "Veranstaltung angefordert mit id {0}", id);

        // Verbindung zur verteilten Datenstruktur in Hazelcast aufbauen



        // Veranstaltung aus verteilter Datenstruktur auslesen


        
        
        // sofern die Veranstaltung nicht gefunden wurde, koennen Sie so eine "404 Not Found" ausloesen:
        // throw new WebApplicationException(Response.Status.NOT_FOUND);
        
        
        // gefundene Veranstaltung zurueckgeben
        return null;
    }
    
    
}
