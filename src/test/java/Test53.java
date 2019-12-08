import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test53 {
    @DataProvider(parallel = true)
    public Object[][] getData() {
        return new Object[][] {
                {"1 searchRequest"},
                {"2 searchRequest"},
                {"3 searchRequest"}

        };

    }





    @Test(dataProvider = "getData")
    public void Test31(String searchRequest) {


        System.out.println( searchRequest);

    }
}
