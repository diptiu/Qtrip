package qtriptest.pages;

import qtriptest.DriverSingleton;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedUsername = "";


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
       PageFactory.initElements(driver, this);
        // it will take max 20 sec to load annotations if it does not load it will throw some error
    }
    
    public void navigateToRegisterPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    public boolean registerUser(String username, String password,Boolean generateRandomUsername) throws InterruptedException, TimeoutException {

        WebElement Username=driver.findElement(By.xpath("//input[@id ='floatingInput']"));
        WebElement Password=driver.findElement(By.xpath("//input[@name ='password']"));
        WebElement Confirm_Password=driver.findElement(By.xpath("//input[@name ='confirmpassword']"));


        String test_data_username;
        String test_data_password;

        if (generateRandomUsername==true) {

            test_data_username = username + UUID.randomUUID().toString();
        } else {
            test_data_username = username;
        }
        test_data_password = password;
        Username.clear();
        Username.sendKeys(test_data_username);
        //Thread.sleep(2000);
        Password.clear();
        Password.sendKeys(test_data_password);
       // Thread.sleep(2000);
        Confirm_Password.clear();
        Confirm_Password.sendKeys(test_data_password);
        Thread.sleep(2000);

        WebElement Register_button = driver.findElement(By.xpath("//button[text()='Register Now']"));
         Register_button.click();
        Thread.sleep(3000);
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.or(
        //     ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login")));
    
        this.lastGeneratedUsername = test_data_username;
        return this.driver.getCurrentUrl().endsWith("/login");


    }
    public Boolean assertRegistrationpg() {
        if (driver.getCurrentUrl().endsWith("/register/")) {
            return true;
        }
        return false;

    }

}
