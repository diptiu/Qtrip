package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_01 {

  //static RemoteWebDriver driver;
  public String lastGeneratedUsername= "";

  @BeforeTest(alwaysRun = true)
  public static void createDriver() throws MalformedURLException {
    // Launch Browser using Zalenium
    // final DesiredCapabilities capabilities = new DesiredCapabilities();
    // capabilities.setBrowserName(BrowserType.CHROME);
    // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
    // System.out.println("createDriver()");
    // driver.manage().window().maximize();
    DriverSingleton.getInstance();
 }

 

   @Test(dataProvider = "data_Provider",dataProviderClass = DP.class,priority = 1,description = "testCase01",groups = {"Login Flow"})
   public void TestCase01(String UserName, String Password, Object SI )
      throws InterruptedException, MalformedURLException, TimeoutException {
    boolean status;

    HomePage home = new HomePage(DriverSingleton.getInstance());
    home.navigateToHomePage();
    Thread.sleep(1000);

    home.clkRegister();

    RegisterPage registration = new RegisterPage(DriverSingleton.getInstance());
    registration.navigateToRegisterPage();
    Thread.sleep(1000);

    status = registration.assertRegistrationpg();
    assertTrue(status, "unable to assert registration");

   status= registration.registerUser(UserName, Password, true);
    
    assertTrue(status,"unable to register the user");

    lastGeneratedUsername = registration.lastGeneratedUsername;

    LoginPage login = new LoginPage(DriverSingleton.getInstance());
    login.navigateToLoginPage();
    Thread.sleep(2000);

    status = login.assertLoginpg();

    login.performLogin(lastGeneratedUsername, Password);
    Thread.sleep(2000);

    status = home.isUserLoggedIn();

    home.logOutUser();
    Thread.sleep(1000);

    status = home.isUserLoggedOut();
    assertTrue(status, "unable to logout");
    }

//     @AfterTest
//   public static void teardown (){
//   DriverSingleton.close();
//  }
   

}
