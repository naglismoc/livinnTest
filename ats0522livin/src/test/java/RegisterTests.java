import org.example.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTests {

    @Test
    public void positiveTest(){
        User u = new User("jonas","kisielius","jonokisielius10@gmail.com","68479974","Aa3456789");
        u.register();
        String text = "";
        try {
            text = User.driver.findElement(By.className("form-title")).getText();
        }catch (Exception e){}
        Assert.assertEquals(text,"Patvirtinkite paskyrą");
    }

    @Test
    public void noEmailAddressTest(){
        User u = new User("jonas","kisielius","","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailUserNoEtaNoDomainNoTopDomainTest(){
        User u = new User("jonas","kisielius","labas","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailUserEtaDomainNoTopDomainTest(){
        User u = new User("jonas","kisielius","labas@varle","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailWithSpaceInUserTest(){
        User u = new User("jonas","kisielius","labas vakaras@varle.lt","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailTooLongTest(){
        User u = new User("jonas","kisielius","labavakara".repeat(60) + "@varle.lt ","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"El. paštas turi būti ilgesnis nei 255 simbolių.");
    }

    @Test
    public void emailWithLeadingTreilingSpacesTest(){
        User u = new User("jonas","kisielius"," labavakara@varle.lt ","63777954","Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }


    @BeforeClass
    public void beforeClass(){
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        acceptCookies();
    }

    public String errorMsg(String field){
        String errorMsg = "";
        int position = 0;

        switch (field){
            case "name":
                position = 0;
                break;
            case "surname":
                position = 1;
                break;
            case "email":
                position = 2;
                break;
        }
        try {
            errorMsg = User.driver.
                    findElement(By.className("login-page-body--form")).
                    findElements(By.className("form-block")).get(position).
                    findElement(By.className("field-error")).getText();
        }catch (Exception e){}
        return errorMsg;
    }
    public void acceptCookies(){
        User.driver.get("https://www.livinn.lt/register");
        User.driver.findElement(By.id("onetrust-button-group")).click();
    }

    @AfterClass
    public void afterClass(){
      //  driver.close();
    }
}
