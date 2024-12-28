package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    //create variable at once and use wherever required
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook workbook;
    public  static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;
    String path;

    //constructor
    public ExcelUtils(String path)
    {
        this.path=path;
    }

    //this method will return row count
    public int getRowCount(String xlsheet) throws IOException {

        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        System.out.println(sheet);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }

    //this method will return cell count
    public int getCellCount(String xlsheet,int rowNo) throws IOException {

        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        int cellCount = sheet.getRow(rowNo).getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }

    //this method will read data from cell
    public String getCellData(String xlsheet,int rowNo,int colNo) throws IOException {
        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        row=sheet.getRow(rowNo);
        cell=row.getCell(colNo);
        String data ;
        // Note : if data not present in cell it may throw exception DataNotFoundException to handle this use tryCatch
        try {
           // data=cell.toString();
           // or
            DataFormatter formatter=new DataFormatter();
            data=formatter.formatCellValue(cell);// Return the formatted cell value as String regardless of cell type.
        }catch (Exception e)
        {
            data=""; // make data value empty
        }
        workbook.close();
        fis.close();
        return  data;
    }

    //this method will write data in cell
    public void setCellData(String xlsheet,int rowNo,int cellNo,String data) throws IOException {
        //read data

        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        row=sheet.getRow(rowNo);

        //write data in same sheet
        cell=row.createCell(cellNo);
        cell.setCellValue(data);
        fos=new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

    /**
     * This method will fill green colour
     * @param xlfile
     * @param xlsheet
     * @param rowNo
     * @param cellNo
     * @throws IOException
     */
    public void fillGreenColour(String xlsheet,int rowNo,int cellNo) throws IOException {

        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        row=sheet.getRow(rowNo);
        cell=row.getCell(cellNo);

        style=workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos=new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }
    /**
     * This method will fill red  colour
     * @param xlfile
     * @param xlsheet
     * @param rowNo
     * @param cellNo
     * @throws IOException
     */
    public void fillRedColour(String xlsheet,int rowNo,int cellNo) throws IOException {

        fis=new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(xlsheet);
        row=sheet.getRow(rowNo);
        cell=row.getCell(cellNo);

        style=workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos=new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }


}
