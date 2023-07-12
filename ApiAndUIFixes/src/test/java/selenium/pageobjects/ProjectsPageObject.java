package selenium.pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utilities.StringUtility;

public class ProjectsPageObject extends Form {

    public ProjectsPageObject() {
        super(By.xpath("//div[@class='list-group']/a[contains(@href, 'projectId')]/parent::div"), "Projects Page");
    }

    public String getVariant() {
        String textOfVariantElement = AqualityServices.getElementFactory().getTextBox(By.xpath("//p/span"), "Variant In Footer").getText();
        String variant = StringUtility.getLastElementInString(textOfVariantElement);

        return variant;
    }

    public void goToNexageProject() {
        AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='list-group']/a[contains(text(), 'Nexage')]"), "Nexage Project Button").click();
    }

    public void goToProjectWithName(String name) {
        getProject(name).click();
    }

    public void clickToAddProject() {
        AqualityServices.getElementFactory().getButton(By.xpath("//a[contains(@href, 'addProject')]"), "Add Project Button").click();
    }

    public String getIdOfNexageProject() {
        String hrefOfProject = AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='list-group']/a[contains(text(), 'Nexage')]"), "Nexage Project Button").getAttribute("href");
        String projectId = hrefOfProject.substring(hrefOfProject.length() -1);

        return projectId;
    }

    public ITextBox getProject(String name) {
        return AqualityServices.getElementFactory().getTextBox(By.xpath(String.format("//div[@class='list-group']/a[contains(text(), '%s')]", name)), String.format("Project With Name %s", name));
    }

    public boolean isProjectWithNameDisplayed(String name) {
        return getProject(name).state().isDisplayed();
    }
}
