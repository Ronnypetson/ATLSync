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
    private final static int SLEEP_DURATION = 3000;
    private int consumerId;
    private OrderBuffer buffer;
    
    public OrderConsumer(int id, OrderBuffer b){
        consumerId = id;
        buffer = b;
    }
    
    @Override
    public void run(){
        Order ord;
        while((ord = buffer.getOrder()) != null){
            try {
                Timestamp t0 = new Timestamp((new Date()).getTime());
                Thread.sleep(SLEEP_DURATION);
                Timestamp t1 = new Timestamp((new Date()).getTime());
                printProcessing(t0,t1,ord);
            } catch (InterruptedException ex) {
                Logger.getLogger(OrderConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
