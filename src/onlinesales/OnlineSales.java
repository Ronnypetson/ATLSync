/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinesales;

import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Tertulino
 */
public class OnlineSales {

    static OrderQueue orders;
    static int consNumber;
    static Consumer[] consumers;
    
    public static void initializeConsumers(){
        Scanner in = new Scanner(System.in);
        consNumber = in.nextInt();
        consumers = new Consumer[consNumber];
        
        for (int i = 0; i < consNumber; i++) {
            consumers[i] = (new Consumer(i+1, orders));
        }
    }
    
    public static void runConsumers(){
        for (int i = 0; i < consNumber; i++) {
            consumers[i].start();
        }
    }
    
    public static void main(String[] args) {
        float start = System.currentTimeMillis();
        
        orders = new OrderQueue();
        orders.initializeQueue();        
        initializeConsumers();        
        runConsumers();
        
        float end = System.currentTimeMillis();
        
        System.out.println("------------------------\n" +
                           "Tempo de execução (em s): " +
                           ( (end - start)/1000) +
                           "\n------------------------");
    }
    
}
