package com.guru99.demo.pageobjects;

import org.openqa.selenium.By;

public class LoginPageObject {

    // methods locators
    public static By generateAccess = By.xpath("//a[text() = 'here']");
    public static By txtEmailRegister = By.name("emailid");
    public static By btnSubmit = By.name("btnLogin");
    public static By tableUserId = By.xpath("//tbody/tr[4]/td[2]");
    public static By tablePasswordId = By.xpath("//tbody/tr[5]/td[2]");
}
