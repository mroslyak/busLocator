package com.busLocator.jaxrs;

import com.busLocator.service.MBTAService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/18/12
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/trainInfo/")
public class TrainService {
    MBTAService service;

    public void setService(MBTAService service){
        this.service = service;
    }

    @GET
    @Path("/stops/{trainLine}/")
    public String getStops(@PathParam("trainLine") String trainLine){
        return service.getTrainStops(trainLine);
    }

    @GET
    @Path("/estimate/{trainLine}/{stopId}")
    public String getBusStops(@PathParam("trainLine") String trainLine,@PathParam("stopId") String stopId ){
        return service.getTrainEstimates(trainLine,stopId);
    }

}
