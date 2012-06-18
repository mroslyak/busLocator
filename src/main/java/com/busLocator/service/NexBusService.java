package com.busLocator.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/17/12
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NexBusService {
    public List<String> getRoutes(String location){
        //http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=mbta
        List<String> routes = new ArrayList<String>();
        routes.add("1");
        routes.add("2");
        return routes;
    }

    public String getStops(String location, String route){
        //http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=mbta&r=60

        return "";
    }
    
}
