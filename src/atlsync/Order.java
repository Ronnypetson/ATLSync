/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.math.BigInteger;

/**
 *
 * @author ronnypetsonss
 */
public class Order {
    private BigInteger id;
    private String data;
    
    public Order(BigInteger id, String data){
        super();
        this.id = id;
        this.data = data;
    }
    
    public BigInteger getId(){
        return this.id;
    }
    
    public String getData(){
        return this.data;
    }
}
