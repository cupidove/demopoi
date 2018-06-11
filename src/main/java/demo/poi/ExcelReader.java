package demo.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public List<Map> read(String filePath) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        List list = new LinkedList();
        System.out.println("workbook has " + workbook.getNumberOfSheets() + " sheets : ");
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()){
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Map rowMap = new HashMap();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                rowMap.put(cell.getColumnIndex(),cell);
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
        workbook.close();
        return list;
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}
