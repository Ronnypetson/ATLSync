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
    final static int numConsumers[] = {1, 5, 10, 50}; // , 100, 500, 1000
    final static int numProducers[] = {1, 5, 10, 50};
    final static int numRepetitions = 10;
    static OrderBuffer buffer = new OrderBuffer();
    static OrderConsumer consumers[];
    static OrderProducer producers[];
    
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
// <<<<<<< OURS
        // test1();
        test2();
    }
    
    private static void test2() throws InterruptedException{
        double statistics[][] = new double[numConsumers.length][4];
        // 0: mean, 1: minimum, 2: maximum, 3: standard deviation
        //
        for(int i = 0; i < numConsumers.length; i++){
            //
            // OrderBuffer.CAPACITY = 5*numConsumers[i];
            //
            System.out.println("\n\n\t -- " + numConsumers[i] + " consumers --\n");
            long min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            double mean = 0.0;
            double times[] = new double[numRepetitions];
            consumers = new OrderConsumer[numConsumers[i]];
            producers = new OrderProducer[numProducers[i]];
            for(int j = 0; j < numRepetitions; j++){
                System.out.println("- Repetition #" + (j+1) + " - \n");
                // buffer.fill();
                // setConsumers(numConsumers[i]);
                long t0 = System.currentTimeMillis();
                for(int k = 0; k < numProducers[i]; k++){
                    producers[k] = new OrderProducer(k, buffer);
                    producers[k].start();
                }
                for(int k = 0; k < numConsumers[i]; k++){
                    consumers[k] = new OrderConsumer(k, buffer);
                    consumers[k].start();
                }
                //
                // Vai para a próxima repetição depois que os consumidores terminam
                for(int k = 0; k < numConsumers[i]; k++){
                    consumers[k].join();
                }
                for(int k = 0; k < numProducers[i]; k++){
                    producers[k].join();
                }
                long t1 = System.currentTimeMillis();
                long t = t1 - t0;
                times[j] = t;
                if(t < min){
                    min = t;
                }
                if(t > max){
                    max = t;
                }
                mean += t;
            }
            mean /= numRepetitions;
            //
            /* double k_ = 5000.0/OrderBuffer.CAPACITY;
            mean *= k_;
            min *= k_;
            max *= k_; */
            //
            double sd = 0.0;
            for(int j = 0; j < numRepetitions; j++){
                sd += Math.pow(mean - times[j], 2.0);
            }
            sd /= numRepetitions;
            sd = Math.sqrt(sd);
            statistics[i][0] = mean;
            statistics[i][1] = min;
            statistics[i][2] = max;
            statistics[i][3] = sd;
            //
        }
        printStatistics(statistics);
    }
    
    private static void test1() throws InterruptedException{
        double statistics[][] = new double[numConsumers.length][4];
        // 0: mean, 1: minimum, 2: maximum, 3: standard deviation
        //
        for(int i = 0; i < numConsumers.length; i++){
            //
            // OrderBuffer.CAPACITY = 5*numConsumers[i];
            //
            System.out.println("\n\n\t -- " + numConsumers[i] + " consumers --\n");
            long min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            double mean = 0.0;
            double times[] = new double[numRepetitions];
            for(int j = 0; j < numRepetitions; j++){
                System.out.println("- Repetition #" + (j+1) + " - \n");
                // buffer.fill();
                setConsumers(numConsumers[i]);
                long t0 = System.currentTimeMillis();
                for(int k = 0; k < numConsumers[i]; k++){
                    consumers[k].start();
                }
                //
                // Vai para a próxima repetição depois que os consumidores terminam
                for(int k = 0; k < numConsumers[i]; k++){
                    consumers[k].join();
                }
                long t1 = System.currentTimeMillis();
                long t = t1 - t0;
                times[j] = t;
                if(t < min){
                    min = t;
                }
                if(t > max){
                    max = t;
                }
                mean += t;
            }
            mean /= numRepetitions;
            //
            /* double k_ = 5000.0/OrderBuffer.CAPACITY;
            mean *= k_;
            min *= k_;
            max *= k_; */
            //
            double sd = 0.0;
            for(int j = 0; j < numRepetitions; j++){
                sd += Math.pow(mean - times[j], 2.0);
            }
            sd /= numRepetitions;
            sd = Math.sqrt(sd);
            statistics[i][0] = mean;
            statistics[i][1] = min;
            statistics[i][2] = max;
            statistics[i][3] = sd;
            //
        }
        printStatistics(statistics);
    }
    
    private static void printStatistics(double stat[][]){
        System.out.print("\tMean, \t\tMin, \t\tMax, \t\tStd_Dev\n");
        for(int i = 0; i < numConsumers.length; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(" \t" + stat[i][j] + ",");
            }
            System.out.print(" \t" + stat[i][3] + "\n");
        }
    }
    
    private static void setConsumers(int numCons){
        consumers = new OrderConsumer[numCons];
        for(int i = 0; i < numCons; i++){
            consumers[i] = new OrderConsumer(i, buffer);
        }
    }
}
