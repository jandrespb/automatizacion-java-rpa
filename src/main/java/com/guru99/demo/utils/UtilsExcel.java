package com.guru99.demo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import  com.guru99.demo.models.*;

public class UtilsExcel {

    // global Variables
    public static ArrayList<Map<String, String>> arrayReadingExcelLogin = new ArrayList<Map<String, String>>();
    public static ArrayList<LoginModel> arrayDataModelLogin = new ArrayList<LoginModel>();


    public static ArrayList<Map<String, String>> readExcel(String filepath, String sheetName) {
        try {
            ArrayList<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<Map<String, String>>();
            Map<String, String> informacionProyecto = new HashMap<String, String>();
            File file = new File(filepath);
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
            Iterator<Row> rowIterator = newSheet.iterator();
            Row titulos = rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.getColumnIndex();
                    switch (cell.getCellType()) {
                        case STRING:
                            informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((long) cell.getNumericCellValue()));
                            break;
                        case FORMULA:
                            switch (cell.getCachedFormulaResultType()) {
                                case NUMERIC:
                                    informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((double) (cell.getNumericCellValue())));
                                    break;
                                case STRING:
                                    informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                                    break;
                            }
                            break;
                        case BLANK:
                            informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                            break;
                        default:
                    }
                }
                arrayListDatoPlanTrabajo.add(informacionProyecto);
                informacionProyecto = new HashMap<String, String>();
                System.out.println(arrayListDatoPlanTrabajo);
            }
            return arrayListDatoPlanTrabajo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void modifyCellsLogin(int fila, int columna, String valorString) {

//        String pathNameLogin ="/Users/macbookdejaimeandres/Documents/Automatizaciones/IntelliJOpenJDK/MiPrimerRPA/" +
//                "src/main/resources/templates/LoginTemplateDemoGuru.xlsx";

        String pathNameLogin = "src/main/resources/templates/LoginTemplateDemoGuru.xlsx";
        try {

            // Se crea una referencia al documento excel
            File archivo = new File(pathNameLogin);
            FileInputStream file = new FileInputStream(archivo);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum() + sheet.getFirstRowNum();
            Row row = sheet.getRow(fila);
            if (row == null) {
                row = sheet.createRow(fila);
            }
            Cell cell = row.getCell(columna);
            if (cell == null) {
                cell = row.createCell(columna);
            }
            if (valorString != null) {
                cell.setCellValue(valorString);
            }
            FileOutputStream outputStream = new FileOutputStream(pathNameLogin);
            workbook.setForceFormulaRecalculation(true);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<LoginModel>getExcelDataLoginModel(String filepath, String sheetName){

        try{
            arrayReadingExcelLogin = readExcel(filepath, sheetName);

            for(int i = 0; i < arrayReadingExcelLogin.size(); i++){

                if(arrayReadingExcelLogin.get(i).get("Email User") != null){

                    LoginModel data = new LoginModel();
                    //note: these gets we put the name of column that we add on our file excel
                    data.setEmailUser(arrayReadingExcelLogin.get(i).get("Email User"));
                    data.setUserName(arrayReadingExcelLogin.get(i).get("User Name"));
                    data.setPassword(arrayReadingExcelLogin.get(i).get("Password"));
                    data.setMessageRobot(arrayReadingExcelLogin.get(i).get("Message Robot"));

                    arrayDataModelLogin.add(data);

                }else{

                    String messageEmpty = "The column User Name is empty on method getExcelDataLoginModel()";
                    System.out.println(messageEmpty);

                }

            }

            return arrayDataModelLogin;


        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
