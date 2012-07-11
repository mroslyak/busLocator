package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/10/12
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StopsDirectionStop implements Serializable{
    @XmlAttribute(name = "tag")
    String tag;
    public String toString(){
        return tag;
    }
}
