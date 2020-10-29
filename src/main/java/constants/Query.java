package constants;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Query {

    public static final String PROJECT_TEST_MIN_TIME =
            "select project.name, test.name, min(timestampdiff(second, start_time, end_time)) time " +
                    "from test join project on test.project_id = project.id " +
                    "group by test.name, test.project_id order by project.name, test.name";

    public static final String PROJECT_TESTS_COUNT =
            "select project.name, count(test.id) test_count " +
                    "from test join project on test.project_id = project.id " +
                    "group by test.project_id";

    private static final String TESTS_FROM_DATE_TIME_FORMAT =
            "select project.name, test.name, test.start_time " +
                    "from test join project on test.project_id = project.id " +
                    "where test.start_time >= '%tF %tT' " +
                    "order by project.name, test.name";

    private static final String BROWSERS_TEST_COUNT_FORMAT = "select count(id) from test where browser = '%s'";
    private static final String BROWSERS_TEST_COUNT_DELIMITER = " union all ";

    public static String getTestsFromDateTime(Timestamp datetime) {
        return String.format(Query.TESTS_FROM_DATE_TIME_FORMAT, datetime, datetime);
    }

    public static String getBrowsersTestCount(String[] browsers) {
        List<String> queryParts = new ArrayList<>();
        for (String browser : browsers) {
            String part = String.format(BROWSERS_TEST_COUNT_FORMAT, browser.toLowerCase());
            queryParts.add(part);
        }
        return String.join(BROWSERS_TEST_COUNT_DELIMITER, queryParts);
    }
}
