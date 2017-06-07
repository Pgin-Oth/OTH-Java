/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteiltesysteme.uebung07.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 陈香甦
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Adresse {
    @XmlAttribute
    private String strasse;
    private String ort;
}
