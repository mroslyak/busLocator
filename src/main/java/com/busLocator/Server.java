package com.busLocator;

import com.busLocator.jaxrs.BusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Endpoint;
import javax.xml.ws.http.HTTPBinding;

/**
 * Created by IntelliJ IDEA.
 * User: mroslyakov
 * Date: 6/16/12
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private static Log logger = LogFactory.getLog(Server.class);

    protected Server()  {
        BusService busService = new BusService();

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(BusService.class);
        sf.setResourceProvider(BusService.class,
                new SingletonResourceProvider(busService));
        sf.setAddress("http://0.0.0.0:9000/");

        sf.create();
    }

    public static void main(String args[]) {
       logger.info("Starting Server.. ");

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]
                {"application.xml"});

        JAXRSServerFactoryBean sfb = (JAXRSServerFactoryBean)ctx.getBean("servicers");
        System.out.println(sfb.getAddress());
    //    sfb.create();
//        new Server();
        logger.info("Server ready...");
        try{
        while (true){
            Thread.sleep(5 * 60 * 1000);
        }
        }catch(InterruptedException e){

        }
//        System.out.println("Server exiting");
//        System.exit(0);
    }

}
