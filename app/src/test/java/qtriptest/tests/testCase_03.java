package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.concurrent.TimeoutException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_03 {

    boolean status;
    public String lastGeneratedUsername = "";

    @BeforeTest(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton.getInstance();
    }

    @Test(dataProvider = "data_Provider",dataProviderClass = DP.class,priority = 3,description = "testCase03", groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String newUser, String password, String searchcity, String adventureName, String guestname,String date, String count, Object sr_no) throws InterruptedException, MalformedURLException, TimeoutException {

        HomePage home = new HomePage(DriverSingleton.getInstance());
        home.navigateToHomePage();
        Thread.sleep(1000);

        home.clkRegister();

        RegisterPage registration = new RegisterPage(DriverSingleton.getInstance());
        registration.navigateToRegisterPage();
        Thread.sleep(1000);

        status = registration.assertRegistrationpg();
        assertTrue(status, "unable to assert registration");

        status = registration.registerUser(newUser, password, true);

        assertTrue(status, "unable to register the user");

        lastGeneratedUsername = registration.lastGeneratedUsername;

        LoginPage login = new LoginPage(DriverSingleton.getInstance());
        login.navigateToLoginPage();
        Thread.sleep(2000);

        status = login.assertLoginpg();

        login.performLogin(lastGeneratedUsername, password);
        Thread.sleep(2000);
        status = home.isUserLoggedIn();
        home.navigateToHomePage();
        Thread.sleep(1000);
        home.searchCity(searchcity);
        Thread.sleep(2000);
        status = home.assertAutoCompleteText(searchcity);
        home.selectCity(searchcity);
        Thread.sleep(1000);
        assertTrue(status, "city not found");

        AdventurePage adventurePage = new AdventurePage(DriverSingleton.getInstance());
        AdventureDetailsPage adventureDetailsPage =
                new AdventureDetailsPage(DriverSingleton.getInstance());

        status = adventurePage.assertAdventurepg(searchcity);

        assertTrue(status, "Could not able to assert adventurepage");

        adventurePage.searchAdventure(adventureName);
        Thread.sleep(3000);
        adventureDetailsPage.bookAdventure(guestname, date, count);
        Thread.sleep(2000);
       status= adventureDetailsPage.isBookingSuccessful();
       assertTrue(status, "Could not able to reserve the adventure booking");

        HistoryPage historyPage = new HistoryPage(DriverSingleton.getInstance());
        historyPage.clkOnHistorypg();
        Thread.sleep(2000);
        historyPage.getReservations();
        Thread.sleep(5000);
        historyPage.cancelReservations();
        Thread.sleep(3000);
        historyPage.checkCancellation();
        Thread.sleep(2000);

        home.logOutUser();
        Thread.sleep(2000);

        status = home.isUserLoggedOut();
        assertTrue(status, "unable to logout");



    }
    // @AfterSuite
    // public static void teardown (){
    // DriverSingleton.close();
    // }

}
