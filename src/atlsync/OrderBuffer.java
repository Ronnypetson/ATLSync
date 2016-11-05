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
    private final static int CAPACITY = 5000;
    
    public OrderBuffer(){
        orders = new LinkedList<>();
    }
    
    public void fill(){
        orders = new LinkedList<>();
        for(int i = 0; i < CAPACITY; i++){
            orders.add(new Order( BigInteger(""+i) ,""+i) );
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

    private BigInteger BigInteger(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
