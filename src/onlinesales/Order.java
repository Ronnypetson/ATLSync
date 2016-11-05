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
public class Order {
    private long orderNumber;
    private String description;
    private static long lastNumberUsed = 0;
    
    /*
        A order is composed by a identification number and a description 
        (in theory, with a maximum of 1000 characters).
        The number will be chosen (at random) at the initialization of a order
        object, but it also can be defined by the static variable
        lastNumberUsed, which will/should provide unique identification.
    */
    
    public Order(String description){
        Order.lastNumberUsed++;
        this.orderNumber = Order.lastNumberUsed;
        this.description = description;
    }
    
    public Order(long orderNumber, String description){
        this.orderNumber = orderNumber;
        this.description = description;
    }
    
    public long getOrderNumber(){
        return this.orderNumber;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setOrderNumber(long newOrderNumber){
        this.orderNumber = newOrderNumber;
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
}
