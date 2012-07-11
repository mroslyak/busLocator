package com.busLocator.beans;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/10/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class BusStop implements Serializable{
    public BusStop(String tag, String name){
        this.name = name;
        this.tag = tag;
    }
    String tag;
    String name;
    
    public String getTag(){return tag;}
    public String getName(){return name;}
}
