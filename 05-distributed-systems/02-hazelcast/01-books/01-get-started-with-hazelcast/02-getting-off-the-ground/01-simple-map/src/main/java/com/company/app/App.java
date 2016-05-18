package com.company.app;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        Map<String, String> capitals = hz.getMap("capitals");
        capitals.put("GB", "London");
        capitals.put("FR", "Paris");
        capitals.put("US", "Washington DC");
        capitals.put("AU", "Canberra");

        System.out.println("Known capital cities: " + capitals.size());

        System.out.println("Capital city of GB: " + capitals.get("GB"));
    }
}
/*

output:
1 cluster
Members [1] {
	Member [192.168.1.8]:5701 this
}
Known capital cities: 4
Capital city of GB: London

$ java -cp hazelcast-3.6.2.jar com.hazelcast.console.ConsoleApp
hazelcast[default] > ns capitals
namespace: capitals
hazelcast[capitals] > m.get GB
London

 */