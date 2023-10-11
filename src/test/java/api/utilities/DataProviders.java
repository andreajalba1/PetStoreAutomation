package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {


    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int rowNum = xlUtility.getRowCount("Sheet1");
        int colCount = xlUtility.getCellCount("Sheet1", 1);

        Object[][] apiData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                apiData[i - 1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }


    @DataProvider(name = "UserNames")
    public Iterator<Object[]> getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int rowNum = xlUtility.getRowCount("Sheet1");

        List<Object[]> data = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            String[] rowData = {xlUtility.getCellData("Sheet1", i, 1)};
            data.add(rowData);
        }
        return data.iterator();
    }
}




