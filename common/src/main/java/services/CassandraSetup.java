package services;

import com.datastax.driver.core.Session;

public class CassandraSetup {

    public static void main(String[] args) {

        CassandraHelper cassandraHelper = CassandraHelper.getInstance();
        Session session = cassandraHelper.getSession();
        session.execute("CREATE KEYSPACE IF NOT EXISTS demo WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};");
        session.execute("CREATE TABLE IF NOT EXISTS demo.song (song_id text,  key_codes list<int>, PRIMARY KEY(song_id));");
        System.out.println("created table for piano data pipeline");
        cassandraHelper.close();

    }
}
