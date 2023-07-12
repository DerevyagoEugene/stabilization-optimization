package tests;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import selenium.core.BaseTest;
import tests.steps.StepsOfVariant2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import static dataread.DataRead.*;

public class VariantTwo extends BaseTest {

    public VariantTwo(RemoteWebDriver remoteWebDriver) {
        super(remoteWebDriver);
    }

    @Test
    public void AlphabeticalProjectName() throws ParseException, IOException {
        browser.maximize();
        browser.goTo((configDataApiDTO.baseUri + configDataUiDTO.webAppPath));
        browser.waitForPageToLoad();

        final StepsOfVariant2 steps = new StepsOfVariant2();

        String token = new StepsOfVariant2().getTokenByVariant(testDataApiDTO.variantToTakeToken);
        steps.assertTokenIsGenerated(token);

        String variant = steps.passAuthSetTokenToCookieAndReturnTheVariant(token);
        steps.assertThatVariantIsCorrectAndProjectPageDisplayed(variant);

        String idOfNexageProject = steps.getNexageProjectId();
        steps.goToNexageProject();
        steps.assertThatTestsOrderedByDescendingAndSameWithApiTests(idOfNexageProject);

        String nameOfProject = steps.generateAlphabeticalNameOfProject();
        steps.createNewProject(nameOfProject);
        steps.assertThatSuccessAlertDisplayed();

        steps.closeWindow();
        steps.assertThatFormIsClosedAndProjectAdded(nameOfProject);

        String nameOfMethod = steps.addProjectWithLogsAndScreenshot(nameOfProject);
        steps.assertThatTestIsAdded(nameOfMethod);

        AqualityServices.getBrowser().quit();
    }

    @Test
    public void NumericalProjectName() throws ParseException, IOException {
        browser.maximize();
        browser.goTo((configDataApiDTO.baseUri + configDataUiDTO.webAppPath));
        browser.waitForPageToLoad();

        final StepsOfVariant2 steps = new StepsOfVariant2();

        String token = steps.getTokenByVariant(testDataApiDTO.variantToTakeToken);
        steps.assertTokenIsGenerated(token);

        String variant = steps.passAuthSetTokenToCookieAndReturnTheVariant(token);
        steps.assertThatVariantIsCorrectAndProjectPageDisplayed(variant);

        String idOfNexageProject = steps.getNexageProjectId();
        steps.goToNexageProject();
        steps.assertThatTestsOrderedByDescendingAndSameWithApiTests(idOfNexageProject);

        String nameOfProject = steps.generateNumericalNameOfProject();
        steps.createNewProject(nameOfProject);
        steps.assertThatSuccessAlertDisplayed();

        steps.closeWindow();
        steps.assertThatFormIsClosedAndProjectAdded(nameOfProject);

        String nameOfMethod = steps.addProjectWithLogsAndScreenshot(nameOfProject);
        steps.assertThatTestIsAdded(nameOfMethod);

        AqualityServices.getBrowser().quit();
    }

    @Test
    public void AlphaNumericalProjectName() throws ParseException, IOException {
        browser.maximize();
        browser.goTo((configDataApiDTO.baseUri + configDataUiDTO.webAppPath));
        browser.waitForPageToLoad();

        final StepsOfVariant2 steps = new StepsOfVariant2();

        String token = steps.getTokenByVariant(testDataApiDTO.variantToTakeToken);
        steps.assertTokenIsGenerated(token);

        String variant = steps.passAuthSetTokenToCookieAndReturnTheVariant(token);
        steps.assertThatVariantIsCorrectAndProjectPageDisplayed(variant);

        String idOfNexageProject = steps.getNexageProjectId();
        steps.goToNexageProject();
        steps.assertThatTestsOrderedByDescendingAndSameWithApiTests(idOfNexageProject);

        String nameOfProject = steps.generateAlphaNumericalNameOfProject();
        steps.createNewProject(nameOfProject);
        steps.assertThatSuccessAlertDisplayed();

        steps.closeWindow();
        steps.assertThatFormIsClosedAndProjectAdded(nameOfProject);

        String nameOfMethod = steps.addProjectWithLogsAndScreenshot(nameOfProject);
        steps.assertThatTestIsAdded(nameOfMethod);

        AqualityServices.getBrowser().quit();
    }

    @Test
    public void AlphaNumericalProjectNameSpecialSymbols() throws ParseException, IOException {
        browser.maximize();
        browser.goTo((configDataApiDTO.baseUri + configDataUiDTO.webAppPath));
        browser.waitForPageToLoad();

        final StepsOfVariant2 steps = new StepsOfVariant2();

        String token = steps.getTokenByVariant(testDataApiDTO.variantToTakeToken);
        steps.assertTokenIsGenerated(token);

        String variant = steps.passAuthSetTokenToCookieAndReturnTheVariant(token);
        steps.assertThatVariantIsCorrectAndProjectPageDisplayed(variant);

        String idOfNexageProject = steps.getNexageProjectId();
        steps.goToNexageProject();
        steps.assertThatTestsOrderedByDescendingAndSameWithApiTests(idOfNexageProject);

        String nameOfProject = steps.generateAlphaNumericalWithSpecialSymbolsNameOfProject();
        steps.createNewProject(nameOfProject);
        steps.assertThatSuccessAlertDisplayed();

        steps.closeWindow();
        steps.assertThatFormIsClosedAndProjectAdded(nameOfProject);

        String nameOfMethod = steps.addProjectWithLogsAndScreenshot(nameOfProject);
        steps.assertThatTestIsAdded(nameOfMethod);

        AqualityServices.getBrowser().quit();
    }
}
