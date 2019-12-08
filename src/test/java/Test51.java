import org.testng.annotations.*;

public class Test51 {






    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\tbeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\tafterMethod");
    }


    @BeforeClass(enabled = false)
    public void beforeClass() {
        System.out.println("\tbeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\tafterClass");
    }


    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("afterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");

    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite");
    }




    @Test(timeOut = 3)
    public void Test31() {

       method33();
       System.out.println("\t\tTest31");
       // throw new NullPointerException();
    }

   //@Test(description = "ssdfsigisgdfigufd")
    @Test(dependsOnMethods = "Test31", alwaysRun = true)
    public void Test32() {
        System.out.println("\t\tTest32");
    }


    public void method33() {
        System.out.println("\t\tMethod33");
    }





}
