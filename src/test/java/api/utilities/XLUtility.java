package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fi;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    public XLUtility(String path){
        this.path=path;
    }
    public int getRowCount(String sheetName) throws IOException {
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
       fi = new FileInputStream(path);
       workbook = new XSSFWorkbook(fi);
       sheet=workbook.getSheet(sheetName);
       row=sheet.getRow(rownum);
       int cellCount=row.getLastCellNum();
       workbook.close();
       fi.close();
       return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data = "";
        try{
            data = formatter.formatCellValue(cell);
        }
        catch (Exception e){
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }
}