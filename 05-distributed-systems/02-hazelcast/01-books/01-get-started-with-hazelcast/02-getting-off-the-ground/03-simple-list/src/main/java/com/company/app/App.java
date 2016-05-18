package com.company.app;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;

import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();

        Map<String, String> captials = hz.getMap("capitals");
        captials.put("GB", "London");
        captials.put("FR", "Paris");
        captials.put("US", "Washington DC");
        captials.put("AU", "Canberra");

        IList<Object> countries = hz.getList("countries");
        countries.addAll(captials.keySet());
        countries.add("CA");
        countries.add("DE");
        countries.add("GB");

        countries.forEach(c -> System.out.print(c + ", "));
    }
}
/*
output:
Members [2] {
	Member [192.168.1.8]:5701
	Member [192.168.1.8]:5702 this
}
AU, US, FR, GB, CA, DE, GB,

$ java -cp hazelcast-3.6.2.jar com.hazelcast.console.ConsoleApp
hazelcast[default] > ns countries
namespace: countries
hazelcast[countries] > l.iterator
1 AU
2 US
3 FR
4 GB
5 CA
6 DE
7 GB
 */
