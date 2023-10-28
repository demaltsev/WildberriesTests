package base;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected String pagePath;
    protected WebDriver driver;

    public void openPage(){
        driver.get(pagePath);
    }


}
