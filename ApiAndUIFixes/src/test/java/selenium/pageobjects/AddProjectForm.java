package selenium.pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {

    public AddProjectForm() {
        super(By.id("addProjectForm"), "Add Project Form");
    }

    public void inputProjectNameAndSubmit(String name) {
        AqualityServices.getBrowser().tabs().switchToLastTab();
        ITextBox inputNameOfProject = AqualityServices.getElementFactory().getTextBox(By.id("projectName"), "Input Name TextBox");
        inputNameOfProject.sendKeys(name);
        IButton submitButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Submit Button");
        submitButton.click();
    }

    public boolean isAlertDisplayed() {
        ITextBox successAlert = AqualityServices.getElementFactory().getTextBox(By.xpath("//div[contains(@class, 'alert') and contains(@class, 'alert-success')]"), "Success Alert");
        return successAlert.state().isDisplayed() && successAlert.state().isExist();
    }

    public boolean isFormDisplayed() {
        ITextBox inputNameOfProject = AqualityServices.getElementFactory().getTextBox(By.id("projectName"), "Input Name TextBox");
        return inputNameOfProject.state().isDisplayed();
    }
}
