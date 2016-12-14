package com.company.app;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import java.util.stream.IntStream;

/*
Producer - offer items to client workers
 */
public class App
{
    public static void main( String[] args )
    {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IQueue<String> producer = hz.getQueue("arrivals");

        IntStream.range(1, 9).forEach(i -> producer.offer("item_" + i));

        System.out.println("# Producer finished offering");
    }
}
