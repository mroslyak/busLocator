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
}

class Stop {
    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlAttribute
    public String stopId;


}



