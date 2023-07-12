package utility;

import aquality.selenium.browser.AqualityServices;

import static datareadmain.DataRead.*;

public class JavaScriptUtility {
    public static void closeWindow() {
        AqualityServices.getBrowser().executeScript(scriptsDTO.getCloseWindowJavaScriptCommand());
        //workaround as we need to wait until form is closed, just takes a second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AqualityServices.getBrowser().tabs().switchToLastTab();
    }
}
