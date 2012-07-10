package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EstimatesPredictions{
    @XmlAttribute
    private String agencyTitle;

    @XmlAttribute
    private String routeTitle;

    @XmlAttribute
    private String routeTag;

    @XmlAttribute
    private String stopTitle;

    @XmlAttribute
    private String stopTag;

    @XmlElement(name="direction")
    List<EstimatesDirection> directionList;

    public String getAgencyTitle() {
        return agencyTitle;
    }

    public String getRouteTitle() {
        return routeTitle;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public String getStopTitle() {
        return stopTitle;
    }

    public String getStopTag() {
        return stopTag;
    }

    public List<EstimatesDirection> getDirectionList() {
        if (directionList == null)
            return new ArrayList<EstimatesDirection>();
        return directionList;
    }
}