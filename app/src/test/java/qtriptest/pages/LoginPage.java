package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";
    //public String lastGeneratedUsername = "";

    
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
        // it will take max 20 sec to load annotations if it does not load it will throw some error
    }
    

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean assertLoginpg() {
        if (driver.getCurrentUrl().endsWith("/login/")) {
            return true;
        }
        return false;


    }

    public Boolean performLogin(String username, String password) throws InterruptedException {
        
     WebElement Username=driver.findElement(By.xpath("//input[@name ='email']"));
     WebElement Password=driver.findElement(By.xpath("//input[@name ='password']"));
     WebElement login_button = driver.findElement(By.xpath("//button[text()='Login to QTrip']"));

        Username.clear();
        Username.sendKeys(username);
       // Thread.sleep(2000);
        Password.clear();
        Password.sendKeys(password);
        //Thread.sleep(2000);
        login_button.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        return assertLoginpg();
        
    }


}
