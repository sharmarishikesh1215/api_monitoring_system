import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelDataExtractor;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetApiTest {


    final String finalPath = "https://petstore.swagger.io/v2";
    final String workbookPath = "src/test/java/resources/GetRequest.xlsx";

    @Test(description = "Base URL Test")
    @Severity(SeverityLevel.BLOCKER)
    public void baseTest() {
        final String path = "/swagger.json";
        Response response = given()
                .header("Content-Type", "application/json")
                .get(finalPath + path);
        response.prettyPrint();
        if (response.getStatusCode() != 200)
            Assert.fail();
    }

    @DataProvider(name="status")
    public Object[][] findStatusDataProvider() throws IOException {
        return ExcelDataExtractor.testDataExtractor(workbookPath, "findByStatus");
    }

    @Test(description = "Find By Status API", dataProvider = "status")
    @Severity(SeverityLevel.BLOCKER)
    public void findByStatus(String apiName, String endpoint, String status, String statusCode) {
        System.out.println("API Name: " + apiName);

        Response response = given()
                .header("Content-Type", "application/json")
                .get(finalPath + endpoint + status);
        response.prettyPrint();

        String sc = String.valueOf(response.getStatusCode());

        if (!sc.equals(statusCode))
            Assert.fail();
    }

    @DataProvider(name = "store")
    public Object[][] storeDataProvider() throws IOException {
        return ExcelDataExtractor.testDataExtractor(workbookPath, "store");
    }

    @Test(dataProvider = "store")
    public void store(String apiName, String endPoint,  String statusCode) {
        System.out.println("API Name: " + apiName);

        Response response = given()
                .get(finalPath + endPoint);
        response.prettyPrint();
        Assert.assertEquals(String.valueOf(response.getStatusCode()), statusCode);
    }
}
