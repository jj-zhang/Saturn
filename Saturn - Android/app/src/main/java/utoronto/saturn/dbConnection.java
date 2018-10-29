package utoronto.saturn;

//paste this into a file called Postgres.java
import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dbConnection {

    public static void main(String[] args) throws ParseException {

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn";
        String username = "tjlevpcn";
        String password = "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu";

        try {
            Connection db = DriverManager.getConnection(url, username, password);

            //delete(db, 4);
           //add(db, "FoodWar", "anime", "sampleurl", "2019-03-12");
            Statement st = db.createStatement();
//            ResultSet rs = st.executeQuery("CREATE TABLE users (\n" +
//                    "    id        SERIAL PRIMARY KEY,\n" +
//                    "    name       varchar(40) NOT NULL,\n" +
//                    "    lastName       varchar(40) NOT NULL,\n" +
//                    "    password        varchar(40) NOT NULL,\n" +
//                    "    eventId     INT" +
//                    ")");
//            ResultSet rs = st.executeQuery("INSERT INTO events (id, name, type, isGlobal, url, date)" +
//                    " VALUES (DEFAULT,'YuruCamp', 'anime', TRUE, 'sampleurl', '2019-01-23')");
//            ResultSet rs = st.executeQuery("UPDATE events SET date = '2019-01-23' WHERE id = 1");
//
            //ResultSet rs = st.executeQuery("SELECT id FROM events WHERE name = 'YuruCamp'");

//            ResultSet rs = st.executeQuery("INSERT INTO users (id, name, lastName, password, eventId)" +
//                  " VALUES (DEFAULT,'Jennifer', 'Law', 'password', 2)");

            //ResultSet rs = st.executeQuery("SELECT eventID, COUNT(*) FROM users GROUP BY eventId");
            //addColumn(db);
            ResultSet rs = st.executeQuery("SELECT * FROM events");

            //ResultSet rs = st.executeQuery("SELECT name, url, date FROM events");

            //ResultSet rs = st.executeQuery("UPDATE events SET url = 'https://myanimelist.cdn-dena.com/images/anime/4/89877.jpg' ");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
//                Array a = rs.getArray(1);
//                String[] b = (String []) a.getArray();
//                for(int i = 0; i < b.length; i ++)
//                    System.out.print(b[i]);


                for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
                    System.out.printf("Column %d returned ", i);
                    System.out.println(rsmd.getColumnName(i));
                    System.out.println(rs.getString(i));
                }
            }
            rs.close();
            st.close();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date parseDate = f.parse("2019-03-23");
            long milliseconds = parseDate.getTime();
            System.out.print(milliseconds);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(Connection db, int id){
        try {
        Statement st = db.createStatement();
        ResultSet rs = st.executeQuery("DELETE FROM events WHERE id=" + id);
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void add(Connection db, String name, String genre, String url, String date){
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO events (id, name, type, isGlobal, url, date)" +
                    " VALUES (DEFAULT, '" + name + "','" + genre + "', TRUE, '" + url +"', '" + date + "')");
        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addColumn(Connection db){
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("ALTER TABLE users ADD eventId INT");

        }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}