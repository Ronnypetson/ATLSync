/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronnypetsonss
 */
public class OrderProducer extends Thread {
    public final static int SLEEP_DURATION = 3000;
    public final static int PRODUCTION_LIMIT = 25;
    private int produced;
    private int producerId;
    private OrderBuffer buffer;
    private OrderMonitor monitor;
    
    public OrderProducer(int id, OrderBuffer b, OrderMonitor ord){
        producerId = id;
        buffer = b;
        produced = 0;
        monitor = ord;
    }
    
    @Override
    public void run(){
        try {
            monitor.produce(buffer, producerId);
        } catch (InterruptedException ex) {
            Logger.getLogger(OrderProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void printProcessing(Timestamp t0, Timestamp t1, Order ord){
        System.out.println("Order producer id: " + this.producerId + "\n"
                         + "Order id: " + ord.getId() + "\n"
                         + "Starting time: " + t0 + "\n"
                         + "Ending time: " + t1 + "\n\n");
    }
}
