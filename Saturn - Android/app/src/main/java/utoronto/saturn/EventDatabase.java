package utoronto.saturn;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDatabase extends Database {

    public EventDatabase(Object password, String dbms, String dbName, String portNumber, Object userName, String serverName) {
        super(password, dbms, dbName, portNumber, userName, serverName);
        log.setLevel(Level.FINE);
    }


}
