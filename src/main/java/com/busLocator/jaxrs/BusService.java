package com.busLocator.jaxrs;

import com.busLocator.service.NextBusService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/16/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/busInfo/")
public class BusService {
    NextBusService nextBusService;

    public void setNextBusService(NextBusService service) {
        nextBusService = service;
    }
    @GET
    @Path("/getRoutes/{location}/")
    public String getRoutes(@PathParam("location") String location){
       return nextBusService.getRoutes(location);
    }
    
    @GET
    @Path("/getStops/{location}/{busNumber}")
    public String getBusStops(@PathParam("location") String location, @PathParam("busNumber") String busNumber){
          return nextBusService.getStops(location, busNumber);
    }
   

    @GET
    @Path("/getEstimate/{location}/{fromBusStop}/{toBusStop}")
    public String getEstimateTime(@PathParam("location") String location, @PathParam("fromBusStop") String fromBusStop, @PathParam("toBusStop") String toBusStop){
        return nextBusService.getEstimates(location,fromBusStop,toBusStop);

    }
}
