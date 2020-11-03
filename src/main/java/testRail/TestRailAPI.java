package testRail;

import aquality.selenium.browser.AqualityServices;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestResult;
import testRail.gurock.APIClient;
import testRail.gurock.APIException;
import utils.SettingsData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailAPI {

    private static APIClient client;

    private static APIClient getClient() {
        if (client == null) {
            client = new APIClient(SettingsData.getStringValue("test.rail.url"));
            client.setUser(SettingsData.getStringValue("test.rail.username"));
            client.setPassword(SettingsData.getStringValue("test.rail.password"));
        }
        return client;
    }

    private static int getId(JSONObject object) {
        return Integer.parseInt(object.get("id").toString());
    }

    public static int getProjectId() {
        int id = 0;
        try {
            JSONArray projects = (JSONArray) getClient().sendGet("get_projects");
            JSONObject project = (JSONObject) projects.get(0);
            id = getId(project);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createSuite(int projectId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        try {
            JSONObject suite = (JSONObject) getClient().sendPost("add_suite/" + projectId, data);
            id = getId(suite);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createSection(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject section = (JSONObject) getClient().sendPost("add_section/" + projectId, data);
            id = getId(section);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createTestCase(int sectionId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("title", name);
        try {
            JSONObject testCase = (JSONObject) getClient().sendPost("add_case/" + sectionId, data);
            id = getId(testCase);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static int createRun(int projectId, int suiteId, String name) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        try {
            JSONObject run = (JSONObject) getClient().sendPost("add_run/" + projectId, data);
            id = getId(run);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    private static int getTestRailStatusId(int statusId) {
        switch (statusId) {
            case ITestResult.SUCCESS:
                return 1;
            case ITestResult.FAILURE:
                return 5;
            case ITestResult.SKIP:
                return 4;
            default:
                return 3;
        }
    }

    private static String getCommentByStatusId(int statusId) {
        switch (statusId) {
            case ITestResult.SUCCESS:
                return "Passed";
            case ITestResult.FAILURE:
                return "Failed";
            case ITestResult.SKIP:
                return "Skipped";
            default:
                return "Untested";
        }
    }

    public static void addAttachmentToResult(int resultId, String path) {
        try {
            getClient().sendPost("add_attachment_to_result/" + resultId, path);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static int addResult(int runId, int caseId, int statusId) {
        int id = 0;
        Map<String, Object> data = new HashMap<>();
        data.put("status_id", getTestRailStatusId(statusId));
        data.put("comment", getCommentByStatusId(statusId));
        try {
            JSONObject result = (JSONObject) getClient().sendPost("add_result_for_case/" + runId + "/" + caseId, data);
            id = getId(result);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return id;
    }

    public static void deleteRun(int runId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_run/" + runId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteSuite(int suiteId) {
        Map<String, Object> data = new HashMap<>();
        try {
            getClient().sendPost("delete_suite/" + suiteId, data);
        } catch (IOException | APIException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public static void deleteRunSuite(int runId, int suiteId) {
        deleteRun(runId);
        deleteSuite(suiteId);
    }
}
