package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/*

1, Create table
https://www.tutorialspoint.com/cassandra/cassandra_create_table.htm

 */
public class App
{
    public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
            //.withCredentials("jeff", "i6XJsj!k#9")
            .build();

        // create session on the "hotel" keyspace
        Session session = cluster.connect("home_security");

        dropTable(session);

        createTable(session);

        session.close();
        cluster.close();

        System.out.println("### END");

    }

    private static void dropTable(Session session) {

        System.out.println("dropTable");

        // Query
        String query = "DROP TABLE IF EXISTS activity;";

        // Executing the query
        session.execute(query);
    }

    private static void createTable(Session session) {

        System.out.println("createTable");

        // Query
        String query = "CREATE TABLE IF NOT EXISTS activity (home_id text, "
            + "datetime timestamp, "
            + "event text, "
            + "code_used text, "
            + "PRIMARY KEY (home_id, datetime));";

        // Executing the query
        session.execute(query);
    }

}
