package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * This class is calling the Test Cases sheet for reading the
 * test steps and the expected results
 *
 */

public class TestCaseSheetUtil {
    private static FileInputStream fs = null;
    private static Workbook workbook = null;
    private static Sheet sheet = null;
    private static String excelextensionxlsx = ".xlsx";
    private static String excelextensionxls = ".xls";
    private static String stepName = null;
    private static String moduleName = null;
    private static int expectedRow = 0;

    /**
     * <H1>Excel initialize.</H1>
     *
     * @param filePath
     * @param sheetName
     */
    public static void init(final String filePath, final String sheetName) {
        String fileExtensionName = filePath.substring(filePath.indexOf('.'));
        try {
            fs = new FileInputStream(System.getProperty("user.dir") + "/" + filePath);
            if (fileExtensionName.equals(excelextensionxlsx)) {
                // If it is xlsx file then create object of XSSFWorkbook class
                workbook = new XSSFWorkbook(fs);
            } else if (fileExtensionName.equals(excelextensionxls)) {
                // If it is xls file then create object of XSSFWorkbook class
                workbook = new HSSFWorkbook(fs);
            }
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will read the test steps from the Test Case Excel Sheet.
     *
     * @param tcNo
     * @param stepNo
     * @return test Steps
     * @throws Exception
     */
    public static String readTestCaseSteps(final String tcNo, final String stepNo) {

        int columnIndex = 3;
        int rowCount = sheet.getPhysicalNumberOfRows();
        int firstRow = 0;

        try {

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING
                            && cell.getRichStringCellValue().getString().trim().equals(tcNo)) {
                        firstRow = row.getRowNum();
                    }
                }
            }

            for (int rowIndex = firstRow; rowIndex < rowCount; rowIndex++) {

                Row row = CellUtil.getRow(rowIndex, sheet);

                if (CellUtil.getCell(row, 0).getCellType() == CellType.STRING
                        || CellUtil.getCell(row, 0).getCellType() == CellType.BLANK) {

                    Cell cell = CellUtil.getCell(row, columnIndex);
                    if (CellUtil.getCell(row, 0).getCellType() == CellType.BLANK) {
                        if (cell.getStringCellValue().split("\\.")[0].contentEquals(stepNo)) {

                            stepName = cell.getStringCellValue();
                            expectedRow = cell.getRowIndex();
                        }

                    } else if (CellUtil.getCell(row, 0).getStringCellValue().equals(tcNo)) {

                        if (cell.getStringCellValue().split("\\.")[0].contentEquals(stepNo)) {

                            stepName = cell.getStringCellValue();
                            expectedRow = cell.getRowIndex();
                        }

                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            return stepName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method will return the Expected Results from the excel sheet.
     *
     * @return expected Result
     * @throws Exception
     */
    public static String readExpectedResults() {
        int columnIndex = 5;
        Row row = CellUtil.getRow(expectedRow, sheet);
        Cell cell = CellUtil.getCell(row, columnIndex);
        return cell.getStringCellValue();
    }

    /**
     * This method will return the Module Name from the Test Case Sheet.
     *
     * @param tcNo
     * @return Module name
     */
    public static String readModuleName(final String tcNo) {
        int columnIndex = 7;
        int rowCount = sheet.getPhysicalNumberOfRows();
        int firstRow = 0;

        try {

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING
                            && cell.getRichStringCellValue().getString().trim().equals(tcNo)) {
                        firstRow = row.getRowNum();
                    }
                }
            }

            for (int rowIndex = firstRow; rowIndex < rowCount; rowIndex++) {

                Row row = CellUtil.getRow(rowIndex, sheet);

                if (CellUtil.getCell(row, 0).getCellType() == CellType.STRING
                        || CellUtil.getCell(row, 0).getCellType() == CellType.BLANK) {

                    Cell cell = CellUtil.getCell(row, columnIndex);
                    if (CellUtil.getCell(row, 0).getCellType() != CellType.BLANK) {

                        moduleName = cell.getStringCellValue();
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            return moduleName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * This method will run json file path.
     *
     * @param filePath
     * @return readJsonFromFile
     * @throws IOException
     */
    public static JsonNode readJsonFromFile(String filePath) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonData);
    }
}

