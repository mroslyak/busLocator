package com.busLocator.beans;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
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
public class Stops implements Serializable{
    @XmlElement(name = "route")
    StopsRoute route;

    public StopsRoute getRoute(){
        return route;
    }
}




