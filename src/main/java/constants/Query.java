package constants;

public class Query {
    public static final String PROJECT_TEST_MIN_TIME =
            "select project.name, test.name, min(timestampdiff(second, start_time, end_time)) time " +
                    "from test join project on test.project_id = project.id " +
                    "group by test.name order by project.name, test.name";
}
