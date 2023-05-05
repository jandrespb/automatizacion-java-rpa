package com.guru99.demo.stepsdefinitions;

import com.guru99.demo.models.LoginModel;
import com.guru99.demo.utils.UtilsDrivers;
import com.guru99.demo.utils.UtilsExcel;


import java.util.ArrayList;

import static com.guru99.demo.controllers.LoginController.clickLinkVisit;
import static com.guru99.demo.controllers.LoginController.controlMessageErrorTryCatch;
import static com.guru99.demo.controllers.LoginController.writeEmailUser;
import static com.guru99.demo.controllers.LoginController.clickButtonSumbit;
import static com.guru99.demo.utils.UtilsDrivers.quitWebpage;
import static com.guru99.demo.utils.UtilsDrivers.openBrowserWithChrome;
import static com.guru99.demo.utils.UtilsDrivers.timeWebPageSleep;
import static com.guru99.demo.utils.UtilsExcel.modifyCellsLogin;
import static com.guru99.demo.pageobjects.LoginPageObject.tableUserId;
import static com.guru99.demo.pageobjects.LoginPageObject.tablePasswordId;


public class LoginStepDefinitions {

    // This Section we program  the process that what our robot want to do, on this case the robot create 10 users
    // NOTE: This user is temporal of 20 days

    // Variables
    private static boolean validateSection = true;

    //Methods
    public static Boolean generateAccessOnDemoGuru() {

        // First Try - Catch - read excel
        try {

            String filePathLogin = "src/main/resources/templates/LoginTemplateDemoGuru.xlsx";

            ArrayList<LoginModel> varExcel = UtilsExcel.getExcelDataLoginModel(filePathLogin, "login");
            int i = 0;

            do {
                // Second Try-catch - bucle do-while
                try {
                    openBrowserWithChrome();
                    clickLinkVisit();
                    timeWebPageSleep(2);
                    if (varExcel.get(i).getEmailUser().contains("@") && varExcel.get(i).getEmailUser().contains(".com")) {
                        writeEmailUser(varExcel.get(i).getEmailUser());
                        timeWebPageSleep(2);
                        clickButtonSumbit();

                        timeWebPageSleep(1);
                        String userId = UtilsDrivers.getTextOfElement(tableUserId);
                        String passwordId = UtilsDrivers.getTextOfElement(tablePasswordId);
                        modifyCellsLogin(i + 1, 1, userId);
                        modifyCellsLogin(i + 1, 2, passwordId);
                        modifyCellsLogin(i + 1, 3, "SUCCESSFUL");

                    } else {

                        modifyCellsLogin(i + 1, 3, "INVALID EMAIL");
                    }


                } catch (Exception e) {
                    quitWebpage();
                    modifyCellsLogin(i + 1, 3, "TECHNICAL ERROR");
                    e.printStackTrace();
                    controlMessageErrorTryCatch("errorDoWhile");
                }

                quitWebpage();

                i++;
            } while (i < varExcel.size());


        } catch (Exception e) {

            quitWebpage();
            controlMessageErrorTryCatch("errorFileExcel");
            e.printStackTrace();
        }

        return validateSection;
    }
}
