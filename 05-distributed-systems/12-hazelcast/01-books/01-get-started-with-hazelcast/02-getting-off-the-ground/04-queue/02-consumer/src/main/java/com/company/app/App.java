package com.company.app;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/*
Consumer - client worker
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<String> arrivals = hz.getQueue("arrivals");

        while (true){
            String arrival = arrivals.take();
            System.out.println("New arrival from: " + arrival);
        }
    }
}
/*

1, Run the app (client worker)

2, Interact with test console - offer items to the queue for the client to process
$ java -cp hazelcast-3.6.2.jar com.hazelcast.console.ConsoleApp
hazelcast[countries] > ns arrivals
namespace: arrivals
hazelcast[arrivals] > q.offer Heathrow
true
hazelcast[arrivals] > q.offer JFK
true
hazelcast[arrivals] >

Output: (client worker)
New arrival from: Heathrow
New arrival from: JFK
 */