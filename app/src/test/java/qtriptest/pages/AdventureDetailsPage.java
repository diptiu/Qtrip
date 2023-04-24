
package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdventureDetailsPage {

    WebDriver driver;

    public AdventureDetailsPage(WebDriver driver) {
        this.driver = driver;
          }

    
    public Boolean assertAdventurdetailsepg(String cityName) {
        if (driver.getCurrentUrl().endsWith(cityName.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void bookAdventure(String name , String date, String count) throws InterruptedException{

        WebElement nAme=driver.findElement(By.xpath("//input[@name='name']"));
        WebElement dAte=driver.findElement(By.xpath("//input[@name='date']"));
        WebElement cOunt=driver.findElement(By.xpath("//input[@name='person']"));
        WebElement rEserveBooking=driver.findElement(By.xpath("//button[text()='Reserve']"));
       

        nAme.clear();
        nAme.sendKeys(name);
        Thread.sleep(2000);
        dAte.clear();
        dAte.sendKeys(date);
        Thread.sleep(2000);
        cOunt.clear();
        cOunt.sendKeys(count);
        Thread.sleep(2000);
        rEserveBooking.click();
        Thread.sleep(2000);
        
    }

    public Boolean isBookingSuccessful(){
        WebElement verifyBookingMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        if(verifyBookingMsg.isDisplayed()){
            return true;
        }
        else
        return false;  
    }
}