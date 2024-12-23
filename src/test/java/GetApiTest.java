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

    final String basePath = "https://petstore.swagger.io/v2/swagger.json";
    final String workbookPath = "src/test/java/resources/GetRequest.xlsx";
    String authToken = "";

    @Test(description = "Base URL Test")
    @Severity(SeverityLevel.BLOCKER)
    public void baseTest() {
        Response response = given()
                .header("Content-Type", "application/json")
                .get(basePath);
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
                .get(endpoint + status);
        response.prettyPrint();
        String finalStatus = response.jsonPath().getString("status[0]");

        String sc = String.valueOf(response.getStatusCode());

        if (!sc.equals(statusCode) && !status.equals(finalStatus))
            Assert.fail();
    }
}
