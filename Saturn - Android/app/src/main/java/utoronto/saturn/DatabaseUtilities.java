package utoronto.saturn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DatabaseUtilities extends Database{

    private final static ArrayList<String> tables = new ArrayList<String>(Arrays.asList("events", "users"));
    private final static ArrayList<String> eventsValues = new ArrayList<String>(Arrays.asList("id", "creator", "name", "description", "date", "type", "url", "isglobal", "*"));
    private final static ArrayList<String> usersValues = new ArrayList<String>(Arrays.asList("email", "username", "password", "eventid", "*"));
    private final static String driver = "org.postgresql.Driver";
    private final static String url = "jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn";
    private final static String username = "tjlevpcn";
    private final static String password = "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu";
    private final static String eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
    private final static String usersColumn = "(email, username, password, eventid)";
    private static Connection SQLConnection;
    private static Statement SQLStatement;


    public DatabaseUtilities() throws SQLException {
        super();
    }
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
    public static boolean tryConnect() {
        // Runs if any request fails and in main
        if(!isConnected()) {
            testForJar();
            requestHTTP();
        }

        return isConnected();
    }

    /**
     * Deletes a row from table with Column equal to Value
     *
     * @param table Table in database
     * @param Column Column in database
     * @param Value Row in column in database
     * @return Return whether the query successfully executed
     */
    public static boolean deleteRow(String table, String Column, String Value) throws IllegalArgumentException {
        if(!tryConnect()) {
            return false;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        }

        try {
            SQLStatement.executeUpdate("DELETE FROM " + table + " WHERE " + Column + "=" + Value);
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
    public static boolean addRowUser(String email, String username, String password, int eventid){
        if(!tryConnect()) {
            return false;
        }

        try {
            SQLStatement.executeUpdate("INSERT INTO users " + usersColumn +
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
    public static boolean addRowEvent(String creator, String name, String description, String date, String type, String url, boolean isglobal) throws ParseException {
        if(!tryConnect()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date properDate = dateFormat.parse(date);

        try {
            SQLStatement.executeUpdate("INSERT INTO events " + eventsColumn +
                    " VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + description + "', '" + properDate + "', '" + type + "', '" + url + "', '" + isglobal + "')");
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
            SQLStatement.executeUpdate("ALTER TABLE " + table + " ADD " + valueName + " " + valueType);
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
            SQLStatement.executeUpdate("ALTER TABLE " + table + " DROP " + valueName);
            return true;
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return false;
    }

    /**
     * Return the query after selecting a column
     *
     * @param table Table to select from
     * @param column Column to select
     * @return Query of selection
     * @throws IllegalArgumentException If values are not valid
     */
    public static ResultSet selectColumn(String table, String column) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if ((table.equals("events") && !eventsValues.contains(column)) || (table.equals("users") && !usersValues.contains(column))) {
            throw new IllegalArgumentException("Column is not valid!");
        }

        try {
            return SQLStatement.executeQuery("SELECT " + column + " FROM " + table);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Return the query after selecting columns
     *
     * @param table Table to select from
     * @param columns Columns to select
     * @return Query of selection
     * @throws IllegalArgumentException If values are not valid
     */
    private static ResultSet selectColumns(String table, List<String> columns) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if (table.equals("events")) {
            for (String column : columns) {
                if (!eventsValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
        } else if (table.equals("users")) {
            for (String column : columns) {
                if (!usersValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
        }

        try {
            String query = "SELECT ";
            for(String column : columns) {
                query += column + ", ";
            }
            query = query.substring(0, query.length() - 2) + " FROM " + table;
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /**
     * Return the query after selecting a column
     *
     * @param table Table to select from
     * @param column Column to select from
     * @param value Value to select in the column
     * @return Query of selection
     * @throws IllegalArgumentException If values are not valid
     */
    public static ResultSet selectRow(String table, String column, String conditionColumn, String value) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if ((table.equals("events") && !eventsValues.contains(column) && !eventsValues.contains(conditionColumn)) || (table.equals("users") && !usersValues.contains(column) && !usersValues.contains(conditionColumn))) {
            throw new IllegalArgumentException("Column is not valid!");
        } else if (value.contains(" ")) {
            throw new IllegalArgumentException("Value is trying to exploit SQL query!");
        }

        try {
            return SQLStatement.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + conditionColumn + "=" + value);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    public static ResultSet selectRows(String table, List<String> columns, String conditionColumn, String value) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if (table.equals("events")) {
            for (String column : columns) {
                if (!eventsValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
            if(!eventsValues.contains(conditionColumn)) {
                throw new IllegalArgumentException("Column " + conditionColumn + " is not valid!");
            }
        } else if (table.equals("users")) {
            for (String column : columns) {
                if (!usersValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
            if(!usersValues.contains(conditionColumn)) {
                throw new IllegalArgumentException("Column " + conditionColumn + " is not valid!");
            }
        } else if (value.contains(" ")) {
            throw new IllegalArgumentException("Value is trying to exploit SQL query!");
        }

        try {
            String query = "SELECT ";
            for(String column : columns) {
                query += column + ", ";
            }
            query = query.substring(0, query.length() - 2) + " FROM " + table + " WHERE " + conditionColumn + "=" + value;
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    /* Need to implement conditioncolumn
    public static ResultSet selectRows(String table, String column, List<String> values) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if ((table.equals("events") && !eventsValues.contains(column)) || (table.equals("users") && !usersValues.contains(column))) {
            throw new IllegalArgumentException("Column is not valid!");
        } else
            for(String value : values)
                if(value.contains(" "))
                    throw new IllegalArgumentException("Value is trying to exploit SQL query!");

        try {
            String query = "SELECT " + column + " FROM " + table + " WHERE ";
                for(String value : values)
                    query += column + "=" + value + " OR ";

            return SQLStatement.executeQuery(query.substring(0, query.length() - 4));
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    public static ResultSet selectRows(String table, List<String> columns, List<String> values) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if (table.equals("events")) {
            for (String column : columns) {
                if (!eventsValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
        } else if (table.equals("users")) {
            for (String column : columns) {
                if (!usersValues.contains(column)) {
                    throw new IllegalArgumentException("Column " + column + " is not valid!");
                }
            }
        } else
            for(String value : values)
                if(value.contains(" "))
                    throw new IllegalArgumentException("Value is trying to exploit SQL query!");

        try {
            String query = "SELECT ";
            String laterQuery = "";
            for(String column : columns) {
                query += column + ", ";
                for(String value : values)
                    laterQuery += column + "=" + value + " OR ";
            }
            query = query.substring(0, query.length() - 2) + " FROM " + table + " WHERE " + laterQuery.substring(0, laterQuery.length() - 4);
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }
    */

    /**
     * Prints out the entirety of the table
     *
     * @param table Table to print
     * @param rows Number of rows to print
     */
    private static boolean printTable(String table, int rows) throws IllegalArgumentException {
        if(!tryConnect()) {
            return false;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        }

        // Cycle through everything from select column
        ResultSet set = selectColumn(table, "*");
        if(set == null)
            return false;

        ResultSetMetaData metaSet;

        try {
            metaSet = set.getMetaData();

            ArrayList<String> columns = new ArrayList<>();

            for(int column = 1; column <= metaSet.getColumnCount(); column++) {
                columns.add(metaSet.getColumnLabel(column));
            }

            String rowString;
            while(set.next() && rows != 0) {
                rowString = "";
                for(int i = 0; i < columns.size(); i++) {
                    rowString += columns.get(i) + ": " + set.getString(columns.get(i)) + ", ";
                }

                System.out.println(rowString.substring(0, rowString.length() - 2));
                rows -= 1;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static void main(String a[]) {
        printTable("events", 5);
        ArrayList<String> lst = new ArrayList<>();
        ResultSet set = DatabaseUtilities.selectColumn("users", "email");
        try {
            while (set.next()) {
                lst.add(set.getString(1));
                System.out.print(set.getString(1));
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}