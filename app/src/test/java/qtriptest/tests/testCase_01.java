package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeoutException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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
  
  static ExtentReports report;
  static ExtentTest test;

  @BeforeSuite(alwaysRun = true)
  public static void createDriver() throws MalformedURLException {
    
    DriverSingleton.getInstance();
    
    // report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");
    // System.out.println(System.getProperty("user.dir"));
    // report.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
     ReportSingleton rs=ReportSingleton.getObject();
     report=rs.getReports();
    
    test = report.startTest("Extent_TestCase01");
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
    test.log(LogStatus.PASS, "Test passed");

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

   @AfterSuite
 public static void teardown (){
//    DriverSingleton.close();
   report.endTest(test);
   report.flush();
}

   

}
