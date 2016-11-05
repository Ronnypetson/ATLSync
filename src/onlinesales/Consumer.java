/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinesales;

/**
 *
 * @author Tertulino
 */

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    private int consumerId;
    private OrderQueue orders;
    private long min = 30, max = 300;
    
    public Consumer(int consumerId, OrderQueue orders){
        super();
        this.consumerId = consumerId;
        this.orders = orders;
    }
    
    public int getConsumerId(){
        return this.consumerId;
    }
          
    @Override
    public void run(){
        while(!orders.getOrders().isEmpty()){
            Order o = orders.removeOrder();
            
            // gets the current timestamp
            // selects a random integer between 30 and 300
            // sleeps for the selected number of miliseconds
            // gets the new timestamp
            // prints the order's number, the old and thw new timestamps
            
            Date d1 = new Date();
            Timestamp t1 = new Timestamp(d1.getTime());
            
            long sleep = min + (long)(Math.random() * (max - min + 1) );
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Date d2 = new Date();
            Timestamp t2 = new Timestamp(d2.getTime());
            
            System.out.println("Consumidor " + this.consumerId + " processou "
                + "pedido " + o.getOrderNumber() + "\n"
                + "Início do processamento: " + t1 + "\n"
                + "Fim do processamento: " + t2);
        }
        
        // When the orders' queue gets empty, the consumers must block
    }
}
