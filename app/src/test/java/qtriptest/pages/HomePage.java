package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";


    public HomePage(WebDriver driver) {
        this.driver = driver;
        // PageFactory.initElements(driver,this);
        // it will take max 20 sec to load annotations if it does not load it will throw some error
    }

    public void navigateToHomePage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    public Boolean isUserLoggedIn() {

        List<WebElement> listofelements = new ArrayList<WebElement>();
        listofelements = driver.findElements(By.xpath("//a[text()='Login Here']"));
        if (listofelements.size() == 0) {
            return true;
        }
        return false;
    }

    public Boolean logOutUser() throws InterruptedException {
 try{
        WebElement logout_button = driver.findElement(By.xpath("//div[@class='nav-link login register']"));
        logout_button.click();
        Thread.sleep(3000);
        return true;
   }
      catch (Exception e) {
    return false;
      }

 }

    public Boolean isUserLoggedOut() {

        List<WebElement> listofelements = new ArrayList<WebElement>();
        listofelements = driver.findElements(By.xpath("//div[text()='Logout']"));
        if (listofelements.size() == 0) {
            return true;
        }
        return false;
    }

    public Boolean clkRegister() throws InterruptedException{
        try{
        WebElement Register_button = driver.findElement(By.xpath("//a[text()='Register']"));
        Register_button.click();
        Thread.sleep(3000);
        return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void searchCity(String cityName) throws InterruptedException{
        List<WebElement> listOfCities =driver.findElements(By.xpath("//ul[@id='results']"));
        WebElement enterCity=driver.findElement(By.xpath("//input[@placeholder='Search a City '] "));
        String cityNotFound="No City found";

        if(cityName.length()>3){
            cityName=cityName.substring(0,4);
            Thread.sleep(2000);
            enterCity.clear();
            enterCity.sendKeys(cityName);
        }
        else{
            Thread.sleep(2000);
            enterCity.clear();
            enterCity.sendKeys(cityName);
        }
        Thread.sleep(2000);
        for(WebElement cty:listOfCities){
            if(cty.getText().equalsIgnoreCase(cityName)){
            }
           
        }
    }

    public Boolean assertAutoCompleteText(String cityName){
    List<WebElement> listOfCities =driver.findElements(By.xpath("//ul[@id='results']"));
   // WebElement enterCity=driver.findElement(By.xpath("//input[@placeholder='Search a City '] "));
    for(WebElement cty:listOfCities){
        cty.isDisplayed();
        return true;
    }

        return false;
        
    }

    public void selectCity(String cityName){
        List<WebElement> listOfCities =driver.findElements(By.xpath("//ul[@id='results']"));
        
                for(WebElement cty:listOfCities){
            if(cty.getText().equalsIgnoreCase(cityName)){
                cty.click();
            }
            
        }
    }

    public Boolean noCityFound(){
    WebElement noCityFound=driver.findElement(By.xpath("//h5[text()='No City found']"));
    noCityFound.isDisplayed();
        return true;
        
    }

}
