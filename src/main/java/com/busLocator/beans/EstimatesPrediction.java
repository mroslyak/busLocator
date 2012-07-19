package com.busLocator.beans;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class EstimatesPrediction{
    @XmlAttribute
    private String seconds;

    @XmlAttribute
    private String minutes;

    @XmlAttribute
    private String isDeparture;
    @XmlAttribute
    private String affectedByLayover;
    @XmlAttribute
    private String  dirTag;
    @XmlAttribute
    private String vehicle;

    public String getSeconds() {
        return seconds;
    }

    
    public String getMinutes(){
        return minutes;
    }
    public String getMinutesStr() {

        if (affectedByLayover != null && affectedByLayover.equals("true"))
            return minutes +"*";
        return minutes;
    }

    public String getDeparture() {
        return isDeparture;
    }

    public String getAffectedByLayover() {
        return affectedByLayover;
    }

    public String getDirTag() {
        return dirTag;
    }

    public String getVehicle() {
        return vehicle;
    }
}

