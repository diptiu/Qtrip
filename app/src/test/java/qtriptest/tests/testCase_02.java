package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.util.concurrent.TimeoutException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_02 {

  static ExtentReports report;
  static ExtentTest test;
  
  boolean status;
    @BeforeSuite(alwaysRun = true)
  public static void createDriver() throws MalformedURLException {
    DriverSingleton.getInstance();
    ReportSingleton rs=ReportSingleton.getObject();
    report=rs.getReports();
   
   test = report.startTest("Extent_TestCase02");
 }

 @Test
 @Parameters({"invalidCity"})
 public void verifyNoCityDisplayed(String invalidCity) throws MalformedURLException, InterruptedException{
 
   
    HomePage home = new HomePage(DriverSingleton.getInstance());
    home.navigateToHomePage();
    Thread.sleep(1000);
    home.searchCity(invalidCity);
    status=home.noCityFound();
    assertTrue(status, "Failed to display the no city found message");
 }

   @Test(dataProvider = "data_Provider",dataProviderClass = DP.class,priority = 2,description = "testCase02",groups = {"Search and Filter flow"})
   public void TestCase02(String cityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults, Object sr)
      throws InterruptedException, MalformedURLException, TimeoutException {
    
      
    HomePage home = new HomePage(DriverSingleton.getInstance());
    home.navigateToHomePage();
    Thread.sleep(1000);
    home.searchCity(cityName);
    Thread.sleep(2000);
    status=  home.assertAutoCompleteText(cityName);

    home.selectCity(cityName);
    Thread.sleep(1000);
    assertTrue(status,"city not found");
 
    AdventurePage adventurePage=new AdventurePage(DriverSingleton.getInstance());

   status= adventurePage.assertAdventurepg(cityName);

   assertTrue(status, "Could not able to assert adventurepage");

   adventurePage.setfilterdurationvalue(DurationFilter);
   

   Thread.sleep(1000);
   adventurePage.setCategoryValue(Category_Filter);

   Thread.sleep(1000);
   status=adventurePage.iscategoryfilterMatching(Category_Filter, ExpectedFilteredResults);

   assertTrue(status, "Could not able to get filteredData rightly"); 

   adventurePage.clearData();
   Thread.sleep(2000);

   status= adventurePage.unfilteredDataMatching(ExpectedUnFilteredResults);
   assertTrue(status, "Could not able to get unfilteredData rightly"); 

}

@AfterSuite
public static void teardown (){
//    DriverSingleton.close();
  report.endTest(test);
  report.flush();
}
}