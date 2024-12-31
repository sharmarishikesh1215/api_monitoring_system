package utils;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.IOException;

public class SlackUpdater extends TestNGListener implements ITestListener {

    private static String urlSlackWebHook = "https://hooks.slack.com/services/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";
    private static String channelName = "apimonitoring";
    private static String userOAuthToken= "xoxb-8222274585907-8222413251090-ObPUy03LOBKMCk8QFD6oX7cZ";


    public void onTestFailure(ITestResult result){
        sendTestExecutionStatusToSlack("Test Failed: "+result.getMethod().getConstructorOrMethod().getName());
    }

    @Test
    public void SendStatus(){
        double percent=(float)(TestNGListener.successnumber)/(TestNGListener.totalnumber-1);
        percent = percent*100;
        String rounded= String.format("%.2f", percent);
        rounded+="%";
        sendTestExecutionStatusToSlack("Success Percentage: " + rounded + "\n" + "======================================");
    }

    public void sendTestExecutionStatusToSlack(String message) {
        try {
            StringBuilder messageBuider = new StringBuilder();
            messageBuider.append(message);
            Payload payload = Payload.builder().channel(channelName).text(messageBuider.toString()).build();

            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
            webhookResponse.getMessage();
        } catch (IOException e) {
            System.out.println("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }

}