package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.concurrent.TimeUnit;


public class DriverSingleton {

    private static WebDriver driver=null;
    public static String browserName="chrome";
    private DriverSingleton(){
        
    }

    public static WebDriver getInstance () throws MalformedURLException{
        if(driver==null)
        {
            if(browserName.equalsIgnoreCase("chrome"))
            {
                final DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(BrowserType.CHROME);
                driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
                
            }
        }
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
        
    public static void close(){
        driver.close();
        System.out.println("driver current session is closed");
        driver=null;
    }

    public static void quit(){
        driver.quit();
        System.out.println("driver is closed");
        driver=null;
    }    
}