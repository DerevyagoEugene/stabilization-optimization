package tests.steps;

import api.models.TestDTO;
import api.requests.*;
import aquality.selenium.browser.AqualityServices;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import selenium.pageobjects.*;
import utilities.*;
import utility.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static api.specifications.Specifications.requestSpecForTests;
import static api.specifications.Specifications.requestSpecForToken;
import static dataread.DataRead.*;
import static io.restassured.RestAssured.given;

public class StepsOfVariant2 {

    public String getTokenByVariant(String variant) {
        return given()
                .spec(requestSpecForToken)
                .queryParam(configDataApiDTO.variantParam, variant)
                .post(configDataApiDTO.getTokenInTokenRequests)
                .asString();
    }

    public String passAuthSetTokenToCookieAndReturnTheVariant(String token) throws MalformedURLException {
        new LoginForm().authentificate();
        StringUtility.setTokenToCookie(token);
        AqualityServices.getBrowser().refresh();

        return new ProjectsPageObject().getVariant();
    }

    public String getNexageProjectId() {
        return new ProjectsPageObject().getIdOfNexageProject();
    }

    public void goToNexageProject() {
        new ProjectsPageObject().goToNexageProject();
    }

    private List<Date> getDatesFromApi(String idOfProject) throws ParseException {
        List<TestDTO> listOfTestsFromAPI = TestsRequest.getTestsById(idOfProject);

        return StringUtility.getStartedDates(listOfTestsFromAPI);
    }

    public String generateAlphabeticalNameOfProject() {
        return RandomStringUtils.randomAlphabetic(RandomUtility.randomIntegerFrom5To15());
    }

    public String generateAlphaNumericalNameOfProject(){
        return RandomStringUtils.randomAlphanumeric(RandomUtility.randomIntegerFrom5To15());
    }

    public String generateNumericalNameOfProject(){
        return RandomStringUtils.randomNumeric(RandomUtility.randomIntegerFrom5To15());
    }

    public String generateAlphaNumericalWithSpecialSymbolsNameOfProject(){
        return RandomStringUtils.randomAscii(RandomUtility.randomIntegerFrom5To15());
    }

    public void createNewProject(String nameOfProject) {
        new NexagePageObject().goHome();
        new ProjectsPageObject().clickToAddProject();
        new AddProjectForm().inputProjectNameAndSubmit(nameOfProject);
    }

    public void closeWindow() {
        JavaScriptUtility.closeWindow();
    }

    public String addProjectWithLogsAndScreenshot(String nameOfProject) throws IOException {
        new ProjectsPageObject().goToProjectWithName(nameOfProject);

        String nameOfClass = getClass().getName();
        String nameOfMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        String idOfTest = TestsRequest.addTest(nameOfProject, nameOfClass, nameOfMethod);

        TestsRequest.addLogsToTest(idOfTest);
        org.apache.commons.codec.binary.Base64 binaryBase64 = new Base64();
        String encodedString = new String(binaryBase64.encode(AqualityServices.getBrowser().getScreenshot()));

        given()
                .spec(requestSpecForTests)
                .contentType("application/x-www-form-urlencoded")
                .formParam(configDataApiDTO.testIdParam, idOfTest)
                .formParam(configDataApiDTO.contentParam, encodedString)
                .formParam(configDataApiDTO.contentTypeParam, configDataApiDTO.contentTypeOfImage)
                .post(configDataApiDTO.addAttachmentsInTestRequests);

        return nameOfMethod;
    }

    public void assertTokenIsGenerated(String token) {
        Assert.assertNotEquals(token.length(), 0);
    }

    public void assertThatVariantIsCorrectAndProjectPageDisplayed(String variant) {
        //Workaround, as we need to wait until page is updated
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(new ProjectsPageObject().state().isDisplayed());
        Assert.assertTrue(isVariantTheSame(variant));
    }

    public void assertThatTestsOrderedByDescendingAndSameWithApiTests(String idOfNexageProject) throws ParseException {
        List<Date> datesFromAPI = getDatesFromApi(idOfNexageProject);
        List<Date> myDatesUI = new NexagePageObject().getListOfDates();
        List<Date> sortedDescDatesList = myDatesUI;
        for (int i = 0; i < sortedDescDatesList.size(); i++){
            for (int j = sortedDescDatesList.size() - 1; j > i; j--) {
                if (sortedDescDatesList.get(i).compareTo(sortedDescDatesList.get(j))>0){
                    Date temp = sortedDescDatesList.get(i);
                    sortedDescDatesList.set(i, sortedDescDatesList.get(j));
                    sortedDescDatesList.set(j, temp);
                }
            }
        }

        Assert.assertEquals(myDatesUI, sortedDescDatesList);
        Assert.assertTrue(datesFromAPI.containsAll(myDatesUI));
    }

    public void assertThatSuccessAlertDisplayed() {
        Assert.assertTrue(new AddProjectForm().isAlertDisplayed());
    }

    public void assertThatFormIsClosedAndProjectAdded(String nameOfProject) {
        Assert.assertFalse(new AddProjectForm().isFormDisplayed());
        AqualityServices.getBrowser().refresh();
        Assert.assertTrue(new ProjectsPageObject().isProjectWithNameDisplayed(nameOfProject));
    }

    public void assertThatTestIsAdded(String nameOfMethod) {
        Assert.assertTrue(new AddedProjectPageObject().doesTestExist(nameOfMethod));
    }

    private boolean isVariantTheSame(String variant) {
        boolean flag = true;
        String variantExpected = testDataApiDTO.variantToTakeToken;
        if (variant.length() != variantExpected.length())
            flag = false;
        else {
            for (int i = 0; i < variant.length(); i++) {
                if (variant.charAt(i) != variantExpected.charAt(i))
                    flag = false;
            }
        }
        return flag;
    }
}
