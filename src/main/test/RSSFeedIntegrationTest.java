import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RSSFeedIntegrationTest {

private static DropwizardAppExtension<RSSFeedConfiguration> EXT = new DropwizardAppExtension(RSSFeedApplication.class,
        ResourceHelpers.resourceFilePath("dropwizard-service-config.yml"));

    @Test
    void testRssFeed() {
        Client client = EXT.client();

        Response response = client.target(
                String.format("http://localhost:%d/feed-subscriber/feeds", EXT.getLocalPort()))
                .request()
                .get();
        String res = response.readEntity(String.class);
        Boolean result = JsonParser.parseString(res).getAsJsonObject().get("success").getAsBoolean();
        Assert.assertTrue(result);
        assert (response.getStatus() == 200);

    }
}
