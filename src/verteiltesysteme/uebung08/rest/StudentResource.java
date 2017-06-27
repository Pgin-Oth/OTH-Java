/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung08.rest;

import verteiltesysteme.uebung08.entity.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
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
    private String driver = "com.mysql.jdbc.Driver";
    private String dbName = "vs-08";
    private String passwrod = "vs-08-pw";
    private String userName = "vs-08";
    private String url = "jdbc:mysql://im-vm-011:3306/" + dbName;

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student createStudent(Student s) {
        // in Datenbank eintragen
        s.setMartikelnr(lastMartikelNr);
        s.correctNullValue();
        Connection conn = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, userName, passwrod);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            try {
                String sql = "INSERT INTO Student VALUES ("
                        + s.getMartikelnr()
                        + ",'" + s.getVornamen() + "'"
                        + ",'" + s.getNachnamen() + "'"
                        + "," + s.getEcts()
                        + ",'" + s.getAdresse().getStrasse() + "'"
                        + ",'" + s.getAdresse().getOrt() + "'" + ")";
                System.out.print(sql);
                stmt.executeUpdate(sql);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
        Connection conn = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, userName, passwrod);
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM Student WHERE matrikelNr = " + martikelnr;
                System.out.print(sql);
                rs = stmt.executeQuery(sql);
                if (rs.first()) {
                    result = new Student();
                    result.correctNullValue();
                    result.setMartikelnr(rs.getInt("matrikelNr"));
                    result.setVornamen(rs.getString("vorname"));
                    result.setNachnamen(rs.getString("nachname"));
                    result.setEcts(rs.getInt("ects"));
                    result.getAdresse().setStrasse(rs.getString("strasse"));
                    result.getAdresse().setOrt(rs.getString("ort"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Student s : database) {
            if (s.getMartikelnr() == martikelnr)
                result = s;
        }
        return result;
    }

    @Path("student/{studentMartikelnr}")
    @DELETE
    public void deleteStudentById(@PathParam("studentMartikelnr") int martikelnr) {
        Student toBeDeleted = null;
        for (Student s : database) {
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
        for (Student s : database) {
            if (s.getMartikelnr() >= begin && s.getMartikelnr() <= end)
                count++;
        }
        Student[] result;
        result = new Student[count];

        for (Student s : database) {
            if (s.getMartikelnr() >= begin && s.getMartikelnr() <= end)
                result[index++] = s;
        }
        return result;
    }

}
