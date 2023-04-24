package qtriptest;

import qtriptest.pages.Utility;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class DP {
 
@DataProvider(name="data_Provider")    
public Object[][] getData(Method m) throws IOException{
    
    String path="src/test/resources/DatasetsforQTrip.xlsx";
    Utility util=new Utility(path);
    
    int totalrows=util.getRowCount(m.getName());
    int totalcols=util.getCellCount(m.getName(), 1);
    //strore the data in 2 dim array
    String excelData [][]= new String[totalrows][totalcols];


    for(int i=1;i<=totalrows;i++){
        for(int j=1;j<=totalcols;j++){
            excelData[i-1][j-1]= util.getcellData(m.getName(), i, j);
        }
    }

    return excelData;

}

}
