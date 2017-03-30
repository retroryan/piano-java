package services;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraHelper {

    public static String HOST = "127.0.0.1";
    private Cluster cluster;
    private Session session;

    private static CassandraHelper ourInstance = new CassandraHelper();

    public static CassandraHelper getInstance() {
        return ourInstance;
    }

    private CassandraHelper() {
        this.cluster = Cluster.builder().addContactPoint("127.0.0.1").build();;
        this.session = cluster.connect();
    }

    public void close() {
        this.cluster.close();
    }

    public Session getSession() {
        return session;
    }
}
