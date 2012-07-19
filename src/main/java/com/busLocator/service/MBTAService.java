package com.busLocator.service;

import com.busLocator.beans.TrainFeed;
import com.busLocator.beans.TrainStop;
import com.google.gson.Gson;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/18/12
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class MBTAService {
    Log logger = LogFactory.getLog(MBTAService.class);
    CacheManager manager;
    private final String LONG_TERM_CACHE = "LongCacheTrain";
    private final String SHORT_TERM_CACHE = "ShortCacheTrain";
    Gson gson;

    public MBTAService() {
        gson = new Gson();
        manager = CacheManager.create();
        if (!manager.cacheExists(LONG_TERM_CACHE)) {
            Cache memoryOnlyCache = new Cache(LONG_TERM_CACHE, 20, false, false, 3600, 3600);
            manager.addCache(memoryOnlyCache);
        }
        if (!manager.cacheExists(SHORT_TERM_CACHE)) {
            Cache memoryOnlyCache = new Cache(SHORT_TERM_CACHE, 20, false, false, 30, 30);
            manager.addCache(memoryOnlyCache);
        }
    }

    public String getTrainStops(String train) {
        Cache memoryCache = manager.getCache(LONG_TERM_CACHE);
        Element element = memoryCache.get(train + "_stops");
        if (element != null) {
            String str = (String) element.getValue();
            logger.info("getting cached version of stops for " + train);
            return str;
        }

        List<TrainFeed> trainFeedList = getTrainFeed(train);
        Map<String, List<TrainStop>> trainStopMap = new HashMap<String, List<TrainStop>>();

        for (TrainFeed trainFeed : trainFeedList) {
            String trainDirection = trainFeed.getDestination();
            List<TrainStop> stops = trainStopMap.get(trainDirection);
            if (stops == null) {
                stops = new ArrayList<TrainStop>();
                trainStopMap.put(trainDirection, stops);
            }
            TrainStop stop = new TrainStop(trainFeed.getStopId(), trainFeed.getStopName());
            if (!stops.contains(stop)) {
                stops.add(stop);
            }
        }

        //sort stops
        for (String direction : trainStopMap.keySet()){
            List<TrainStop> list = trainStopMap.get(direction);
            Collections.sort(list);
        }
        String str = gson.toJson(trainStopMap);
        memoryCache.put(new Element(train + "_stops", str));

        return str;


    }

    
    public String getTrainEstimates(String trainType, String stopId){
        List<TrainFeed> trainFeedList = getTrainFeed(trainType);
        String rtnStr ="";
        for (TrainFeed feed:trainFeedList){
            if (stopId.equals(feed.getStopId())){
                int minutes = feed.getSeconds() /60;
                String note = "";
                if (feed.getNote() != null && feed.getNote().length()>0){
                    note = "(" + feed.getNote() +")";
                }
                if (minutes <1 )
                    rtnStr +="< 1"+ note+",";
                else
                    rtnStr += minutes + note+",";

            }
        }

        if (!rtnStr.isEmpty()){
            rtnStr = rtnStr.substring(0,rtnStr.length()-1);
        }

        return rtnStr;
    }


    public List<TrainFeed> getTrainFeed(String trainType) {
        List<TrainFeed> list;
        Cache memoryCache = manager.getCache(SHORT_TERM_CACHE);
        Element element = memoryCache.get(trainType + "_feed");
        if (element != null) {
            list = (List<TrainFeed>) element.getValue();
            logger.info("getting cached version of feed for " + trainType);
            return list;
        }
        InputStream in = null;
        list = new ArrayList<TrainFeed>();
        String line = null;
        try {

            URL url = new URL("http://Developer.mbta.com/lib/rthr/"+trainType+".csv");

            //in = ClassLoader.getSystemResourceAsStream("xml/red.csv");
            in = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                String[]fields =line.split(",",-1);
                if (fields[0].equals("CurrentTime")){
                    continue;
                }
                if (fields.length != 13){
                    logger.error("The format is corrupt.  Get line="+line +" "+ fields.length);
                    return list;
                }

                TrainFeed feed = new TrainFeed();
                feed.setTripId(fields[2]);
                feed.setDestination(fields[3]);
                feed.setStopId(fields[4]);
                feed.setStopName(fields[5]);
                feed.setSeconds(Integer.parseInt(fields[6]));
                if (fields[7] != null && !fields[7].isEmpty())
                    feed.setTimestamp(Long.parseLong(fields[7]));
                if (fields[8] != null && !fields[8].isEmpty())
                    feed.setTrainNumber(Integer.parseInt(fields[8]));
                if (fields[12] != null)
                    feed.setNote(fields[12]);
                list.add(feed);
            }
        } catch (Exception e) {
            logger.error("Failed, line ="+ line,e);

        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }

        Collections.sort(list);

        memoryCache.put(new Element(trainType + "_feed",list));

        return list;
    }
 }
