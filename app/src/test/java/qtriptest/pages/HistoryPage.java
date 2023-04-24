
package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HistoryPage {

    WebDriver driver;
    private String transactionId;

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
          }

    
    public Boolean asserthistorypg(String cityName) {
        if (driver.getCurrentUrl().endsWith(cityName.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void clkOnHistorypg() throws InterruptedException{
        WebElement goToHistorypg=driver.findElement(By.xpath("//a[@href='../reservations/']//child::strong"));
        goToHistorypg.click();
        Thread.sleep(3000);
    }

    public String getReservations() throws InterruptedException{

        String transactionId="";
        List <WebElement> transactionIdRows=driver.findElements(By.xpath("//tbody//tr//th[@scope='row']"));
        for(WebElement row:transactionIdRows){
            if(row.isDisplayed())
            {
                transactionId=row.getText();
                this.transactionId =transactionId;
                return this.transactionId;
            }
        }
        return transactionId;
        
    }

    public void cancelReservations() throws InterruptedException{

        WebElement cancelButton=driver.findElement(By.xpath("//button[@class='cancel-button']"));
        cancelButton.click();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public Boolean checkCancellation(){

        WebElement noResrvationBanner=driver.findElement(By.xpath("//div[@id='no-reservation-banner']"));
       List <WebElement> transactionIds=driver.findElements(By.xpath("//tbody//tr//th[@scope='row']"));
        if((transactionIds.size()==0) && (noResrvationBanner.isDisplayed())){
            return true;
        }
        return false;
        
    }
}