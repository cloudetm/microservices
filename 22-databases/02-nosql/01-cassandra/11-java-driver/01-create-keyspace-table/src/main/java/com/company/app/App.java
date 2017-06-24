package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/*

1, Create keyspace
https://www.tutorialspoint.com/cassandra/cassandra_create_keyspace.htm

2, Create table
https://www.tutorialspoint.com/cassandra/cassandra_create_table.htm

CQL:
-----------------------
DESCRIBE KEYSPACES;

CREATE KEYSPACE IF NOT EXISTS home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

DESCRIBE KEYSPACE home_security;

DROP KEYSPACE IF EXISTS home_security;

-----------------------
USE home_security;

CREATE TABLE IF NOT EXISTS activity (
home_id text,
datetime timestamp,
event text,
code_used text,
PRIMARY KEY (home_id, datetime)
);

DESCRIBE TABLE activity;

DROP TABLE IF EXISTS activity;

DESCRIBE TABLES;

 */
public class App
{
    public static void main( String[] args )
    {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
            //.withCredentials("jeff", "i6XJsj!k#9")
            .build();

        // create session
        Session session = cluster.connect();

        dropKeyspace(session);
        createKeyspace(session);

        connectKeyspace(session);

        dropTable(session);
        createTable(session);

        session.close();
        cluster.close();

        System.out.println("### END");

    }

    public static void dropKeyspace(Session session) {

        System.out.println("dropKeyspace");

        String query = "DROP KEYSPACE IF EXISTS home_security;";

        session.execute(query);
    }

    public static void createKeyspace(Session session) {

        System.out.println("createKeyspace");

        String query = "CREATE IF NOT EXISTS KEYSPACE home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};";

        session.execute(query);
    }

    private static void dropTable(Session session) {

        System.out.println("dropTable");

        // Query
        String query = "DROP TABLE IF EXISTS activity;";

        // Executing the query
        session.execute(query);
    }

    private static void connectKeyspace(Session session) {

        System.out.println("connectKeyspace");

        // Query
        String query = "USE home_security";

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
/*
output:
dropKeyspace
createKeyspace
connectKeyspace
dropTable
createTable
### END
 */
