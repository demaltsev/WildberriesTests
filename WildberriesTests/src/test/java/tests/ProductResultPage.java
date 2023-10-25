package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductResultPage extends MainPage {


    public ProductResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


}
