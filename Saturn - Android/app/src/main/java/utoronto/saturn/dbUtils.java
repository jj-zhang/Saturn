package utoronto.saturn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class dbUtils {

    private final static String driver = "org.postgresql.Driver";
    private final static String url = "jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn";
    private final static String username = "tjlevpcn";
    private final static String password = "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu";
    private final static String eventsColumn = "(id, creator, name, description, date, type, url)";
    private final static String usersColumn = "(email, username, password, eventid)";
    private static Connection SQLConnection;
    private static Statement SQLStatement;

    /**
     * Check if sql library exists
     */
    private static void testForJar() {
        try {
            Class.forName(driver);
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests connection to database through HTTP request
     */
    private static void requestHTTP() {
        try {
            SQLConnection = DriverManager.getConnection(url, username, password);
            SQLStatement = SQLConnection.createStatement();
        } catch (SQLException e) {
            System.out.println("Unable to connect to Database!");
            SQLConnection = null;
        }
    }

    /**
     * Return whether or not we're currently connected to the database
     *
     * @return True if connected, else false
     */
    private static boolean isConnected() {
        return SQLConnection != null;
    }

    /**
     * Return whether or not the attempt to connect was successful
     *
     * @return True if connected, else false
     */
    private static boolean tryConnect() {
        // Runs if any request fails and in main
        if(!isConnected()) {
            testForJar();
            requestHTTP();
        }

        return isConnected();
    }

    /**
     * Deletes a row from table with SQLIdentifier equal to SQLValue
     *
     * @param table Table in database
     * @param SQLIdentifier Column in database
     * @param SQLValue Row in column in database
     * @return Return whether the query successfully executed
     */
    public static boolean deleteRow(String table, String SQLIdentifier, String SQLValue){
        if(!tryConnect()) {
            return false;
        }

        try {
            SQLStatement.executeQuery("DELETE FROM " + table + " WHERE " + SQLIdentifier + "=" + SQLValue);
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    /**
     * Add a row in users
     *
     * @param email User's email
     * @param username User's username
     * @param password User's password
     * @param eventid A user event's ID
     * @return Return whether the query successfully executed
     */
    public static boolean addRow(String email, String username, String password, int eventid){
        if(!tryConnect()) {
            return false;
        }

        try {
            SQLStatement.executeQuery("INSERT INTO users " + usersColumn +
                    " VALUES ('" + email + "','" + username + "', '" + password + "', '" + eventid + "')");
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    /**
     * Add a row in events
     *
     * @param creator Creator of the event
     * @param name Name of event
     * @param description Description of event
     * @param type Type of event ie. Anime
     * @param url Image url
     * @return Return whether the query successfully executed
     */
    public static boolean addRow(String creator, String name, String description, String date, String type, String url) throws ParseException {
        if(!tryConnect()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date properDate = dateFormat.parse(date);

        try {
            SQLStatement.executeQuery("INSERT INTO events " + eventsColumn +
                    " VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + description + "', '" + properDate + "', '" + type + "', '" + url + "')");
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    /**
     * Add a column in table
     *
     * @param table Table to add column to
     * @param valueName Name of column
     * @param valueType Type of column
     * @return Return whether the query successfully executed
     */
    private static boolean addColumn(String table, String valueName, String valueType){
        if(!tryConnect()) {
            return false;
        }

        try {
            SQLStatement.executeQuery("ALTER TABLE " + table + " ADD " + valueName + " " + valueType);
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    /**
     * Remove a column in table
     *
     * @param table Table to add column to
     * @param valueName Name of column
     * @return Return whether the query successfully executed
     */
    private static boolean removeColumn(String table, String valueName){
        if(!tryConnect()) {
            return false;
        }

        try {
            SQLStatement.executeQuery("ALTER TABLE " + table + " DROP " + valueName);
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    public static void main(String a[]) {
        System.out.println(addRow("a@a.ca", "a", "a", 1));
    }
}