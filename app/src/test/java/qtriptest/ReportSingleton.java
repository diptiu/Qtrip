package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {

    static ExtentReports report;
    private static ReportSingleton reportSingleton=null;
    static String timestamp=String.valueOf(java.time.LocalDateTime.now());

    private ReportSingleton(){
        report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");
        report.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));

    }
    public static ReportSingleton getObject(){
        if(reportSingleton==null){
            reportSingleton=new ReportSingleton();
        }
        return reportSingleton;
    }
    public ExtentReports getReports(){
        return report;
    }
}

//static ExtentTest test;
    //static ExtentReports report;
    // report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");
    // // System.out.println(System.getProperty("user.dir"));
 
    //  System.out.println(System.getProperty("user.dir"));
    //    report.loadConfig(new File(System.getProperty("user.dir")+"/extent_Report.xml"));
    //      test = report.startTest("ExtentDemo");
    //test.log(LogStatus.FAIL, "Test Failed");
    // report.endTest(test);
    //     report.flush();