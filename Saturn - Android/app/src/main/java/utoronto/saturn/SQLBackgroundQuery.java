package utoronto.saturn;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class SQLBackgroundQuery extends AsyncTask<String, Void, ResultSet> {
    Connection conn;
    PreparedStatement st;

    SQLBackgroundQuery() {
        super();
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        // check if username is good
        try {
            Class.forName("org.postgresql.Driver");
            //STEP 3: Open a connection
            Log.d("myTag", "Connecting to database...");
            conn = DriverManager.getConnection("jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn"
                    , "tjlevpcn", "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu");
            st = conn.prepareStatement(strings[0]);
            ResultSet result = st.executeQuery();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new IllegalStateException("Invalid Query");
    }
}
