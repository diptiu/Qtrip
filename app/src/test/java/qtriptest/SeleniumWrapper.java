package qtriptest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    WebDriver driver;


    public SeleniumWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public static Boolean click(WebDriver driver, WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Boolean sendkeys(WebElement element, String str) {
        try {
            element.clear();
            Thread.sleep(1000);
            element.sendKeys(str);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public static Boolean navigateToUrl(WebDriver driver, String url){
        
        try {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
            }
            driver.navigate().to(url);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static WebElement findElementByRetry(WebDriver driver, By by, int Count){
        return driver.findElement(by);

    }


}


