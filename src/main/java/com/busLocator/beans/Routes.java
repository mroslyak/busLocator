package com.busLocator.beans;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/18/12
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Routes {
    @XmlElement(name="route")
    public List<Route> routes;
}

@XmlAccessorType(XmlAccessType.FIELD)
class Route{
    @XmlAttribute
    String tag;
    
    @XmlAttribute
    String title;
    
    @XmlAttribute
    String stopId;
}


