package utoronto.saturn;

import android.annotation.SuppressLint;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDatabase extends Database {

    EventDatabase() throws SQLException {
        super();
        log.setLevel(Level.FINE);
    }

    void addEvent(String name, String genre, String url, String date, boolean isGlobal) throws SQLException {
        String bool = "FALSE";
        if (isGlobal)
            bool = "TRUE";
        Statement st = super.connection.createStatement();
        st.executeUpdate("INSERT INTO events (id, name, type, isGlobal, url, date)" +
                " VALUES (DEFAULT, '" + name + "','" + genre + "','" + bool + "', '" + url + "', '" + date + "')");
    }

    void deleteEvent(int id) throws SQLException {
        Statement st = super.connection.createStatement();
        st.executeUpdate("DELETE FROM events WHERE id=" + id);
    }

    public List<Event> getPopular() throws SQLException, MalformedURLException, ParseException {
        Statement st = super.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT eventID, COUNT(*) AS count FROM users GROUP BY eventId ORDER BY count(*) DESC");
        ArrayList<Event> eventLst = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if (eventLst.size() > 5)
                break;
        }
        rs.close();
        st.close();

        return eventLst;

    }
    public List<Event> getTrending() throws SQLException, MalformedURLException, ParseException{
        Statement st = super.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT id FROM events ORDER BY date ORDER BY count(*) DESC");
        ArrayList<Event> eventLst = new ArrayList<>();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if(eventLst.size() > 5)
                break;
        }
        rs.close();
        st.close();
        return eventLst;
    }
    public List<Event> getSuggested() throws SQLException, MalformedURLException, ParseException{
        Statement st = super.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT type, COUNT(*) FROM events GROUP BY type ORDER BY count(*) DESC");
        String type = "";
        while (rs.next()) {
            type = rs.getString(1);
        }
        rs = st.executeQuery("SELECT id FROM events WHERE type = '" + type + "'");
        ArrayList<Event> eventLst = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if(eventLst.size() > 5)
                break;
        }
        rs.close();
        st.close();

        return eventLst;

    }

    Event createEvent(int id) throws SQLException, ParseException, MalformedURLException {
        Statement st = super.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT name, url, date FROM events WHERE id = " + id);
        ResultSetMetaData rsmd = rs.getMetaData();

        String name = "";
        String url = "";
        String date = "";

        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                if (i == 1)
                    name = rs.getString(i);
                if (i == 2)
                    url = rs.getString(i);
                if (i == 3)
                    date = rs.getString(i);
            }
        }

        //https://stackoverflow.com/questions/12473550/how-to-convert-a-string-date-to-long-millseconds
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = f.parse(date);
        long milliseconds = parseDate.getTime();

        URL u = new URL(url);

        Event newEvent = new Event(Integer.toString(id), name, u, milliseconds);
        rs.close();
        st.close();
        return newEvent;
    }
}
