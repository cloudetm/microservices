package com.company.app;


import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

public class App
{
    public static void main( String[] args )
    {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, String> capitals = hz.getMap("capitals");
        capitals.put("GB", "London");
        capitals.put("FR", "Paris");
        capitals.put("US", "Washington DC");
        capitals.put("AU", "Canberra");

        ISet<String> cities = hz.getSet("cities");
        cities.addAll(capitals.values());
        cities.add("London");
        cities.add("Rome");
        cities.add("New York");

        cities.forEach(c -> System.out.print(c + ", "));
    }
}
/*
output:
3 clusters
Members [3] {
	Member [192.168.1.8]:5702
	Member [192.168.1.8]:5701
	Member [192.168.1.8]:5703 this
}
Washington DC, London, Canberra, New York, Rome, Paris,

$ java -cp hazelcast-3.6.2.jar com.hazelcast.console.ConsoleApp
hazelcast[default] > ns cities
namespace: cities
hazelcast[cities] > s.iterator
1 Washington DC
2 London
3 Canberra
4 New York
5 Rome
6 Paris
hazelcast[cities] >
 */
