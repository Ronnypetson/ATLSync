/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 *
 * @author ronnypetsonss
 */
public class OrderBuffer {
    private LinkedList<Order> orders;
    //
    public static int CAPACITY = 250;
    //
    
    public OrderBuffer(){
        orders = new LinkedList<>();
    }
    
    public synchronized void fill() throws InterruptedException{
        orders = new LinkedList<>();
        for(int i = 0; i < CAPACITY; i++){
            orders.add(new Order( new BigInteger(""+i) ,""+i) );
        }
    }
    
    public synchronized Order getOrder() throws InterruptedException{
        return orders.pollFirst();
    }
    
    public synchronized void addOrder(Order ord) throws InterruptedException{
        if(orders.size() < CAPACITY){
            orders.add(ord);
        }
    }
    
    public synchronized boolean isFull(){
        return orders.size() == CAPACITY;
    }
    
    public synchronized int getSize(){
        return orders.size();
    }
}
