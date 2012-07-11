package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/10/12
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StopsRoute implements Serializable {
    @XmlElement(name = "stop")
    List<StopsStop> stopList;

    @XmlElement(name = "direction")
    List<StopsBusDirection> busDirectionList;
    
    public List<StopsBusDirection> getBusDirectionList(){
        return busDirectionList;
    }

}

