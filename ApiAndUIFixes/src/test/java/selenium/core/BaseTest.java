package selenium.core;

import aquality.selenium.browser.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest extends Browser{
    protected Browser browser = AqualityServices.getBrowser();

    public BaseTest(RemoteWebDriver remoteWebDriver) {
        super(remoteWebDriver);
    }
}
