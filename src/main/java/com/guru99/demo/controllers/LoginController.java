package com.guru99.demo.controllers;

import com.guru99.demo.utils.UtilsDrivers;
import static com.guru99.demo.pageobjects.LoginPageObject.*;

public class LoginController {

    // Method to create what action our elements of web driver will do
    public static void clickLinkVisit(){
        UtilsDrivers.clickOnElement(generateAccess);
    }

    public static void writeEmailUser(String email){
        // note: String email is a variable to get of file excel
        UtilsDrivers.clickOnElement(txtEmailRegister);
        UtilsDrivers.writeTextField(txtEmailRegister, email);
    }

    public static void clickButtonSumbit(){

        UtilsDrivers.clickOnElement(btnSubmit);
    }

    public static void controlMessageErrorTryCatch(String mensaje){

        try{

            switch (mensaje){
                case "errorDoWhile":
                    String message = "Error on cycle do while, maybe the page is fail or you close the browser";
                    System.out.println(message);
                    break;

                case "errorFileExcel":
                    String message_excel = "Error on reading file excel, maybe the file is null or empty";
                    System.out.println(message_excel);
                    break;

            }

        }catch(Exception e){

            e.printStackTrace();
        }

    }
}
