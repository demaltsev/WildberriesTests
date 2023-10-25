package base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final static String START_PAGE="http://localhost:8080";
    protected String pagePath;
    protected WebDriver driver;

    public void openPage(){
        driver.get(pagePath);
    }


}
