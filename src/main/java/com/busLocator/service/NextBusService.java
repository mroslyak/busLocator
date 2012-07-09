package com.busLocator.service;

import com.busLocator.beans.Estimates;
import com.busLocator.beans.Routes;
import com.busLocator.beans.Stops;
import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/17/12
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NextBusService {
    Gson gson;
    JAXBContext mcontext;
    public NextBusService(){
        gson = new Gson();
        try{
            mcontext = JAXBContext.newInstance(Routes.class, Stops.class, Estimates.class);
        } catch (JAXBException e) {
            e.printStackTrace();

        }
    }

    private JAXBContext getContext(){
        return mcontext;
    }

    public String getRoutes(String location) {
        //http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=mbta
        InputStream in = ClassLoader.getSystemResourceAsStream("xml/routes_mbta.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Routes.class);

            Unmarshaller m = context.createUnmarshaller();
            Routes routes = (Routes) m.unmarshal(in);
            Gson gson = new Gson();
            String str = gson.toJson(routes);
            return str;
        } catch (JAXBException e) {
            e.printStackTrace();

        }

       return "error";
    }

    public String getStops(String location, String route) {
        //http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=mbta&r=60
        InputStream in = ClassLoader.getSystemResourceAsStream("xml/route_stops.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Stops.class);

            Unmarshaller m = context.createUnmarshaller();
            Stops stops = (Stops) m.unmarshal(in);
            Gson gson = new Gson();
            String str = gson.toJson(stops);
            return str;
        } catch (JAXBException e) {
            e.printStackTrace();

        }
        return "";
    }
    
    
    public String getEstimates(String location, String fromBusStop, String toBusStop){
        //http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=mbta&stopId=01562
        InputStream in = ClassLoader.getSystemResourceAsStream("xml/prediction.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Estimates.class) ;

            Unmarshaller m = context.createUnmarshaller();
            Estimates stops = (Estimates) m.unmarshal(in);
            Gson gson = new Gson();
            String str = gson.toJson(stops);
            return str;
        } catch (JAXBException e) {
            e.printStackTrace();

        }
        return "";
    }

}
