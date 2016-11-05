/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinesales;

import java.util.Vector;

/**
 *
 * @author Tertulino
 */
public class OrderQueue {
    private Vector<Order> orders;
    private int maxOrders = 100;
    //This number is temporary
    
    public OrderQueue(){
        orders = new Vector<>(maxOrders);
    }
    
    /*
        In the following method, we initialize the queue with the 5000 orders,
        for the first part of the work.
    */
    public void initializeQueue(){
        for (long i = 0; i < maxOrders; i++) {
            String description = "Order " + (i+1);
            orders.add(new Order(i, description));
        }
    }
    
    public Vector<Order> getOrders(){
        return orders;
    }
    
    public Order removeOrder(){
        Order o = orders.firstElement();
        orders.remove(0);
        
        return o;
    }
}
