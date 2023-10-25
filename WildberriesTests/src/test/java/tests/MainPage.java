package tests;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@data-wba-header-name='Main']")
    private WebElement mainButton;



    public MainPage (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public MainPage openMainPage() {
        driver.get("https://www.wildberries.ru/");
        return this;
    }

    public void clickMainButton() {
        mainButton.click();
    }



}
