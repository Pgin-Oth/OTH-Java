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
public class Adresse {
    @XmlAttribute
    private String strasse;
    private String ort;

    public Adresse() {
    }

    public Adresse(String strasse, String ort) {
        this.strasse = strasse;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getOrt() {
        return ort;
    }
}
