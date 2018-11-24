package utoronto.saturn;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLBackgroundUpdate extends AsyncTask<String, Void, Void> {
    SQLBackgroundUpdate() {
        super();
    }

    @Override
    protected Void doInBackground(String... strings) {
        // check if username is good
        try {
            Class.forName("org.postgresql.Driver");
            //STEP 3: Open a connection
            Log.d("myTag", "Connecting to database...");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://tantor.db.elephantsql.com:5432/tjlevpcn"
                    , "tjlevpcn", "SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu");
            PreparedStatement st = conn.prepareStatement(strings[0]);
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new IllegalStateException("Invalid Query");
    }
}
