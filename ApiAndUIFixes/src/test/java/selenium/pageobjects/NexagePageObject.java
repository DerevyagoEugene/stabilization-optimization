package selenium.pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.*;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import selenium.customelements.Table;
import utilities.StringUtility;

import java.text.ParseException;
import java.time.Duration;
import java.util.*;

public class NexagePageObject extends Form {

    public NexagePageObject() {
        super(By.xpath("//ol[@class='breadcrumb']/li[contains(text(), 'Nexage')]"), "Nexage Page");
    }

    public List<Date> getListOfDates() throws ParseException {
        AqualityServices.getConditionalWait().waitFor(
                () -> AqualityServices.getElementFactory().getCustomElement(Table::new, By.id("allTests"), "Diagram", ElementState.EXISTS_IN_ANY_STATE).state().isDisplayed(), Duration.ofMinutes(2)
        );
        List<ITextBox> allDates = AqualityServices.getElementFactory().findElements(By.xpath("//table//td[count(//table//th[contains(text(), 'Latest test start time')]/preceding-sibling::*) +1]"), ElementType.TEXTBOX);
        List<Date> myDates = StringUtility.convertToListOfDate(allDates);

        return myDates;
    }

    public void goHome() {
        AqualityServices.getElementFactory().getLink(By.xpath("//ol[@class='breadcrumb']/li/a[contains(text(), 'Home')]"), "Home Link").click();
    }
}
