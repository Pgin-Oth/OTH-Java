package verteiltesysteme.uebung09.xml;


import de.othr.vs.data.entity.Adresse;
import de.othr.vs.data.entity.Student;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class MarshallTest {
    
    /*  Unabhaengig von den Uebungen zu REST.
     *  Hier soll nur gezeigt werden, wie man aus Java JSON und XML erzeugen kann.
     *  Innerhalb Ihrer REST-Implementierungen geschieht dies automatisch.
     */

    public static void main(String[] args) throws JAXBException {
        Student stud = new Student(1234, "Max", "Muster", 87, new Adresse("Unistr. 1", "Ortingen"));
       
        // XML in Datei schreiben
        JAXB.marshal(stud, new File("stud1234.xml"));

        // XML in String schreiben (geht nur über StringWriter) ...
        StringWriter sw = new StringWriter();
        JAXB.marshal(stud, sw);
        System.out.println(sw.toString());
        
        // ... und den String (XML) wieder in neues Objekt umwandeln lassen
        Student neu = JAXB.unmarshal(new StringReader(sw.toString()), Student.class);
        // toString() des neuen Objekts aufrufen
        System.out.println(neu);
        
        // Normale Ausgabe auf System.out
        JAXB.marshal(neu, System.out);
        
    }
}
