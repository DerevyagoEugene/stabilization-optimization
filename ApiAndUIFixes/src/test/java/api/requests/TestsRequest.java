package api.requests;

import api.models.TestDTO;
import utility.LogsUtility;

import java.io.IOException;
import java.util.List;

import static api.specifications.Specifications.*;
import static dataread.DataRead.*;
import static io.restassured.RestAssured.given;

public class TestsRequest {
    public static List<TestDTO> getTestsById(String projectID) {
        List<TestDTO> testDTOS = new java.util.ArrayList<>(List.of(given()
                .spec(requestSpecForTests)
                .filters(FORCE_JSON_RESPONSE_BODY)
                .queryParam(configDataApiDTO.projectIdParam, projectID)
                .post(configDataApiDTO.getTestsByJsonRequest)
                .then()
                .extract()
                .body()
                .as(TestDTO[].class)));
        return testDTOS;
    }

    public static String addTest(String projectName, String testName, String methodName) {
        return given()
                .spec(requestSpecForTests)
                .queryParam(configDataApiDTO.sidParam, testDataApiDTO.sid)
                .queryParam(configDataApiDTO.projectNameParam, projectName)
                .queryParam(configDataApiDTO.testNameParam, testName)
                .queryParam(configDataApiDTO.methodNameParam, methodName)
                .queryParam(configDataApiDTO.envParam, System.getProperty(configDataApiDTO.usersEnvName))
                .post(configDataApiDTO.addTestInTestRequests)
                .asString();
    }

    public static void addLogsToTest(String testId) throws IOException {
        for (int i = 0; i < LogsUtility.getLogsOfTest().size(); i++) {
            String line = LogsUtility.getLogsOfTest().get(i);
            given()
                    .spec(requestSpecForTests)
                    .queryParam(configDataApiDTO.testIdParam, testId)
                    .queryParam(configDataApiDTO.contentParam, line)
                    .post(configDataApiDTO.addLogsInTestRequests);
        }
    }
}
