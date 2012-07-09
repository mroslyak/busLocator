package com.busLocator.beans;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Estimates {
    @XmlElement(name = "predictions")
    List<Predictions> predictionsList;

    public List<Predictions> getPredictionsList() {
        return predictionsList;
    }
}

    @XmlAccessorType(XmlAccessType.FIELD)
    class Predictions{
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
        List<Direction> directionList;

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

        public List<Direction> getDirectionList() {
            return directionList;
        }
    }

        @XmlAccessorType(XmlAccessType.FIELD)
         class Direction{
            @XmlElement(name = "prediction")
            List<Prediction> predictionList;

            public List<Prediction> getPredictionList() {
                return predictionList;
            }

        }
             class Prediction{
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

                public String getMinutes() {
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





