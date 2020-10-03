import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import model.CreateFeedRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RSSFeedIntegrationTest {

    private static DropwizardAppExtension<RSSFeedConfiguration> EXT = new DropwizardAppExtension(RSSFeedApplication.class,
        ResourceHelpers.resourceFilePath("dropwizard-service-config.yml"));

    private static String id = "";


    @Test
    void testCreateFeed() {
        Client client = EXT.client();
        String feedUrl = "https://twitter.com/narendramodi";
        CreateFeedRequest request = new CreateFeedRequest();
        request.setSrcUrl(feedUrl);
        Response response = client.target(
                String.format("http://localhost:%d/feed-subscriber/feeds/createFeed?url=%s", EXT.getLocalPort(),feedUrl))
                .request()
                .get();
        String res = response.readEntity(String.class);
        JsonObject responseObject = JsonParser.parseString(res).getAsJsonObject();
        Boolean result = responseObject.get("success").getAsBoolean();
        JsonObject feed = responseObject.get("feed").getAsJsonObject();
        id = feed.get("id").getAsString();
        Assert.assertTrue(result);
        assert (response.getStatus() == 200);

    }

   @Test
    void testGetFeedList() {
        Client client = EXT.client();

        Response response = client.target(
                String.format("http://localhost:%d/feed-subscriber/feeds/getFeedList", EXT.getLocalPort()))
                .request()
                .get();
        String res = response.readEntity(String.class);
        JsonObject responseObject = JsonParser.parseString(res).getAsJsonObject();
        Boolean result = responseObject.get("success").getAsBoolean();
        Assert.assertTrue(result);
        assert (response.getStatus() == 200);

    }

   @Test
    void testDeleteFeedById() {
        Client client = EXT.client();
        Response response = client.target(
                String.format("http://localhost:%d/feed-subscriber/feeds/deleteFeed?id=%s", EXT.getLocalPort(), id))
                .request()
                .delete();
        String res = response.readEntity(String.class);
        Boolean result = JsonParser.parseString(res).getAsJsonObject().get("success").getAsBoolean();
        Assert.assertTrue(result);
        assert (response.getStatus() == 200);

    }

  private void testHelper(JsonArray feedList){
        for(int i = 0 ; i < feedList.size(); i++){
            JsonObject jsonObject = feedList.get(i).getAsJsonObject();
                id = jsonObject.get("id").getAsString();
        }
    }
}
