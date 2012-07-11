package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/10/12
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StopsBusDirection implements Serializable{
    @XmlAttribute (name="tag")
    String tag;
    @XmlAttribute (name = "title")
    String title;
    @XmlAttribute (name = "name")
    String direction;

    @XmlElement(name = "useForUI")
    boolean useForUI;
    @XmlElement (name = "stop")
    List<StopsDirectionStop> stopOrderList;

    public boolean isUseForUI(){
        return useForUI;
    }
    public List<StopsDirectionStop> getStopOrderList(){
        return stopOrderList;
    }

    public String getTitle(){ return title;}
    
}
