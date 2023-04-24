package qtriptest.pages;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage 
{
    WebDriver driver;

    public AdventurePage(WebDriver driver) {
        this.driver = driver;
          }

    
    public Boolean assertAdventurepg(String cityName) {
        if (driver.getCurrentUrl().endsWith(cityName.toLowerCase())) {
            return true;
        }
        return false;

    }

    public void setfilterdurationvalue(String duration) throws InterruptedException{
        WebElement durationselect = driver.findElement(By.xpath("//select[@id='duration-select']"));
        
        Select select=new Select(durationselect);
        durationselect.isSelected();
        Thread.sleep(2000);
        durationselect.click();
        Thread.sleep(2000);
        select.selectByVisibleText(duration);

    }

    public void setCategoryValue(String category) throws InterruptedException{

        WebElement catagoryToSelect = driver.findElement(By.xpath("//select[@id='category-select']"));
        Select select=new Select(catagoryToSelect);
        catagoryToSelect.isSelected();
        Thread.sleep(2000);
        catagoryToSelect.click();
        Thread.sleep(2000);
        select.selectByVisibleText(category);

    }
    public Boolean iscategoryfilterMatching(String category ,String expectedfilterResults)
    {
        List<WebElement> finalCountafterFilters=driver.findElements(By.xpath("//div[@class='category-banner']"));
        try
        {
            for(WebElement we: finalCountafterFilters)
            {
                if(Integer.parseInt(expectedfilterResults)==finalCountafterFilters.size()&& category.contains(we.getText()))
                {
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;

    }

    public void clearData() throws InterruptedException{
        WebElement clearfilter=driver.findElement(By.xpath("//div[@onclick='clearDuration(event)']"));
        clearfilter.click();
        Thread.sleep(1000);
        WebElement clearCategory=driver.findElement(By.xpath("//div[@onclick='clearCategory(event)']"));
        clearCategory.click();
    }

    public Boolean unfilteredDataMatching(String expectedUnfilterResults){
        List<WebElement> unfilteredData=driver.findElements(By.xpath("//div[@class='category-banner']"));
      try
        {
                if(Integer.parseInt(expectedUnfilterResults)==unfilteredData.size())
                {
                    return true;
                }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void searchAdventure(String adventure) throws InterruptedException{
        WebElement adventureSearch=driver.findElement(By.xpath("//input[@id='search-adventures']"));
        adventureSearch.click();
        adventureSearch.clear();
        adventureSearch.sendKeys(adventure);
        Thread.sleep(2000);

        List<WebElement> adventures=driver.findElements(By.xpath("//h5[text()='"+adventure+"']"));
        for(WebElement ad:adventures){
            if(ad.getText().equalsIgnoreCase(adventure)){
                ad.click();
                Thread.sleep(2000);
            }
        }

    }

}