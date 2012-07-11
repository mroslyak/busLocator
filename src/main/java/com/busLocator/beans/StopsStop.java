package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/10/12
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StopsStop implements Serializable{
    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlAttribute
    public String stopId;
    
    public String getTag(){ return tag;}
    public String getTitle(){return title;}
}
