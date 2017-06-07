/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung07.rest;

import verteiltesysteme.uebung07.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;

/**
 *
 * @author 陈香甦
 */
// http://localhost:9001/webresources/crm/student/1
// für Pfad "crm"
@Path("crm")
public class StudentResource {
    // für zusätzlichen Pfad "student"
    // für HTTP-Methode POST
    private static final List<Student> database = new ArrayList();
    private static int lastMartikelNr = 1;

    /*

{
    "vornamen": "vornamen1",
    "nachnamen": "nachnamen1",
    "ects": 10,
    "adresse": {
        "strasse": "strasse1",
        "ort": "ort1"
    }
}

<student>
    <vornamen>vornamen1</vornamen>
    <nachnamen>nachnamen1</nachnamen>
	<ects>10</ects>
    <adresse strasse="strasse1">
        <ort>ort1</ort>
    </adresse>
</student>
    * */

    @Path("student")
    @POST
    // If you only want to accept xml in post then do this
    // @Consumes(MediaType.APPLICATION_XML)
    // If you want to accept xml and json in post then do this
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Student createStudent(Student s) {
        // in Datenbank eintragen
        s.setMartikelnr(lastMartikelNr);
        database.add(s);
        lastMartikelNr++;
        return s;
    }
    // für zusätzliche Pfad "student/{studentId}"
    // für HTTP-Method GET
    @Path("student/{studentMartikelnr}")
    @GET
    //By default return contents is xml. If you want to have a json as return type, you can do this:
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("studentMartikelnr") int martikelnr) {
        // in Datenbank suchen
        Student result = null;
        for (Student s : database){
            if (s.getMartikelnr() == martikelnr)
                result = s;
        }
        return result;
    }
    @Path("student/{studentMartikelnr}")
    @DELETE
    public void deleteStudentById(@PathParam("studentMartikelnr") int martikelnr) {
        Student toBeDeleted = null;
        for (Student s : database){
            if (s.getMartikelnr() == martikelnr)
                toBeDeleted = s;
        }
        database.remove(toBeDeleted);
    }

    @Path("student/{studentMartikelnrFrom}-{studentMartikelnrTo}")
    @GET
    public Student[] getStudentsByIdRange(@PathParam("studentMartikelnrFrom") int from, @PathParam("studentMartikelnrTo") int to) {
        int begin, end, count = 0, index = 0;
        
        begin = from > to ? to : from;
        end = from > to ? from : to;
        for (Student s : database){
            if (s.getMartikelnr() >= begin && s.getMartikelnr() <= end)
                count++;
        }
        Student[] result;
        result = new Student[count];

        for (Student s : database){
            if (s.getMartikelnr() >= begin && s.getMartikelnr() <= end)
                result[index++] = s;
        }
        return result;
    }

}
