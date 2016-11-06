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
    private final static int SLEEP_DURATION = 3000;
    private final static int PRODUCTION_LIMIT = 250;
    private int produced;
    private int producerId;
    private OrderBuffer buffer;
    
    public OrderProducer(int id, OrderBuffer b){
        producerId = id;
        buffer = b;
        produced = 0;
    }
    
    @Override
    public void run(){
        //
        while(produced < PRODUCTION_LIMIT){
            if(!buffer.isFull()){
                Timestamp t0 = new Timestamp((new Date()).getTime());
                String prod = "" + (super.getId()*1000 + (produced++));
                Order ord = new Order(new BigInteger(prod), prod);
                try {
                    buffer.addOrder(ord);
                    Thread.sleep(SLEEP_DURATION);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OrderProducer.class.getName()).log(Level.SEVERE, null, ex);
                }
                Timestamp t1 = new Timestamp((new Date()).getTime());
                printProcessing(t0,t1,ord);
            }
        }
    }
    
    private void printProcessing(Timestamp t0, Timestamp t1, Order ord){
        System.out.println("Order producer id: " + this.producerId + "\n"
                         + "Order id: " + ord.getId() + "\n"
                         + "Starting time: " + t0 + "\n"
                         + "Ending time: " + t1 + "\n\n");
    }
}
