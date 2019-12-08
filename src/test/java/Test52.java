import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test52 {

    @DataProvider(parallel = true)
    public Object[][] getData() {
       return new Object[][] {
                {"1 searchRequest", "2"},
                {"2 searchRequest", "2"},
                {"3 searchRequest", "2"}

        };

    }





    @Test(dataProvider = "getData")
    public void Test31(String searchRequest, String arg2/*, String expectedResult*/) {


        System.out.println( searchRequest + " " + arg2);

    }


}
