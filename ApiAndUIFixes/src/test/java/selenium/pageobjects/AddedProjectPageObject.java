package selenium.pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.time.Duration;

public class AddedProjectPageObject extends Form {
    public AddedProjectPageObject() {
        super(By.id("allTests"), "Added Project Page object");
    }

    public boolean doesTestExist(String testMethodName) {
        ITextBox rowOfTestInTable = AqualityServices.getElementFactory().getTextBox(By.xpath(String.format("//tr[td//text()[contains(., '%s')]]", testMethodName)), "Test Of My Project");
        rowOfTestInTable.state().waitForDisplayed(Duration.ofMinutes(15));

        return rowOfTestInTable.state().isDisplayed();
    }
}
