/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung08.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 陈香甦
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private int martikelnr;
    private String vornamen;
    private String nachnamen;
    private int ects;
    private Adresse adresse;

    public int getMartikelnr() {
        return martikelnr;
    }

    public void setMartikelnr(int martikelnr) {
        this.martikelnr = martikelnr;
    }

    public String getVornamen() {
        return vornamen;
    }

    public void setVornamen(String vornamen) {
        this.vornamen = vornamen;
    }

    public String getNachnamen() {
        return nachnamen;
    }

    public void setNachnamen(String nachnamen) {
        this.nachnamen = nachnamen;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Student{" + "martikelnr=" + martikelnr + ", vornamen=" + vornamen + ", nachnamen=" + nachnamen + ", ects=" + ects + ", adresse=" + adresse + '}';
    }

    public void correctNullValue() {
        if (this.vornamen == null)
            this.vornamen = "Muster Vorname";
        if (this.nachnamen == null)
            this.nachnamen = "Muster Nachname";
        if (this.adresse == null )
            this.adresse = new Adresse("Muster Strasse","Muster Ort");
        if (this.adresse.getStrasse() == null)
            this.adresse.setStrasse("Muster Strasse");
        if (this.adresse.getOrt() == null)
            this.adresse.setOrt("Muster Ort");
    }
}
