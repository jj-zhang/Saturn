package utoronto.saturn;

import java.sql.Array;
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


public class dbUtils {

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
     * Deletes a row from table with SQLIdentifier equal to row
     *
     * @param table Table in database
     * @param column Column in database
     * @param row Row in column in database
     * @return Return whether the query successfully executed
     */
    public static boolean deleteRow(String table, String column, String row) throws IllegalArgumentException {
        if(!tryConnect()) {
            return false;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        }

        try {
            SQLStatement.executeQuery("DELETE FROM " + table + " WHERE " + column + "=" + row);
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
    public static boolean addRow(String creator, String name, String description, String date, String type, String url, boolean isglobal) throws ParseException {
        if(!tryConnect()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date properDate = dateFormat.parse(date);

        try {
            SQLStatement.executeQuery("INSERT INTO events " + eventsColumn +
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

    private static String queryColumn(String table, String column) throws IllegalArgumentException {
        if (table.equals("events")) {
            if (!eventsValues.contains(column)) {
                throw new IllegalArgumentException("Column " + column + " is not valid!");
            }
        } else if (table.equals("users")) {
            if (!usersValues.contains(column)) {
                throw new IllegalArgumentException("Column " + column + " is not valid!");
            }
        }

        return column;
    }

    private static String queryColumns(String table, List<String> columns) throws IllegalArgumentException {
        String columnQuery = "";

        for(String column : columns) {
            columnQuery += queryColumn(table, column) + ", ";
        }

        return columnQuery.substring(0, columnQuery.length() - 2);
    }

    private static String queryRow(String column, String row) throws IllegalArgumentException {
        if(row.contains(" ")) {
            throw new IllegalArgumentException(row + " is trying to exploit SQL query!");
        }

        return column + "=" + row;
    }

    /**
     * Automates the querying and error-checking of multiple row queries over a single column
     *
     * @param column
     * @param rows
     * @return Query of rows
     * @throws IllegalArgumentException
     */
    private static String queryRows(String column, List<String> rows) throws IllegalArgumentException {
        for(String row : rows)
            if(row.contains(" "))
                throw new IllegalArgumentException(row + " is trying to exploit SQL query!");

        String rowQuery = "";

        for(String row : rows)
            rowQuery += column + "=" + row + " OR ";

        return rowQuery.substring(0, rowQuery.length() - 4);
    }

    /**
     * Automates the querying and error-checking of multiple column and row queries
     * Queries column index and row index
     * Ie. ["A", "B"], ["C", "D"] = "A=C", "B=D"
     *
     * @param columns
     * @param rows
     * @return Query of rows
     * @throws IllegalArgumentException
     */
    private static String queryRows(List<String> columns, List<String> rows) throws IllegalArgumentException {

        if(columns.size() != rows.size()) {
            throw new IllegalArgumentException("Column List is not the same size of Row list");
        }

        String rowQuery = "";

        for(int i = 0; i < columns.size(); i++) {
            rowQuery += queryRow(columns.get(i), rows.get(i)) + " OR ";
        }

        return rowQuery.substring(0, rowQuery.length() - 4);
    }

    /**
     * Return the query after selecting a column
     *
     * @param table Table to select from
     * @param column Column to select
     * @return Query of selection
     * @throws IllegalArgumentException If values are not valid
     */
    private static ResultSet selectColumn(String table, String column) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else {
            queryColumn(table, column);
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

        String columnQuery;

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else {
            columnQuery = queryColumns(table, columns);
        }

        try {
            String query = "SELECT " + columnQuery + " FROM " + table;
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
     * @param row Value to select in the column
     * @return Query of selection
     * @throws IllegalArgumentException If values are not valid
     */
    public static ResultSet selectRow(String table, String column, String row) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        String rowQuery;

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else {
            rowQuery = queryRow(column, row);
        }

        try {
            return SQLStatement.executeQuery("SELECT " + column + " FROM " + table + " WHERE " + rowQuery);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    public static ResultSet selectRow(String table, List<String> columns, String value) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        String columnQuery;

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if (value.contains(" ")) {
            throw new IllegalArgumentException("Value is trying to exploit SQL query!");
        } else {
            columnQuery = queryColumns(table, columns);
        }

        try {
            String query = "SELECT " + columnQuery + " FROM " + table + " WHERE ";
            String laterQuery = "";
            for(String column : columns) { // TODO: DEAL WITH THIS
                laterQuery += column + "=" + value;
            }
            query += laterQuery;
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    public static ResultSet selectRows(String table, String column, List<String> rows) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        String rowQuery;

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else if ((table.equals("events") && !eventsValues.contains(column)) || (table.equals("users") && !usersValues.contains(column))) {
            throw new IllegalArgumentException("Column is not valid!");
        } else
            rowQuery = queryRows(column, rows);

        try {
            String query = "SELECT " + column + " FROM " + table + " WHERE " + rowQuery;
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

    public static ResultSet selectRows(String table, List<String> columns, List<String> rows) throws IllegalArgumentException {
        if(!tryConnect()) {
            return null;
        }

        String columnQuery, rowQuery;

        if(!tables.contains(table)) {
            throw new IllegalArgumentException("Table is not valid!");
        } else {
            columnQuery = queryColumns(table, columns);
            rowQuery = queryRows(columns, rows);
        }

        try {
            String query = "SELECT " + columnQuery + " FROM " + table + " WHERE " + rowQuery;
            return SQLStatement.executeQuery(query);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            SQLConnection = null;
        }
        return null;
    }

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
        deleteRow("events", "id", "4001");
        printTable("events", 5);
    }
}