package com.busLocator.service;

import org.junit.Test;

public class NextBusServiceTest {

    @Test
    public void getRoutes() {

        NextBusService service = new NextBusService();
        String str = service.getRoutes("mbta");
        System.out.println(str);

 //       service.getRoutes("mbta");
    }
    
    @Test
    public void getStops(){
        NextBusService service = new NextBusService();
        String str = service.getStops("mbta","60");
        System.out.println(str);
    }
    
    
    @Test
    public void getPredictions(){
        NextBusService service = new NextBusService();
        String str = service.getEstimates("mbta","899","8993");
        System.out.println(str);
    }
}

