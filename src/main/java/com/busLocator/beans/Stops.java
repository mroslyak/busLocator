package com.busLocator.beans;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/18/12
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stops {
    @XmlElement(name = "route")
    StopRoute route;
}

class StopRoute {
    @XmlElement(name = "stop")
    List<Stop> stopList;

    @XmlElement (name= "direction")
    List<BusDirection> busDirectionList;
}

class BusDirection{
    @XmlAttribute (name="tag")
    String tag;
    @XmlAttribute (name = "title")
    String title;
    @XmlAttribute (name = "name")
    String direction;

    @XmlElement (name = "useForUI")
    boolean useForUI;
    @XmlElement (name = "stop")
    List<DirectionStop> stopOrderList;
}

class DirectionStop {
    @XmlAttribute (name = "tag")
    String tag;
    public String toString(){
        return tag;
    }
}
class Stop {
    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlAttribute
    public String stopId;


}



