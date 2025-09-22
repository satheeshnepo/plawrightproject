package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class DataReader {
    public static  Properties prop;
    public void loadPropertyFile() throws Exception{
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\satheess\\Downloads\\growAiFramework\\udemyPlaywrightJavaTest\\src\\test\\java\\testresources\\property.properties"));
        prop = new Properties();
        prop.load(fis);
    }
   public Object[][] getDataFromXl() throws  Exception{
        HashMap<String,String> value = new HashMap<>();

       String path = (String)prop.get("xlfilepath");
       System.out.println("Path is " + path);
       File file = new File(path);
       String sheetName = (String)prop.get("sheetName");
       ExcelReader excelReader = new ExcelReader(file,sheetName);
       int row = excelReader.getNumberOfRows();
       int colmn = excelReader.getNumberOfCols();
       System.out.println("Total rows are available are "+ row + colmn);
       Object[][] data = new Object[row][colmn];
//       System.out.println(excelReader.getData(2,1));
       for(int i =1; i < row; i++) {
           for(int j= 1; j <= colmn ;j++) {
               System.out.println(excelReader.getData(1,j) + " ----" + excelReader.getData(i+1,j));
               value.put(excelReader.getData(1,j),excelReader.getData(i+1,j));
               data[i-1][j-1] = value;
           }
       }
       System.out.println(data[0][0]);
       return data;
   }
}
class Main {
    public static void main(String[] args) throws Exception {
        DataReader dataRe = new DataReader();
        dataRe.loadPropertyFile();
        dataRe.getDataFromXl();
    }
}
