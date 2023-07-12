package selenium.customelements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.TextBox;
import org.openqa.selenium.By;

public class Table extends TextBox {
    public Table(By locator, String name, ElementState state) {
        super(locator, name, state);
    }
}
