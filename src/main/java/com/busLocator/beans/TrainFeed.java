package com.busLocator.beans;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/18/12
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainFeed implements Serializable, Comparable<TrainFeed>{
    String tripId, destination,stopId,stopName,note;
    int seconds,trainNumber;

    long timestamp;



    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public int compareTo(TrainFeed trainFeed) {
        return this.getSeconds() - trainFeed.getSeconds();
    }
}
