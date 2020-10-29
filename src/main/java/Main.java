import database.ConnectionManager;
import database.DB;

import java.sql.Timestamp;

public class Main {

    private static final Timestamp dateTimeFrom = Timestamp.valueOf("2015-11-07 00:00:00");
    private static final String[] browsers = { "Firefox", "Chrome" };

    public static void main(String[] args) {
        ConnectionManager.createConnection();
        DB.showTestProjectMinTime();
        DB.showProjectTestCount();
        DB.showTestProjectFromDate(dateTimeFrom);
        DB.showBrowsersTestCount(browsers);
        ConnectionManager.closeConnection();
    }
}
