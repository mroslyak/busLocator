package com.busLocator.beans;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/18/12
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainStop extends BusStop implements Comparable<TrainStop>{
    public TrainStop(String tag, String name){
        super(tag,name);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TrainStop){
            TrainStop t = (TrainStop) obj;
            return t.getTag().equals(this.tag);
        }else
            return false;
    }


    public int compareTo(TrainStop trainStop) {
        return this.getTag().compareTo(trainStop.getTag());

    }
}
