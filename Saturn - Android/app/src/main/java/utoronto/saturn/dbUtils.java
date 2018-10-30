package utoronto.saturn;

import java.sql.*;


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
     * @return Return the result of the query
     */
    public static ResultSet deleteRow(String table, String SQLIdentifier, String SQLValue){
        if(!tryConnect()) {
            return null;
        }

        try {
            return SQLStatement.executeQuery("DELETE FROM " + table + " WHERE " + SQLIdentifier + "=" + SQLValue);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Add a row in users
     *
     * @param email User's email
     * @param username User's username
     * @param password User's password
     * @param eventid A user event's ID
     * @return Return the result of the query
     */
    public static ResultSet addRow(String email, String username, String password, int eventid){
        if(!tryConnect()) {
            return null;
        }

        try {
            return SQLStatement.executeQuery("INSERT INTO users " + usersColumn +
                    " VALUES ('" + email + "','" + username + "', " + password + ", '" + eventid + "')");
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Add a row in events
     *
     * @param creator Creator of the event
     * @param name Name of event
     * @param description Description of event
     * @param type Type of event ie. Anime
     * @param url Image url
     * @return the result of the query
     */
    public static ResultSet addRow(String creator, String name, String description, String date, String type, String url){
        if(!tryConnect()) {
            return null;
        }

        try {
            return SQLStatement.executeQuery("INSERT INTO events " + eventsColumn +
                    " VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', " + description + "', '" + date + "', '" + type + "', '" + url + "')");
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Add a column in table
     *
     * @param table Table to add column to
     * @param valueName Name of column
     * @param valueType Type of column
     * @return the result of the query
     */
    private static ResultSet addColumn(String table, String valueName, String valueType){
        if(!tryConnect()) {
            return null;
        }

        try {
            return SQLStatement.executeQuery("ALTER TABLE " + table + " ADD " + valueName + " " + valueType);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Remove a column in table
     *
     * @param table Table to add column to
     * @param valueName Name of column
     * @return the result of the query
     */
    private static ResultSet removeColumn(String table, String valueName){
        if(!tryConnect()) {
            return null;
        }

        try {
            return SQLStatement.executeQuery("ALTER TABLE " + table + " DROP " + valueName);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }
}