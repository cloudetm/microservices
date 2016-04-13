package com.company.app;

import java.util.concurrent.ExecutionException;

interface DemoProducer {
    void configure(String brokerList, String sync);

    void start();

    void produce(String s) throws ExecutionException, InterruptedException;

    void close();
}

public class App {
    private static DemoProducer producer;

    public static void main(String[] args) {
        // try following one by one, and observe the consumer console window output
        oldProducerSync();
//        oldProducerAsync();
//        newProducerSync();
//        newProducerAsync();
    }

    private static void newProducerAsync() {
        String age = "new";
        String sync = "async";
        startProducer(age, sync);
    }
    private static void newProducerSync() {
        String age = "new";
        String sync = "sync";
        startProducer(age, sync);
    }

    private static void oldProducerAsync() {
        String age = "old";
        String sync = "async";
        startProducer(age, sync);
    }

    private static void oldProducerSync() {
        String age = "old";
        String sync = "sync";
        startProducer(age, sync);
    }

    private static void startProducer(String age, String sync) {
        String brokerList = "localhost:9092";
        String topic = "first";
        int delay = 500;
        int count = 3;

        if (age.equals("old")) {
            producer = new DemoProducerOld(topic);
        } else if (age.equals("new")) {
            producer = new DemoProducerNew(topic);
        } else {
            System.out.println("Third argument should be old or new, got " + age);
            System.exit(-1);
        }

        // start a producer
        producer.configure(brokerList, sync);
        producer.start();

        long startTime = System.currentTimeMillis();
        System.out.println("Starting...");
        try {
            producer.produce("Starting...");

            for (int i = 0; i < count; i++) {
                producer.produce(Integer.toString(i));
                Thread.sleep(delay);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("... and we are done. This took " + (endTime - startTime) + " ms");
            producer.produce("... and we are done. This took " + (endTime - startTime) + " ms.");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.close();
        System.exit(0);
    }
}
