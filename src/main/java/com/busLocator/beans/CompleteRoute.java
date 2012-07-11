package com.busLocator.beans;

import java.io.Serializable;
import java.util.List;

/**
Returned to customer in json format.
 */
public class CompleteRoute implements Serializable{
    String direction;
    List<BusStop> stopList;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<BusStop> getStopList() {
        return stopList;
    }

    public void setStopList(List<BusStop> stopList) {
        this.stopList = stopList;
    }
}
