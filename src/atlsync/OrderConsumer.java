/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronnypetsonss
 */
public class OrderConsumer extends Thread {
    public final static int SLEEP_DURATION = 3000;
    public final static int CONSUME_LIMIT = 250;
    private int consumed;
    private int consumerId;
    private OrderBuffer buffer;
    private OrderMonitor monitor;
    
    public OrderConsumer(int id, OrderBuffer b, OrderMonitor ord){
        consumerId = id;
        buffer = b;
        consumed = 0;
        monitor = ord;
    }
    
    @Override
    public void run(){
        monitor.consume(buffer, consumerId);
    }
    
    private void printProcessing(Timestamp t0, Timestamp t1, Order ord){
        System.out.println("Order consumer id: " + this.consumerId + "\n"
                         + "Order id: " + ord.getId() + "\n"
                         + "Starting time: " + t0 + "\n"
                         + "Ending time: " + t1 + "\n\n");
    }
    
    public int getConsuemrId(){
        return consumerId;
    }
    
    public OrderBuffer getBuffer(){
        return buffer;
    }
    
    public void setBuffer(OrderBuffer b){
        buffer = b;
    }
}
