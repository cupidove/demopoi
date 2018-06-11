package demo.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String excelPath = "demo1.xlsx";
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ExcelReader excelReader = new ExcelReader();
        try {
            String path = App.class.getResource("/").getPath();
            path = path.substring(1,path.length()-1) + "/" + excelPath;
            System.out.println(path);
            List<Map> list = excelReader.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
