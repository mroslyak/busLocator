package com.busLocator.service;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 7/18/12
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class MBTAServiceTest {
    
    
    @Test
    public void getLineStops(){
        MBTAService service = new MBTAService();
        String str = service.getTrainStops("red");
        System.out.println(str);
    }
    
    
    @Test 
    public void getEstimate(){
        MBTAService service = new MBTAService();
        String str = service.getTrainEstimates("red","70067");
        System.out.println(str);
    }
}
