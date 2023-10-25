package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends MainPage {

    @FindBy(id="searchInput")
    private WebElement fillProductIdInput;




//что за PageFactory? возможно открывает страницу
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
//ввод id товара
    public ProductPage fillProductId (String x) {
        fillProductIdInput.sendKeys(x);
        return this;
    }

    //нажатие клавиши ENTER
    public ProductResultPage findProductById() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        return new ProductResultPage(driver);
    }


}
