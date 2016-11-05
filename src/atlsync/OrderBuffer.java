/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author ronnypetsonss
 */
public class OrderBuffer {
    private LinkedList<Order> orders;
    private final Semaphore available = new Semaphore(1, true);
    //
    public static int CAPACITY = 5000;
    //
    
    public OrderBuffer(){
        orders = new LinkedList<>();
    }
    
    public void fill(){
        orders = new LinkedList<>();
        for(int i = 0; i < CAPACITY; i++){
            orders.add(new Order( new BigInteger(""+i) ,""+i) );
        }
    }
    
    public Order getOrder(){
        return orders.poll();
    }
    
    public void addOrder(Order ord){
        if(orders.size() < CAPACITY){
            orders.add(ord);
        }
    }
}
