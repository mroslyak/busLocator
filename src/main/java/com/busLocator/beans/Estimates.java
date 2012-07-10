package com.busLocator.beans;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Estimates {
    @XmlElement(name = "predictions")
    List<EstimatesPredictions> predictionsList;

    public List<EstimatesPredictions> getPredictionsList() {
        return predictionsList;
    }
}








