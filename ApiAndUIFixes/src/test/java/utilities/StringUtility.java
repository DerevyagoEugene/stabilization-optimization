package utilities;

import api.models.TestDTO;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.Cookie;

import java.text.*;
import java.util.*;

import static dataread.DataRead.*;

public class StringUtility {
    private static SimpleDateFormat formatter = new SimpleDateFormat(configDataApiDTO.simpleDateFormat);

    public static String getLastElementInString(String input) {
        return input.substring(input.lastIndexOf(" ") + 1);
    }

    public static List<Date> getStartedDates(List<TestDTO> tests) throws ParseException {
        LinkedList<Date> myDates = new LinkedList<>();

        for (int i = 0; i < tests.size(); i++) {
            TestDTO test = tests.get(i);
            String myDate = test.startTime.substring(0, test.startTime.length() - 2);
            myDates.add(formatter.parse(myDate));
        }
        return myDates;
    }

    public static void setTokenToCookie(String token) {
        Cookie cookie = new Cookie(configDataApiDTO.tokenForCoockieParam, token);
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }

    public static List<Date> convertToListOfDate(List<ITextBox> textBoxes) throws ParseException {
        LinkedList<Date> myDates = new LinkedList<>();
        final SimpleDateFormat formatter = new SimpleDateFormat(configDataApiDTO.simpleDateFormat);

        for (int i = 0; i < textBoxes.size(); i++){
            ITextBox date = textBoxes.get(i);
            String myDate = date.getText().substring(0, date.getText().length() - 2);
            myDates.add(formatter.parse(myDate));
        }

        return myDates;
    }
}
