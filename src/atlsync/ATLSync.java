/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlsync;

/**
 *
 * @author ronnypetsonss
 */
public class ATLSync {
    final static int numConsumers[] = {1, 5, 10, 50, 100, 500, 1000};
    final static int numRepetitions = 10;
    static OrderBuffer buffer = new OrderBuffer();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OrderConsumer consumers[] = null;
        //
        double statistics[][] = new double[numConsumers.length][4];
        // 0: mean, 1: minimum, 2: maximum, 3: standard deviation
        //
        for(int i = 0; i < numConsumers.length; i++){
            setConsumers(consumers, numConsumers[i]);
            for(int j = 0; j < numRepetitions; j++){
                buffer.fill();
                for(int k = 0; k < numConsumers[i]; k++){
                    consumers[k].start();
                }
            }
        }
    }
    
    private static void setConsumers(OrderConsumer cons[], int numCons){
        cons = new OrderConsumer[numCons];
        for(int i = 0; i < numCons; i++){
            cons[i] = new OrderConsumer(i, buffer);
        }
    }
}
