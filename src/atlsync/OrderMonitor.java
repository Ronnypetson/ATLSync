/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronnypetsonss
 */
public class OrderMonitor {
    private final int BUFFER_CAPACITY = 250;
    private boolean isFull;
    private boolean isEmpty;
    private Condition waiting;
    private Lock lock;
    
    public OrderMonitor(){
        isFull = false;
        isEmpty = true;
        lock = new ReentrantLock();
        waiting = lock.newCondition();
    }
    
    public void consume(OrderBuffer buffer, int consumerId){
        int consumed = 0;
        try {
            while(consumed < OrderConsumer.CONSUME_LIMIT){
                //
                Order ord = null;
                try{
                    lock.lock();
                    if(!isEmpty){
                        ord = buffer.getOrder();
                        isFull = false;
                        if(buffer.getSize() == 0){
                            isEmpty = true;
                        } else {
                            isEmpty = false;
                        }
                    } else {
                        ord = null;
                    }
                } finally {
                    lock.unlock();
                }
                if(ord != null){
                    consumed++;
                    Timestamp t0 = new Timestamp((new Date()).getTime());
                    Thread.sleep(OrderConsumer.SLEEP_DURATION);
                    Timestamp t1 = new Timestamp((new Date()).getTime());
                    printProcessing(t0,t1,ord, consumerId, "consumer");
                }
                //
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(OrderConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void produce(OrderBuffer buffer, int producerId) throws InterruptedException{
        //
        int produced = 0;
        while(produced < OrderProducer.PRODUCTION_LIMIT){
            //
            Order ord = null;
            try{
                lock.lock();
                if(!isFull){
                    String prod = "" + (producerId*1000 + (produced++));
                    ord = new Order(new BigInteger(prod), prod);
                    buffer.addOrder(ord);
                    isEmpty = false;
                } else {
                    ord = null;
                }
                if(buffer.isFull()){
                    isFull = true;
                } else {
                    isFull = false;
                }
            } finally {
                lock.unlock();
            }
            if(ord != null){
                Timestamp t0 = new Timestamp((new Date()).getTime());
                try {
                    Thread.sleep(OrderProducer.SLEEP_DURATION);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OrderProducer.class.getName()).log(Level.SEVERE, null, ex);
                }
                Timestamp t1 = new Timestamp((new Date()).getTime());
                printProcessing(t0,t1,ord,producerId,"producer");
            }
        }
    }
    
    private void printProcessing(Timestamp t0, Timestamp t1, Order ord, int id, String type){
        System.out.println("Order " + type + " id: " + id + "\n"
                         + "Order id: " + ord.getId() + "\n"
                         + "Starting time: " + t0 + "\n"
                         + "Ending time: " + t1 + "\n\n");
    }
}
