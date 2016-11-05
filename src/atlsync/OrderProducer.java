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
public class OrderProducer extends Thread {
    private final static int SLEEP_DURATION = 3000;
    private int producerId;
    private OrderBuffer buffer;
    
    public OrderProducer(int id, OrderBuffer b){
        producerId = id;
        buffer = b;
    }
    
    @Override
    public void run(){
        //
    }
    
    private void printProcessing(Timestamp t0, Timestamp t1, Order ord){
        System.out.println("Order producer id: " + this.producerId + "\n"
                         + "Order id: " + ord.getId() + "\n"
                         + "Starting time: " + t0 + "\n"
                         + "Ending time: " + t1 + "\n\n");
    }
}
