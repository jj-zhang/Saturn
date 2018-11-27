package utoronto.saturn;

import android.annotation.SuppressLint;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EventDatabase extends Database {

    private static final String table = "events";

    protected static Logger log = Logger.getLogger(EventDatabase.class.getName());

    EventDatabase() {
        super();
        log.setLevel(Level.FINE);
    }

    boolean addEvent(String creator, String name, String description, String date, String type, String url, boolean isglobal) throws SQLException, ParseException{
        return DatabaseUtilities.addRowEvent(creator, name, description, date, type, url, isglobal);
    }

    boolean deleteEvent(int id) {
        return DatabaseUtilities.deleteRow(table, "id", Integer.toString(id));
    }

    public static List<Event> getUserFollowedEvents(String email) throws SQLException, MalformedURLException, ParseException {
        ResultSet rs = DatabaseUtilities.executeQuery(String.format("SELECT eventID FROM users WHERE email = '%s' AND eventId != -1", email));
        List<Event> result = new ArrayList<>();

        while (rs != null && rs.next()) {
            result.add(createEvent(rs.getInt("eventID")));
        }
        return result;
    }

    public static List<Event> getEventsByType(String type) throws SQLException, MalformedURLException, ParseException {
        ResultSet rs = DatabaseUtilities.executeQuery(String.format("SELECT DISTINCT id FROM events WHERE type = '%s'", type));
        List<Event> result = new ArrayList<>();

        while (rs != null && rs.next()){
            result.add(createEvent(rs.getInt("id")));
            log.info(String.format("Received event type = %s, id = %d", type, rs.getInt("id")));
        }

        return result;
    }

    public static List<Event> getPopular() throws SQLException, MalformedURLException, ParseException {
        ResultSet rs = DatabaseUtilities.executeQuery("SELECT eventID, COUNT(*) AS count FROM users GROUP BY eventId ORDER BY count(*) DESC");
        ArrayList<Event> eventLst = new ArrayList<>();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if (eventLst.size() > 5)
                break;
        }
        rs.close();

        return eventLst;

    }
    public static List<Event> getTrending() throws SQLException, MalformedURLException, ParseException{
        ResultSet rs = DatabaseUtilities.executeQuery("SELECT id FROM events ORDER BY date ORDER BY count(*) DESC");
        ArrayList<Event> eventLst = new ArrayList<>();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if(eventLst.size() > 5)
                break;
        }
        rs.close();
        return eventLst;
    }

    public static List<Event> getSuggested() throws SQLException, MalformedURLException, ParseException{
        ResultSet rs = DatabaseUtilities.executeQuery("SELECT type, COUNT(*) FROM events GROUP BY type ORDER BY count(*) DESC");
        String type = "";
        while (rs.next()) {
            type = rs.getString(1);
        }
        rs = DatabaseUtilities.selectRow(table, "id", "type", type);
        if(rs == null) {
            return null;
        }
        ArrayList<Event> eventLst = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            eventLst.add(createEvent(rs.getInt(1)));
            if(eventLst.size() > 5)
                break;
        }
        rs.close();

        return eventLst;

    }

    public static Event createEvent(int id) throws SQLException, ParseException, MalformedURLException {
        ResultSet rs = DatabaseUtilities.selectRows(table, Arrays.asList("name", "url", "date"), "id", Integer.toString(id));
        if(rs == null) {
            return null;
        }
        ResultSetMetaData rsmd = rs.getMetaData();

        String name;
        String url;
        String date;

        if (rs.next()) {
            name = rs.getString("name");
            url = rs.getString("url");
            date = rs.getString("date");
        } else {
            return null;
        }
        //https://stackoverflow.com/questions/12473550/how-to-convert-a-string-date-to-long-millseconds
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = f.parse(date);
        long milliseconds = parseDate.getTime();
        URL u = new URL(url);

        Event newEvent = new Event(Integer.toString(id), name, u, milliseconds);
        rs.close();
        return newEvent;
    }
}
