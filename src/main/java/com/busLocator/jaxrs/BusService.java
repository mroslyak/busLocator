package com.busLocator.jaxrs;

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

    @GET
    @Path("/getRoutes/{location}/")
    public String getRoutes(@PathParam("location") String location){

        return "routes" + location;
    }
    
    @GET
    @Path("/getStops/{busNumber}")
    public String getBusStops(@PathParam("busNumber") String busNumber){
          return "stops";
    }
   

}
