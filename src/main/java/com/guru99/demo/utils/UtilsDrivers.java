package com.guru99.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class UtilsDrivers {

    public static WebDriver driver;
    public static String windowDefault;


    public static WebDriver getDriver(){
        return driver;
    }

    public static void openBrowserWithChrome(){
        String urlPage = "https://demo.guru99.com/V4/index.php";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(urlPage);
    }

    public static void clickOnElement(By elemento){
        int Attempts = 10;
        while (Attempts>0){
            if(driver.findElement(elemento).isDisplayed()){
                driver.findElement(elemento).click();
                break;
            }else{
                Attempts--;
                //ClsTiempoEspera.esperarSegundos(2);
                System.out.println("El elemento no existe en la aplicación");
            }
        }
    }

    public static void quitWebpage(){

        driver.quit();
    }

    public static void writeTextField(By elemento, String texto){

        driver.findElement(elemento).sendKeys(texto);

    }

    public static void timeWebPageSleep(int seconds){

        try {

            switch (seconds){
                case 1:
                    Thread.sleep(1000);
                    break;

                case 2:
                    Thread.sleep(2000);
                    break;

                case 3:
                    Thread.sleep(3000);
                    break;

                case 4:
                    Thread.sleep(4000);
                    break;

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("This function only have until 4 seconds max");
        }
    }

    public static String getTextOfElement(By elemento){

        return driver.findElement(elemento).getText();
    }
}
