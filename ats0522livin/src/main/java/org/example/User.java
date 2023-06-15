package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class User {
    public static WebDriver driver;
    private String name;
    private String surname;
    private String email;
    private String phoNo;
    private String password;

    public User() {
    }
    public User(String name, String surname, String email, String phoNo, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoNo = phoNo;
        this.password = password;
    }
    public void register(){
        driver.get("https://www.livinn.lt/register");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sylius_customer_registration_firstName"))).sendKeys(this.name);
//        driver.findElement(By.id("sylius_customer_registration_firstName")).sendKeys(this.name);
        driver.findElement(By.id("sylius_customer_registration_lastName")).sendKeys(this.surname);
        driver.findElement(By.id("sylius_customer_registration_email")).sendKeys(this.email);
        driver.findElement(By.name("telephone")).sendKeys(this.phoNo);
        driver.findElement(By.id("sylius_customer_registration_user_plainPassword")).sendKeys(this.password);
        driver.findElement(By.xpath("//*[@id=\"website-content\"]/div/div/div/section/form/div/div[7]/div/label/span")).click();
        driver.findElement(By.xpath("//*[@id=\"website-content\"]/div/div/div/section/form/div/div[8]/input")).click();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoNo() {
        return phoNo;
    }

    public void setPhoNo(String phoNo) {
        this.phoNo = phoNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
