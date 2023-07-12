package selenium.pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

import static dataread.DataRead.*;

public class LoginForm extends Form {
    public LoginForm() {
        super(By.xpath("//body"), "Login Page With Alert");
    }

    public void authentificate() throws MalformedURLException {
        URL myUrl = new URL(configDataApiDTO.baseUri + configDataUiDTO.webAppPath);
        String protocol = myUrl.getProtocol();
        String loginAndPassword = configDataUiDTO.userLogin + ":" + configDataUiDTO.userPassword;
        String domain = myUrl.getHost();
        String webApp = StringUtils.substringAfter(myUrl.toString(), domain);
        AqualityServices.getBrowser().goTo(protocol + "://" + loginAndPassword + "@" + domain + webApp);
    }
}
