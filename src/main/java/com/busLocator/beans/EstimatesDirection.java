package com.busLocator.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/9/12
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EstimatesDirection{
    @XmlElement(name = "prediction")
    List<EstimatesPrediction> predictionList;

    public List<EstimatesPrediction> getPredictionList() {
        return predictionList;
    }

}
