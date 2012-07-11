package com.busLocator.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Work around to stop server complaining about missing favicon.ico file.
 */
@Path("/")
public class RootService {

    @GET
    @Path("/favicon.ico")
    public String getRoot(){


        return null;
    }

}
