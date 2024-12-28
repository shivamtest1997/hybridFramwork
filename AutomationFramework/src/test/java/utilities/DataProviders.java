package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //DataProvider 1
    @DataProvider(name = "loginData")
    public String[][] getData() throws IOException {
        String path= System.getProperty("user.dir") + "\\testData\\TestData.xlsx";

        ExcelUtils xlUtil=new ExcelUtils(path);
        int rowCount=xlUtil.getRowCount("Sheet1");
        int cellCount=xlUtil.getCellCount("Sheet1",1);

        //create 2D array
        String loginData[][]=new String[rowCount][cellCount];

        // read data from rows i=0 is header
        for (int i=1;i<=rowCount;i++)
        {
            for (int j=0;j<cellCount;j++)
            {
                loginData[i-1][j]=xlUtil.getCellData("Sheet1", i, j); //i-1 coz array index start from 0
            }
        }
        return loginData;
    }

}
