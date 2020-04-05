package cn.tjy.laminated.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class getAllExcelData {
    public static void main(String[] args){
      try {
          showExcel();
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public static void showExcel() throws Exception{
        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File("../static/feedstock.xls")));
        HSSFSheet sheet=null;
        for (int i = 0; i <workbook.getNumberOfSheets() ; i++) {
            sheet=workbook.getSheetAt(i);
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                HSSFRow row=sheet.getRow(j);
                for (int k = 0; k <row.getPhysicalNumberOfCells() ; k++) {
                    System.out.println(row.getCell(k)+"\t");
                }
                System.out.println("sheet表"+i+"处理完毕----");
            }
        }
    }

}
/*
import java.io.File;
        import java.io.FileInputStream;
        import org.apache.poi.hssf.usermodel.HSSFRow;
        import org.apache.poi.hssf.usermodel.HSSFSheet;
        import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class getAllExcelData {

            public static void main(String[] args) {
                    try {
                        showExcel();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }
            public static  void showExcel() throws Exception {
                HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File("/file/student.xls")));
                HSSFSheet sheet=null;
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                     sheet=workbook.getSheetAt(i);
                     for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
                        HSSFRow row=sheet.getRow(j);
                        for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
                            System.out.print(row.getCell(k)+"\t");
                        }
                        System.out.println("---Sheet表"+i+"处理完毕---");
                    }
            }
            }
        }
        ————————————————
        版权声明：本文为CSDN博主「银狐公子」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/maoxiao1229/article/details/50884290*/
