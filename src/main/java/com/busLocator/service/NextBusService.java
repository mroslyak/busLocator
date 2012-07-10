package com.busLocator.service;

import com.busLocator.beans.*;
import com.google.gson.Gson;
import net.sf.ehcache.CacheManager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.management.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/17/12
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NextBusService {
    private final String NEXT_BUS_URL = "http://webservices.nextbus.com/service/publicXMLFeed";
    private final String LONG_TERM_CACHE = "LongBusCache";
    private final String SHORT_TERM_CACHE = "ShortBusCache";

    Gson gson;
    JAXBContext mcontext;
    CacheManager manager;

    public NextBusService() {
        gson = new Gson();
        try {
            mcontext = JAXBContext.newInstance(Routes.class, Stops.class, Estimates.class);
        } catch (JAXBException e) {
            e.printStackTrace();

        }
        manager = CacheManager.create();
        Cache memoryOnlyCache = new Cache(LONG_TERM_CACHE, 200, false, false, 3600, 3600);
        manager.addCache(memoryOnlyCache);
        Cache shortCache = new Cache(SHORT_TERM_CACHE, 200, false, false, 30, 30);
        manager.addCache(shortCache);

    }

    private JAXBContext getContext() {
        return mcontext;
    }

    public String getRoutes(String location) {
        Cache memoryCache = manager.getCache(LONG_TERM_CACHE);
        Element element = memoryCache.get(location);
        if (element != null) {
            String str = (String) element.getValue();
            return str;
        }
        InputStream in = null;
        try {

            URL url = new URL(NEXT_BUS_URL + "?command=routeList&a=" + location);

            //in = ClassLoader.getSystemResourceAsStream("xml/routes_mbta.xml");
            in = url.openStream();
            JAXBContext context = JAXBContext.newInstance(Routes.class);

            Unmarshaller m = context.createUnmarshaller();
            Routes routes = (Routes) m.unmarshal(in);
            Gson gson = new Gson();
            String str = gson.toJson(routes);
            memoryCache.put(new Element(location, str));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }


        return "error";
    }

    public String getStops(String location, String route) {
        Cache memoryCache = manager.getCache(LONG_TERM_CACHE);
        Element element = memoryCache.get(location+"_"+route);
        if (element != null) {
            String str = (String) element.getValue();
            return str;
        }
        //http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=mbta&r=60
        InputStream in = null;//ClassLoader.getSystemResourceAsStream("xml/route_stops.xml");
        try {
            URL url = new URL(NEXT_BUS_URL +"?command=routeConfig&a="+location +"&r="+route);
            in = url.openStream();
            JAXBContext context = JAXBContext.newInstance(Stops.class);

            Unmarshaller m = context.createUnmarshaller();
            Stops stops = (Stops) m.unmarshal(in);
            Gson gson = new Gson();
            String str = gson.toJson(stops);
            memoryCache.put(new Element(location+"_"+route,str));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
        return "";
    }


    public String getEstimates(String location, String fromBusStop, String toBusStop) {


        Cache memoryCache = manager.getCache(SHORT_TERM_CACHE);
        Element element = memoryCache.get(location+"_"+fromBusStop +"_"+toBusStop);
        if (element != null) {
            String str = (String) element.getValue();
            return str;
        }

        //http://webservices.nextbus.com/service/publicXMLFeed?command=predictions&a=mbta&stopId=01562
        InputStream in = null;//ClassLoader.getSystemResourceAsStream("xml/prediction.xml");
        try {
            URL url = new URL(NEXT_BUS_URL +"?command=predictions&a="+location +"&stopId="+fromBusStop);
            in = url.openStream();

            JAXBContext context = JAXBContext.newInstance(Estimates.class);

            Unmarshaller m = context.createUnmarshaller();
            Estimates stops = (Estimates) m.unmarshal(in);
            
            Map<String,String> matchingRoutesMap = new HashMap<String, String>();
            for (EstimatesPredictions estimatesPrediction:stops.getPredictionsList()){
                if (routeMatches(location,estimatesPrediction.getRouteTag(),toBusStop)){
                    List<EstimatesDirection> directionList = estimatesPrediction.getDirectionList();
                    String timeToStop="";

                    for (EstimatesDirection direction: directionList){
                        for (EstimatesPrediction prediction:direction.getPredictionList()){
                            if (!timeToStop.isEmpty()) {
                                timeToStop +=" & ";
                            }
                            timeToStop += prediction.getMinutes();
                        }
                    }
                    if (!timeToStop.isEmpty())
                        matchingRoutesMap.put(estimatesPrediction.getRouteTitle(),timeToStop);
                }

            }


            Gson gson = new Gson();
            String str = gson.toJson(matchingRoutesMap);
            memoryCache.put(new Element(location+"_"+fromBusStop +"_"+toBusStop,str));
            return str;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
        return "";
    }

        
        
    private boolean routeMatches(String location,String routeTag, String busStop){
        String stops = getStops(location,routeTag);

        return true;
        
    }
    
        
}
