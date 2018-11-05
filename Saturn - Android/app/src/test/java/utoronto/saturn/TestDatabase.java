package utoronto.saturn;
import org.junit.Test;
import org.junit.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class TestDatabase{

    EventDatabase db;

    @Before
    public void create() throws SQLException {
        db = new EventDatabase("SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu", "tjlevpcn", "jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn");
    }

    @Test
    public void testColumnsUsers() throws SQLException{
        Statement st = db.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users");
        ResultSetMetaData rsmd = rs.getMetaData();

        assertEquals(5, rsmd.getColumnCount());
        rs.close();
        st.close();
    }

    @Test
    public void testColumnsEvents() throws SQLException{
        Statement st = db.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM events");
        ResultSetMetaData rsmd = rs.getMetaData();

        assertEquals(6, rsmd.getColumnCount());
        rs.close();
        st.close();
    }

//    @Test
//    public void testAddEvent() throws SQLException{
//        db.addEvent("Yuri On Ice", "anime", "Sampleurl", "2019-02-04", true);
//
//        Statement st = db.connection.createStatement();
//        ResultSet rs = st.executeQuery("SELECT id FROM events WHERE name = 'Yuri On Ice'");
//
//        rs.next();
//        assertEquals(3, rs.getInt(1));
//        db.deleteEvent(rs.getInt(1));
//        rs.close();
//        st.close();
//    }

    @Test(expected = SQLException.class)
    public void testDeleteEvent() throws SQLException{
        //db.addEvent("Yuri On Ice", "anime", "Sampleurl", "2019-02-04", true);
        Statement st = db.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT id FROM events WHERE name = 'Yuri On Ice'");

        rs.next();
        db.deleteEvent(rs.getInt(1));

        rs = st.executeQuery("SELECT id FROM events WHERE name = 'Yuri On Ice'");

        rs.close();
        st.close();
    }

    @Test()
    public void testCreateEvent() throws SQLException, MalformedURLException, ParseException {
        Statement st = db.connection.createStatement();
        Event e = db.createEvent(6001);

        assertEquals("Yuru Camp", e.getName());
        URL u = new URL("https://myanimelist.cdn-dena.com/images/anime/4/89877.jpg");

        assertEquals(u, e.getImageURL());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = f.parse("2018-01-04");
        long milliseconds = parseDate.getTime();
        //Event newEvent = new Event(Integer.toString(1), "YuruCamp", u,milliseconds);

        assertEquals(milliseconds, e.getReleaseDate());

    }

    @Test()
    public void testGetPopular() throws SQLException, MalformedURLException, ParseException {
        List<Event>  list = db.getPopular();
        list = db.getSuggested();
        for(Event e : list){
            System.out.print(e.getName());
        }

        assertEquals(0, 0);

    }
}
